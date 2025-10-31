package net.stonksman01.dungeons_reborn.items.mcd_meele;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class SteelMaceItem extends SkyCore.ToolAPI.SwordItem {
    float baseAttackDamage;
    float attackSpeed;
    public SteelMaceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.attackSpeed = attackSpeed;
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.STEEL_MACE_ATTACK_CHAIN, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.getEntityWorld().playSoundFromEntity(null, attacker, SoundEvents.ITEM_MACE_SMASH_GROUND, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) modifySpeedAndAttackDamage(stack, baseAttackDamage + 8d, attackSpeed -1d);
        else modifySpeedAndAttackDamage(stack, baseAttackDamage + 4d, attackSpeed);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        stack.set(MCD_DataComponentTypes.STEEL_MACE_ATTACK_CHAIN, next_chain_step);
    }

    private void modifySpeedAndAttackDamage(ItemStack stack, double attackDamage, double attackSpeed) {
        McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, mcdRarity == McdRarity.RARE ? attackDamage + 1d : attackDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .with(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.steel_mace.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.steel_mace.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        DungeonsHelpers.appendMcdRarity(stack, textConsumer);
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.powerful_combo").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withItalic(true)));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.setRareOrCommonVariant(stack);
        DungeonsHelpers.makeUnrepairable(stack);
    }
}
