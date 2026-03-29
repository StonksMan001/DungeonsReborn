package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.type.TooltipDisplayComponent;
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
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.Nullable;

import java.util.Random;
import java.util.function.Consumer;

public class RoughDiamondPickaxeItem extends SkyCoreToolAPI.PickaxeItem {
    public RoughDiamondPickaxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        DungeonsHelpers.Tooltip.appendDescription(textConsumer, Text.translatable("tooltip.dungeons_reborn.rough_diamond_pickaxe"));
        DungeonsHelpers.Tooltip.appendMcdRarity(textConsumer, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.prospector"), false);
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getEntityWorld() instanceof ServerWorld serverWorld && target.isDead() && target instanceof MobEntity) {
            Random random = new Random();
            int range = (int) Math.round(6.0 * (20.0 / Math.max(1, (int) target.getMaxHealth())));
            int range_capped = MathHelper.clamp(range, 3, 120); // capped range to avoid extreme values
            double probability = (double) 1 / (range_capped - 1);
            double h = serverWorld.getGameRules().getValue(MCD_GameRules.PROSPECTOR_MINIMUM_TRIGGER_PROBABILITY) / 100.0;
            double modifiedProbability = probability * (1.0 - h) + h;
            if (modifiedProbability >= 1 || random.nextDouble() < modifiedProbability) { // algorithm for hp-based Prospector trigger probability
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
