package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.registries.MCD_GameRules;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.Random;

public class SunsGraceItem extends MaceBaseItem {
    public SunsGraceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        DungeonsHelpers.Tooltip.appendDescription(textConsumer, Text.translatable("tooltip.dungeons_reborn.suns_grace"));
        DungeonsHelpers.Tooltip.appendMcdRarity(textConsumer, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendToggle(textConsumer, Text.translatable("toggle.dungeons_reborn.teammate_only"),
                stack, MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.powerful_combo"), true);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.radiance"), false);
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void postChargedAttack(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        if ((target instanceof MobEntity || target instanceof PlayerEntity) && attacker.getEntityWorld() instanceof ServerWorld serverWorld) {
            double probability = serverWorld.getGameRules().getValue(MCD_GameRules.RADIANCE_TRIGGER_PROBABILITY) / 100.0;
            if (random.nextDouble() < probability) {
                BlockPos center = target.getBlockPos();
                int radius = serverWorld.getGameRules().getValue(MCD_GameRules.RADIANCE_RANGE);
                Boolean teammateOnlyToggle = stack.get(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE);
                boolean teammateOnly = Boolean.TRUE.equals(teammateOnlyToggle);
                DungeonsHelpers.executeForPlayersWithinDistance(serverWorld, center, radius, (playerEntity) -> {
                    if (!teammateOnly || DungeonsHelpers.areAllies(attacker, playerEntity)) {
                        int healAmount = serverWorld.getGameRules().getValue(MCD_GameRules.RADIANCE_HEAL);
                        if (healAmount > 0) playerEntity.heal(healAmount);
                        if (healAmount < 0) playerEntity.damage(serverWorld, serverWorld.getDamageSources().magic(), -healAmount);
                    }
                    return null;
                });
                if (radius != 0)
                    serverWorld.spawnParticles(new DustParticleEffect(16757504, 2.5f),
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            (int) Math.ceil(Math.sqrt(radius)) * 80, (float) radius / 2f, (float) radius / 5f, (float) radius / 2f, 1);
            }
        }
        super.postChargedAttack(stack, target, attacker);
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
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackDamagePair(@Nullable McdRarity mcdRarity) {
        return new Pair<>(8d, 12d);
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackSpeedPair(@Nullable McdRarity mcdRarity) {
        return new Pair<>(1.6d, 0.6d);
    }
}
