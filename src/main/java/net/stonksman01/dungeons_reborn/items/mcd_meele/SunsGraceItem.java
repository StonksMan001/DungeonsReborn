package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.type.TooltipDisplayComponent;
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

import java.util.function.Consumer;

public class SunsGraceItem extends MaceBaseItem {
    public SunsGraceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        DungeonsHelpers.Tooltip.appendDescription(textConsumer, Text.translatable("tooltip.dungeons_reborn.suns_grace"));
        DungeonsHelpers.Tooltip.appendMcdRarity(textConsumer, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.powerful_combo"), true);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.radiance"), false);
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
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
