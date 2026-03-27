package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.util.GrindStoneExperienceNotDropping;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class RoughDiamondSwordItem extends SkyCoreToolAPI.SwordItem implements GrindStoneExperienceNotDropping {
    public RoughDiamondSwordItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, net.minecraft.item.Item.Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rough_diamond_sword.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rough_diamond_sword.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
        textConsumer.accept(
                Text.translatable("tooltip.dungeons_reborn.built_in").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withItalic(true))
                        .append(Text.literal(" "))
                        .append(Text.translatable("enchantment.minecraft.sharpness").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withItalic(true)))
        );
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.addEnchantmentToStack(stack, world.getRegistryManager(), Enchantments.SHARPNESS, 5);
        DungeonsHelpers.makeUnrepairable(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}
