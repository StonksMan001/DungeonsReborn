package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.registries.MCD_GameRules;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;

import java.util.List;
import java.util.Random;

public class RoughDiamondPickaxeItem extends SkyCoreToolAPI.PickaxeItem {
    public RoughDiamondPickaxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.rough_diamond_pickaxe"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.prospector"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getWorld() instanceof ServerWorld serverWorld && target.isDead() && target instanceof MobEntity) {
            Random random = new Random();
            int range = (int) Math.round(6.0 * (20.0 / Math.max(1, (int) target.getMaxHealth())));
            int range_capped = MathHelper.clamp(range, 3, 120); // capped range to avoid extreme values
            double probability = (double) 1 / (range_capped - 1);
            double h = MCD_GameRules.PROSPECTOR_MINIMUM_TRIGGER_PERCENTAGE.getValue(serverWorld) / 100.0;
            double modifiedProbability = probability * (1.0 - h) + h;
            if (modifiedProbability >= 1 || random.nextDouble() < modifiedProbability) { // algorithm for hp-based Prospector trigger probability
                serverWorld.spawnEntity(new ItemEntity(serverWorld, target.getX(), target.getY(), target.getZ(), new ItemStack(Items.EMERALD, random.nextInt(5) + 1)));
                serverWorld.spawnParticles(new DustParticleEffect(Vec3d.unpackRgb(65343).toVector3f(), 1.0f),
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        10, 0.25F, 0.25F, 0.25F, 3);
            }
        }
        super.postDamageEntity(stack, target, attacker);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }
}
