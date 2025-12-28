package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.entity.Entity;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.registries.MCD_GameRules;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class SunsGraceItem extends MaceBaseItem {
    public SunsGraceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.suns_grace"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendToggle(tooltip, Text.translatable("toggle.dungeons_reborn.teammate_only"),
                stack, MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.powerful_combo"), true);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.radiance"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        if ((target instanceof MobEntity || target instanceof PlayerEntity) && attacker.getWorld() instanceof ServerWorld serverWorld && random.nextInt(5) == 0) {
            BlockPos center = target.getBlockPos();
            int radius = MCD_GameRules.RADIANCE_RANGE.getValue(serverWorld);
            Boolean teammateOnlyToggle = stack.get(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE);
            boolean teammateOnly = Boolean.TRUE.equals(teammateOnlyToggle);
            DungeonsHelpers.executeForPlayersWithinDistance(serverWorld, center, radius, (playerEntity) -> {
                if (!teammateOnly || DungeonsHelpers.areAllies(attacker, playerEntity)) {
                    int healAmount = MCD_GameRules.RADIANCE_HEAL.getValue(serverWorld);
                    if (healAmount > 0) playerEntity.heal(healAmount);
                    if (healAmount < 0) playerEntity.damage(serverWorld.getDamageSources().magic(), -healAmount);
                }
                return null;
            });
            if (radius != 0) serverWorld.spawnParticles(new DustParticleEffect(Vec3d.unpackRgb(16757504).toVector3f(), 2.5f),
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    (int)Math.ceil(Math.sqrt(radius)) * 80, (float)radius / 2f, (float)radius / 5f, (float)radius / 2f, 1);
        }
        super.postDamageEntity(stack, target, attacker);
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
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE) == null) {
            stack.set(MCD_DataComponentTypes.TEAMMATE_ONLY_TOGGLE, false);
        }
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot, selected);
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
