package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClaymoreItem extends ClaymoreBaseItem {
    public ClaymoreItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.get(MCD_DataComponentTypes.MCD_RARITY) == McdRarity.RARE) DungeonsHelpers.modifyAttackDamage(stack, super.baseAttackDamage + 5d);
        super.postDamageEntity(stack, target, attacker);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.claymore"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, stack);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.powerful_pushback"), true);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackDamagePair(@Nullable McdRarity mcdRarity) {
        if (mcdRarity == McdRarity.COMMON) return new Pair<>(9d, null);
        if (mcdRarity == McdRarity.RARE) return new Pair<>(10d, null);
        return null;
    }
}
