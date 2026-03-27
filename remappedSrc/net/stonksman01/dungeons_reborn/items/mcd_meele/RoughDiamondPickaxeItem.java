package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
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
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Consumer;

public class RoughDiamondPickaxeItem extends SkyCoreToolAPI.PickaxeItem {
    public RoughDiamondPickaxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, net.minecraft.item.Item.Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rough_diamond_pickaxe.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rough_diamond_pickaxe.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
        textConsumer.accept(Text.translatable("enchantment.dungeons_reborn.prospector").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN)));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isDead() && target instanceof MobEntity && attacker.getEntityWorld() instanceof ServerWorld serverWorld) {
            Random random = new Random();
            int range = (int) Math.round(6.0 * (20.0 / Math.max(1, (int) target.getMaxHealth())));
            int range_capped = Math.min(Math.max(range, 3), 120); // capped range to avoid extreme values
            if (random.nextInt(range_capped - 1) == 0) { // algorithm for hp-based Prospector trigger probability
                serverWorld.spawnEntity(new ItemEntity(serverWorld, target.getX(), target.getY(), target.getZ(), new ItemStack(Items.EMERALD, random.nextInt(5) + 1)));
                serverWorld.spawnParticles(new DustParticleEffect(65343, 1.0f),
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        10, 0.25F, 0.25F, 0.25F, 3);
            }
        }
        super.postDamageEntity(stack, target, attacker);
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.makeUnrepairable(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}
