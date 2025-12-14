package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.util.GrindStoneExperienceNotDropping;

import java.util.List;

public class RoughDiamondSwordItem extends SkyCoreToolAPI.SwordItem implements GrindStoneExperienceNotDropping {
    public RoughDiamondSwordItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.rough_diamond_sword"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendBuiltInEnchantment(tooltip, Text.translatable("enchantment.minecraft.sharpness"));
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        DungeonsHelpers.addEnchantmentToStack(stack, world.getRegistryManager(), Enchantments.SHARPNESS, 5);
        super.inventoryTick(stack, world, entity, slot, selected);
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
