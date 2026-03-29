package net.qbaesz13.dungeons_reborn.items.mcd_meele.templates;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.util.AttackCooldownDependent;
import net.qbaesz13.dungeons_reborn.util.ChainAttackWeapon;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;

public abstract class MaceBaseItem extends SkyCoreToolAPI.SwordItem implements ChainAttackWeapon, AttackCooldownDependent {
    public static final float DEFAULT_DISABLE_BLOCKING_TIME = 5.0f;
    float baseAttackDamage;
    float attackSpeed;
    public MaceBaseItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.attackSpeed = attackSpeed;
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override @NullMarked
    public void postChargedAttack(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = itemStack.getOrDefault(MCD_DataComponents.ATTACK_CHAIN_STEP, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.level().playSound(null, attacker, SoundEvents.MACE_SMASH_GROUND, SoundSource.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) modifySpeedAndAttackDamage(itemStack, baseAttackDamage + 8d, attackSpeed - 5d);
        else modifySpeedAndAttackDamage(itemStack, baseAttackDamage + 4d, attackSpeed - 4d);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        itemStack.set(MCD_DataComponents.ATTACK_CHAIN_STEP, next_chain_step);
    }
    private void modifySpeedAndAttackDamage(ItemStack stack, double attackDamage, double attackSpeed) {
        McdRarity mcdRarity = stack.get(MCD_DataComponents.MCD_RARITY);
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY)
                .withModifierAdded(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, mcdRarity == McdRarity.RARE ? attackDamage + 1d : attackDamage, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .withModifierAdded(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                ));
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponents.ATTACK_CHAIN_STEP, 1);
        if (current_chain_step == 1) return McdItem.getMcdChargedItemBarColor();
        else return McdItem.getMcdItemBarColor();
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.makeUnrepairable(itemStack);
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override @NullUnmarked
    public abstract Tuple<@NonNull Double, Double> getAttackDamagePair(McdRarity mcdRarity);
    @Override @NullUnmarked
    public abstract Tuple<@NonNull Double, Double> getAttackSpeedPair(McdRarity mcdRarity);
}
