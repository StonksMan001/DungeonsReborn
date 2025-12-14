package net.stonksman01.dungeons_reborn.items.mcd_ranged;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn._included_libs.skycore.items.SC_CrossbowItem;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;

import java.util.List;

public class HeavyCrossbowItem extends SC_CrossbowItem {
    public HeavyCrossbowItem(Settings settings) {
        super(settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.heavy_crossbow"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, stack);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
