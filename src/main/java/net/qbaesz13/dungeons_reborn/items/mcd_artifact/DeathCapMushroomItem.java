package net.qbaesz13.dungeons_reborn.items.mcd_artifact;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdArtifactItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.registries.MCD_Sounds;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class DeathCapMushroomItem extends McdArtifactItem {
    public DeathCapMushroomItem(Properties properties) {
        super(properties);
    }
    @Override @NullMarked
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        McdRarity mcdRarity = itemStack.get(MCD_DataComponents.MCD_RARITY);
        if (level instanceof ServerLevel serverLevel && Objects.nonNull(mcdRarity)) {
            int duration = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_DURATION);
            int amplifier = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_AMPLIFIER);
            int cooldown = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_COOLDOWN);
            if (mcdRarity == McdRarity.RARE) {
                duration = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_RARE_DURATION);
                amplifier = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_RARE_AMPLIFIER);
                cooldown = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_DEATH_CAP_MUSHROOM_RARE_COOLDOWN);
            }
            if (cooldown != 0) user.getCooldowns().addCooldown(itemStack, cooldown);
            user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, duration, amplifier, false, true, true));
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, duration, amplifier, false, true, true));
            serverLevel.playSound(null, user, MCD_Sounds.DEATH_CAP_MUSHROOM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
            itemStack.hurtAndBreak(1, user, hand.asEquipmentSlot());
        }
        return InteractionResult.SUCCESS;
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.artifact.death_cap_mushroom"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, itemStack);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.artifact.death_cap_mushroom"), true);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.setRareOrCommonVariant(itemStack);
        super.inventoryTick(itemStack, level, owner, slot);
    }
}
