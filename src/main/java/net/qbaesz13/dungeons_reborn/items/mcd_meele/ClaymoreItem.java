package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(textConsumer);
        DungeonsHelpers.Tooltip.appendDescription(textConsumer, Text.translatable("tooltip.dungeons_reborn.claymore"));
        DungeonsHelpers.Tooltip.appendMcdRarity(textConsumer, stack);
        DungeonsHelpers.Tooltip.appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.powerful_pushback"), true);
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
    @Override
    public Pair<@NotNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        if (mcdRarity == McdRarity.COMMON) return new Pair<>(9d, null);
        if (mcdRarity == McdRarity.RARE) return new Pair<>(10d, null);
        return null;
    }
}
