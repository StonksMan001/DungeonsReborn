package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class ClaymoreItem extends ClaymoreBaseItem {
    public ClaymoreItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, baseAttackDamage, attackSpeed, properties);
    }
    @Override @NullMarked
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        if (itemStack.get(MCD_DataComponents.MCD_RARITY) == McdRarity.RARE) DungeonsHelpers.modifyAttackDamage(itemStack, super.baseAttackDamage + 5d);
        super.postHurtEnemy(itemStack, target, attacker);
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.claymore"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, itemStack);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.powerful_pushback"), true);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.setRareOrCommonVariant(itemStack);
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override @NullUnmarked
    public Tuple<@NonNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        if (mcdRarity == McdRarity.COMMON) return new Tuple<>(9d, null);
        if (mcdRarity == McdRarity.RARE) return new Tuple<>(10d, null);
        return null;
    }
}
