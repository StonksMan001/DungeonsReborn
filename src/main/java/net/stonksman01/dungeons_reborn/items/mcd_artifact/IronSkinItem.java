package net.stonksman01.dungeons_reborn.items.mcd_artifact;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdArtifactItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.registries.MCD_Sounds;

import java.util.List;
import java.util.Objects;

public class IronSkinItem extends McdArtifactItem {
    public IronSkinItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && MCD_DataComponentTypes.IRON_HIDE_AMULET_TEAMMATE_ONLY_TOGGLE != null) {
            stack.set(MCD_DataComponentTypes.IRON_HIDE_AMULET_TEAMMATE_ONLY_TOGGLE, Boolean.FALSE.equals(stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_TEAMMATE_ONLY_TOGGLE)));
            return true;
        } else {
            return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
        }
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_TEAMMATE_ONLY_TOGGLE) == null) {
            stack.set(MCD_DataComponentTypes.IRON_HIDE_AMULET_TEAMMATE_ONLY_TOGGLE, false);
        }
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
        Boolean teammateOnlyToggle = stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_TEAMMATE_ONLY_TOGGLE);
        int distance = mcdRarity == McdRarity.RARE ? 15 : 10;
        int duration = mcdRarity == McdRarity.RARE ? 260 : 200;
        if (!world.isClient && Objects.nonNull(mcdRarity) && Objects.nonNull(teammateOnlyToggle)) {
            boolean personal = Boolean.TRUE.equals(teammateOnlyToggle);
            user.getItemCooldownManager().set(this, 500);
            world.playSoundFromEntity(null, user, MCD_Sounds.IRON_HIDE_AMULET_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            stack.damage(1, user, LivingEntity.getSlotForHand(hand));
            DungeonsHelpers.executeForPlayersWithinDistance(world, user.getBlockPos(), distance, (entity) -> {
                if (!personal || DungeonsHelpers.areAllies(user, entity)) {
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, duration, 2, false, true, true));
                    spawnIronHideAmuletParticle(world, entity);
                }
                return null;
            });
        }
        return TypedActionResult.success(stack, true);
    }
    private void spawnIronHideAmuletParticle(World world, Entity entity) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                    entity.getX(),
                    entity.getY(),
                    entity.getZ(),
                    20, 0.5F, 1.0F, 0.5F, 1);
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, stack);
        DungeonsHelpers.Tooltip.appendToggle(tooltip, Text.translatable("toggle.dungeons_reborn.teammate_only"),
                stack, MCD_DataComponentTypes.IRON_HIDE_AMULET_TEAMMATE_ONLY_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.artifact.iron_hide_amulet"), true);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
