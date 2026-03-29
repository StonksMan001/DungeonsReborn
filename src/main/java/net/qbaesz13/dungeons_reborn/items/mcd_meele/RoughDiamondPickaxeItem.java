package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Random;
import java.util.function.Consumer;

public class RoughDiamondPickaxeItem extends SkyCoreToolAPI.PickaxeItem {
    public RoughDiamondPickaxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, baseAttackDamage, attackSpeed, properties);
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.rough_diamond_pickaxe"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.prospector"), false);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override @NullMarked
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        if (attacker.level() instanceof ServerLevel serverLevel && target.isDeadOrDying() && target instanceof Mob) {
            Random random = new Random();
            int range = (int) Math.round(6.0 * (20.0 / Math.max(1, (int) target.getMaxHealth())));
            int range_capped = Mth.clamp(range, 3, 120); // capped range to avoid extreme values
            double probability = (double) 1 / (range_capped - 1);
            double h = serverLevel.getGameRules().get(MCD_GameRules.PROSPECTOR_MINIMUM_TRIGGER_PROBABILITY) / 100.0;
            double modifiedProbability = probability * (1.0 - h) + h;
            if (modifiedProbability >= 1 || random.nextDouble() < modifiedProbability) { // algorithm for hp-based Prospector trigger probability
                serverLevel.addFreshEntity(new ItemEntity(serverLevel, target.getX(), target.getY(), target.getZ(), new ItemStack(Items.EMERALD, random.nextInt(5) + 1)));
                serverLevel.sendParticles(new DustParticleOptions(65343, 1.0f),
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        10, 0.25F, 0.25F, 0.25F, 3);
            }
        }
        super.postHurtEnemy(itemStack, target, attacker);
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.makeUnrepairable(itemStack);
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}
