package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;

import java.util.List;

public class HeartstealerItem extends ClaymoreBaseItem {
    public HeartstealerItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.heartstealer"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.powerful_pushback"), true);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.leeching"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
