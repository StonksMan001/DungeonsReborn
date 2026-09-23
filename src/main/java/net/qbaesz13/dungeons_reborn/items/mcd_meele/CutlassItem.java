package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import net.qbaesz13.dungeons_reborn.util.GrindStoneExperienceNotDropping;

import java.util.List;

public class CutlassItem extends SkyCoreToolAPI.SwordItem implements GrindStoneExperienceNotDropping {
    float baseAttackDamage;
    public CutlassItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.cutlass"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, stack);
        DungeonsHelpers.Tooltip.appendBuiltInEnchantment(tooltip, Text.translatable("enchantment.minecraft.sweeping_edge"));
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        switch (stack.get(MCD_DataComponentTypes.MCD_RARITY)) {
            case RARE -> {
                DungeonsHelpers.addEnchantmentToStack(stack, world.getRegistryManager(), Enchantments.SWEEPING_EDGE, 3);
                DungeonsHelpers.modifyAttackDamage(stack, baseAttackDamage + 5d);
            }
            case COMMON -> {
                DungeonsHelpers.addEnchantmentToStack(stack, world.getRegistryManager(), Enchantments.SWEEPING_EDGE, 2);
                DungeonsHelpers.modifyAttackDamage(stack, baseAttackDamage + 4d);
            }
            case null, default -> {}
        }
        DungeonsHelpers.setRareOrCommonVariant(stack);
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
