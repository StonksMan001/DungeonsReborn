package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;

import java.util.Random;
import java.util.function.Consumer;

public class SunsGraceItem extends MaceBaseItem {
    public SunsGraceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.suns_grace"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendToggle(builder, Component.translatable("toggle.dungeons_reborn.teammate_only"),
                itemStack, MCD_DataComponents.TEAMMATE_ONLY_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.powerful_combo"), true);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.radiance"), false);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override @NullMarked
    public void postChargedAttack(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        if ((target instanceof Mob || target instanceof Player) && attacker.level() instanceof ServerLevel serverLevel) {
            double probability = serverLevel.getGameRules().get(MCD_GameRules.RADIANCE_TRIGGER_PROBABILITY) / 100.0;
            if (random.nextDouble() < probability) {
                BlockPos center = target.blockPosition();
                int radius = serverLevel.getGameRules().get(MCD_GameRules.RADIANCE_RANGE);
                Boolean teammateOnlyToggle = itemStack.get(MCD_DataComponents.TEAMMATE_ONLY_TOGGLE);
                boolean teammateOnly = Boolean.TRUE.equals(teammateOnlyToggle);
                DungeonsHelpers.executeForPlayersWithinDistance(serverLevel, center, radius, (playerEntity) -> {
                    if (!teammateOnly || DungeonsHelpers.areAllies(attacker, playerEntity)) {
                        int healAmount = serverLevel.getGameRules().get(MCD_GameRules.RADIANCE_HEAL);
                        if (healAmount > 0) playerEntity.heal(healAmount);
                        if (healAmount < 0) playerEntity.hurtServer(serverLevel, serverLevel.damageSources().magic(), -healAmount);
                    }
                    return null;
                });
                if (radius != 0)
                    serverLevel.sendParticles(new DustParticleOptions(16757504, 2.5f),
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            (int) Math.ceil(Math.sqrt(radius)) * 80, (float) radius / 2f, (float) radius / 5f, (float) radius / 2f, 1);
            }
        }
        super.postChargedAttack(itemStack, target, attacker);
    }
    @Override @NullMarked
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem) {
        if (clickAction == ClickAction.SECONDARY && MCD_DataComponents.TEAMMATE_ONLY_TOGGLE != null) {
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
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override @NullUnmarked
    public Tuple<@NonNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        return new Tuple<>(8d, 12d);
    }
    @Override @NullUnmarked
    public Tuple<@NonNull Double, Double> getAttackSpeedPair(McdRarity mcdRarity) {
        return new Tuple<>(1.6d, 0.6d);
    }
}
