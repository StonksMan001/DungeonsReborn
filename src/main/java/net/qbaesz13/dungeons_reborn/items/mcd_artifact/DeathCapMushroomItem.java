package net.qbaesz13.dungeons_reborn.items.mcd_artifact;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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

public class DeathCapMushroomItem extends McdArtifactItem {
    public DeathCapMushroomItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
        if (world instanceof ServerWorld serverWorld && Objects.nonNull(mcdRarity)) {
            int duration = MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_DURATION.getValue(serverWorld);
            int amplifier = MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_AMPLIFIER.getValue(serverWorld);
            int cooldown = MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_COOLDOWN.getValue(serverWorld);
            if (mcdRarity == McdRarity.RARE) {
                duration = MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_RARE_DURATION.getValue(serverWorld);
                amplifier = MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_RARE_AMPLIFIER.getValue(serverWorld);
                cooldown = MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_RARE_COOLDOWN.getValue(serverWorld);
            }
            if (cooldown != 0) user.getItemCooldownManager().set(stack, cooldown);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, duration, amplifier, false, true, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, duration, amplifier, false, true, true));
            serverWorld.playSoundFromEntity(null, user, MCD_Sounds.DEATH_CAP_MUSHROOM_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            stack.damage(1, user, hand.getEquipmentSlot());
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        DungeonsHelpers.Tooltip.appendDescription(textConsumer, Text.translatable("tooltip.dungeons_reborn.artifact.death_cap_mushroom"));
        DungeonsHelpers.Tooltip.appendMcdRarity(textConsumer, stack);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.artifact.death_cap_mushroom"), true);
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
}
