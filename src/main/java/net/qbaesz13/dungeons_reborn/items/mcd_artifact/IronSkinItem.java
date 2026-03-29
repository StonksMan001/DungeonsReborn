package net.qbaesz13.dungeons_reborn.items.mcd_artifact;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
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

public class IronSkinItem extends McdArtifactItem {
    public IronSkinItem(Properties properties) {
        super(properties);
    }

    @Override @NullMarked
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem) {
        if (clickAction == ClickAction.SECONDARY && Objects.nonNull(MCD_DataComponents.TEAMMATE_ONLY_TOGGLE)) {
            self.set(MCD_DataComponents.TEAMMATE_ONLY_TOGGLE, Boolean.FALSE.equals(self.get(MCD_DataComponents.TEAMMATE_ONLY_TOGGLE)));
            return true;
        } else {
            return super.overrideOtherStackedOnMe(self, other, slot, clickAction, player, carriedItem);
        }
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (itemStack.get(MCD_DataComponents.TEAMMATE_ONLY_TOGGLE) == null) {
            itemStack.set(MCD_DataComponents.TEAMMATE_ONLY_TOGGLE, false);
        }
        DungeonsHelpers.setRareOrCommonVariant(itemStack);
        super.inventoryTick(Objects.requireNonNull(itemStack), level, owner, slot);
    }
    @Override @NullMarked
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);
        McdRarity mcdRarity = stack.get(MCD_DataComponents.MCD_RARITY);
        Boolean teammateOnlyToggle = stack.get(MCD_DataComponents.TEAMMATE_ONLY_TOGGLE);
        if (level instanceof ServerLevel serverLevel && Objects.nonNull(mcdRarity) && Objects.nonNull(teammateOnlyToggle)) {
            int range = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_RANGE);
            int cooldown = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_COOLDOWN);
            var ref = new Object() {
                int amplifier = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_AMPLIFIER);
                int duration = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_COMMON_DURATION);
            };
            if (mcdRarity == McdRarity.RARE) {
                range = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_RANGE);
                cooldown = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_COOLDOWN);
                ref.amplifier = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_AMPLIFIER);
                ref.duration = serverLevel.getGameRules().get(MCD_GameRules.ARTIFACT_IRON_HIDE_AMULET_RARE_DURATION);
            }
            boolean teammateOnly = Boolean.TRUE.equals(teammateOnlyToggle);
            if (cooldown != 0) user.getCooldowns().addCooldown(stack, cooldown);
            serverLevel.playSound(null, user, MCD_Sounds.IRON_HIDE_AMULET_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
            stack.hurtAndBreak(1, user, hand.asEquipmentSlot());
            DungeonsHelpers.executeForPlayersWithinDistance(serverLevel, user.blockPosition(), range, (otherPlayer) -> {
                if (!teammateOnly || DungeonsHelpers.areAllies(user, otherPlayer)) {
                    otherPlayer.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, ref.duration, ref.amplifier, false, true, true));
                    serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                            otherPlayer.getX(),
                            otherPlayer.getY(),
                            otherPlayer.getZ(),
                            20, 0.5F, 1.0F, 0.5F, 1);
                }
                return null;
            });
        }
        return InteractionResult.SUCCESS;
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, itemStack);
        DungeonsHelpers.Tooltip.appendToggle(builder, Component.translatable("toggle.dungeons_reborn.teammate_only"),
                itemStack, MCD_DataComponents.TEAMMATE_ONLY_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.artifact.iron_hide_amulet"), true);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
