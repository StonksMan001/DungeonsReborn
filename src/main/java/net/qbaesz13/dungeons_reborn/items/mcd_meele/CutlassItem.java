package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantments;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import net.qbaesz13.dungeons_reborn.util.GrindStoneExperienceNotDropping;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class CutlassItem extends SkyCoreToolAPI.SwordItem implements GrindStoneExperienceNotDropping {
    float baseAttackDamage;
    public CutlassItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, baseAttackDamage, attackSpeed, properties);
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.cutlass"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, itemStack);
        DungeonsHelpers.Tooltip.appendBuiltInEnchantment(builder, Component.translatable("enchantment.minecraft.sweeping_edge"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        switch (itemStack.get(MCD_DataComponents.MCD_RARITY)) {
            case RARE -> {
                DungeonsHelpers.addEnchantmentToStack(itemStack, level.registryAccess(), Enchantments.SWEEPING_EDGE, 3);
                DungeonsHelpers.modifyAttackDamage(itemStack, baseAttackDamage + 5d);
            }
            case COMMON -> {
                DungeonsHelpers.addEnchantmentToStack(itemStack, level.registryAccess(), Enchantments.SWEEPING_EDGE, 2);
                DungeonsHelpers.modifyAttackDamage(itemStack, baseAttackDamage + 4d);
            }
            case null, default -> {}
        }
        DungeonsHelpers.setRareOrCommonVariant(itemStack);
        DungeonsHelpers.makeUnrepairable(itemStack);
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}
