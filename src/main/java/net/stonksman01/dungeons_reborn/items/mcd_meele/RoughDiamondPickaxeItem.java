package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;
import net.stonksman01.dungeons_reborn.items.McdItem;

import java.util.List;
import java.util.Random;

public class RoughDiamondPickaxeItem extends SkyCore.ToolAPI.PickaxeItem {
    public RoughDiamondPickaxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.rough_diamond_pickaxe.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.rough_diamond_pickaxe.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
        tooltip.add(Text.translatable("enchantment.dungeons_reborn.prospector").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN)));
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isDead() && target instanceof MobEntity && attacker.getWorld() instanceof ServerWorld serverWorld) {
            Random random = new Random();
            int range = (int) Math.round(6.0 * (20.0 / Math.max(1, (int) target.getMaxHealth())));
            int range_capped = Math.min(Math.max(range, 3), 120); // capped range to avoid extreme values
            if (random.nextInt(range_capped - 1) == 0) { // algorithm for hp-based Prospector trigger probability
                serverWorld.spawnEntity(new ItemEntity(serverWorld, target.getX(), target.getY(), target.getZ(), new ItemStack(Items.EMERALD, random.nextInt(5) + 1)));
                serverWorld.spawnParticles(new DustParticleEffect(Vec3d.unpackRgb(65343).toVector3f(), 1.0f),
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        10, 0.25F, 0.25F, 0.25F, 3);
            }
        }
        super.postHit(stack, target, attacker);
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
