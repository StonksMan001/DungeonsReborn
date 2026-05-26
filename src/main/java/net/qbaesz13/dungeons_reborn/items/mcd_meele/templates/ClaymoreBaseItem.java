package net.qbaesz13.dungeons_reborn.items.mcd_meele.templates;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.util.AttackCooldownDependent;
import net.qbaesz13.dungeons_reborn.util.ChainAttackWeapon;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;

public abstract class ClaymoreBaseItem extends SkyCoreToolAPI.SwordItem implements ChainAttackWeapon, AttackCooldownDependent {
    protected float baseAttackDamage;
    public ClaymoreBaseItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override
    public void postChargedAttack(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.getEntityWorld().playSoundFromEntity(null, attacker, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) DungeonsHelpers.modifyAttackKnockback(stack, 3.0);
        else DungeonsHelpers.modifyAttackKnockback(stack, 0.0);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        stack.set(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, next_chain_step);
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.setAttackKnockbackModifierIfNotPresent(stack);
        if (stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS) == null) DungeonsHelpers.modifyAttackKnockback(stack, 0.0);
        DungeonsHelpers.makeUnrepairable(stack);
        super.inventoryTick(stack, world, entity, slot);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        if (current_chain_step == 1) return McdItem.getMcdChargedItemBarColor();
        else return McdItem.getMcdItemBarColor();
    }
    @Override @NullUnmarked
    public Pair<@NonNull Double, Double> getAttackKnockbackPair(McdRarity mcdRarity) {
        return new Pair<>(4d, 7d);
    }
}
