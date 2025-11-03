package net.stonksman01.dungeons_reborn.items.mcd_meele.template;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

public abstract class ClaymoreTemplateItem extends SkyCore.ToolAPI.SwordItem {
    protected float baseAttackDamage;
    public ClaymoreTemplateItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.CLAYMORE_ATTACK_CHAIN, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.getEntityWorld().playSoundFromEntity(null, attacker, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) modifyKnockback(stack, 3.0);
        else modifyKnockback(stack, 0.0);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        stack.set(MCD_DataComponentTypes.CLAYMORE_ATTACK_CHAIN, next_chain_step);
        super.postHit(stack, target, attacker);
    }
    protected void modifyKnockback(ItemStack stack, double knockback) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, knockback, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
    protected void modifyAttackDamage(ItemStack stack, double attackDamage) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.CLAYMORE_ATTACK_CHAIN, 1);
        if (current_chain_step == 1) return McdItem.getMcdChargedItemBarColor();
        else return McdItem.getMcdItemBarColor();
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.makeUnrepairable(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
}
