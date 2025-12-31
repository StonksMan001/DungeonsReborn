package net.stonksman01.dungeons_reborn.items.mcd_ranged;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.stonksman01.dungeons_reborn._included_libs.skycore.items.SC_CrossbowItem;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class HeavyCrossbowItem extends SC_CrossbowItem {
    public HeavyCrossbowItem(Settings settings) {
        super(settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        DungeonsHelpers.Tooltip.appendDescription(textConsumer, Text.translatable("tooltip.dungeons_reborn.heavy_crossbow"));
        DungeonsHelpers.Tooltip.appendMcdRarity(textConsumer, stack);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.heavy_crossbow"), true);
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
}
