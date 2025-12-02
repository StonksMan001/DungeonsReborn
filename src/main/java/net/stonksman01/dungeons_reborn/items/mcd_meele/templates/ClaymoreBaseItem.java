package net.stonksman01.dungeons_reborn.items.mcd_meele.templates;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;

public abstract class ClaymoreBaseItem extends SkyCoreToolAPI.SwordItem {
    protected float baseAttackDamage;
    public ClaymoreBaseItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.getWorld().playSoundFromEntity(null, attacker, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) modifyKnockback(stack, 3.0);
        else modifyKnockback(stack, 0.0);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        stack.set(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, next_chain_step);
        super.postDamageEntity(stack, target, attacker);
    }
    protected void modifyKnockback(ItemStack stack, double knockback) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, knockback, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
    protected void modifyAttackDamage(ItemStack stack, double attackDamage) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        if (current_chain_step == 1) return McdItem.getMcdChargedItemBarColor();
        else return McdItem.getMcdItemBarColor();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }
}
