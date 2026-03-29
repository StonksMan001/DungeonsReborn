package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;

import java.util.function.Consumer;

public class SteelMaceItem extends MaceBaseItem {
    public SteelMaceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, baseAttackDamage, attackSpeed, properties);
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.steel_mace"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, itemStack);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.powerful_combo"), true);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @org.jspecify.annotations.Nullable EquipmentSlot slot) {
        DungeonsHelpers.setRareOrCommonVariant(itemStack);
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override @NullUnmarked
    public Tuple<@NonNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        if (mcdRarity == McdRarity.COMMON) return new Tuple<>(7d, 11d);
        if (mcdRarity == McdRarity.RARE) return new Tuple<>(8d, 12d);
        return null;
    }
    @Override @NullUnmarked
    public Tuple<@NonNull Double, Double> getAttackSpeedPair(McdRarity mcdRarity) {
        if (mcdRarity == McdRarity.COMMON || mcdRarity == McdRarity.RARE) return new Tuple<>(1.6d, 0.6d);
        return null;
    }
}
