package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.stonksman01.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;

import java.util.List;
import java.util.function.Consumer;

public class HeartstealerItem extends ClaymoreBaseItem {
    public HeartstealerItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.heartstealer.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.heartstealer.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.heartstealer.tooltip3").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}
