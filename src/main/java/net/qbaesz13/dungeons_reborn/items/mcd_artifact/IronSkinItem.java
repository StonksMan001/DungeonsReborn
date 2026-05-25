package net.qbaesz13.dungeons_reborn.items.mcd_artifact;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdArtifactItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.registries.MCD_Sounds;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class IronSkinItem extends McdArtifactItem {
    public IronSkinItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE != null) {
            stack.set(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE, Boolean.FALSE.equals(stack.get(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE)));
            return true;
        } else {
            return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
        }
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (stack.get(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE) == null) {
            stack.set(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE, false);
        }
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
        Boolean teammateOnlyToggle = stack.get(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE);
        if (world instanceof ServerWorld serverWorld && Objects.nonNull(mcdRarity) && Objects.nonNull(teammateOnlyToggle)) {
            int range = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_RANGE.getValue(serverWorld);
            int cooldown = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_COOLDOWN.getValue(serverWorld);
            var ref = new Object() {
                int amplifier = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_AMPLIFIER.getValue(serverWorld);
                int duration = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_DURATION.getValue(serverWorld);
            };
            if (mcdRarity == McdRarity.RARE) {
                range = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_RANGE.getValue(serverWorld);
                cooldown = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_COOLDOWN.getValue(serverWorld);
                ref.amplifier = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_AMPLIFIER.getValue(serverWorld);
                ref.duration = MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_DURATION.getValue(serverWorld);
            }
            boolean teammateOnly = Boolean.TRUE.equals(teammateOnlyToggle);
            if (cooldown != 0) user.getItemCooldownManager().set(stack, cooldown);
            serverWorld.playSoundFromEntity(null, user, MCD_Sounds.IRON_HIDE_AMULET_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            stack.damage(1, user, hand.getEquipmentSlot());
            DungeonsHelpers.executeForPlayersWithinDistance(serverWorld, user.getBlockPos(), range, (playerEntity) -> {
                if (!teammateOnly || DungeonsHelpers.areAllies(user, playerEntity)) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ref.duration, ref.amplifier, false, true, true));
                    serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            20, 0.5F, 1.0F, 0.5F, 1);
                }
                return null;
            });
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        DungeonsHelpers.Tooltip.appendDescription(textConsumer, Text.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet"));
        DungeonsHelpers.Tooltip.appendMcdRarity(textConsumer, stack);
        DungeonsHelpers.Tooltip.appendToggle(textConsumer, Text.translatable("toggle.dungeons_reborn.teammate_only"),
                stack, MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.artifact.iron_hide_amulet"), true);
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}
