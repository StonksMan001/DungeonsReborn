package net.stonksman01.dungeons_reborn.items.mcd_meele.templates;

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
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.items.mcd_meele.ChainAttackWeapon;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

public abstract class MaceBaseItem extends SkyCoreToolAPI.SwordItem implements ChainAttackWeapon {
    float baseAttackDamage;
    float attackSpeed;
    public MaceBaseItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.attackSpeed = attackSpeed;
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.getEntityWorld().playSoundFromEntity(null, attacker, SoundEvents.ITEM_MACE_SMASH_GROUND, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) modifySpeedAndAttackDamage(stack, baseAttackDamage + 8d, attackSpeed -1d);
        else modifySpeedAndAttackDamage(stack, baseAttackDamage + 4d, attackSpeed);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        stack.set(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, next_chain_step);
        super.postHit(stack, target, attacker);
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
    public int getItemBarColor(ItemStack stack) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        if (current_chain_step == 1) return McdItem.getMcdChargedItemBarColor();
        else return McdItem.getMcdItemBarColor();
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.makeUnrepairable(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
}
