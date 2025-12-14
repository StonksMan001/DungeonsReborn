package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SunsGraceItem extends MaceBaseItem {
    public SunsGraceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.suns_grace"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.powerful_combo"), true);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.radiance"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackDamagePair(@Nullable McdRarity mcdRarity) {
        return null;
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackSpeedPair(@Nullable McdRarity mcdRarity) {
        return null;
    }
}
