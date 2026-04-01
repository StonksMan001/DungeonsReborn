package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import net.qbaesz13.dungeons_reborn.util.GrindStoneExperienceNotDropping;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BroadswordItem extends ClaymoreBaseItem implements GrindStoneExperienceNotDropping {
    public BroadswordItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.broadsword"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.powerful_pushback"), true);
        DungeonsHelpers.Tooltip.appendBuiltInEnchantment(tooltip, Text.translatable("enchantment.minecraft.sharpness"));
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        DungeonsHelpers.addEnchantmentToStack(stack, world.getRegistryManager(), Enchantments.SHARPNESS, 5);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackDamagePair(@Nullable McdRarity mcdRarity) {
        return new Pair<>(10d, null);
    }
}
