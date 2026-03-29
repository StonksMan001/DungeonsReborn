package net.qbaesz13.dungeons_reborn.items.mcd_meele.templates;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
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

public abstract class ClaymoreBaseItem extends SkyCoreToolAPI.SwordItem implements ChainAttackWeapon, AttackCooldownDependent {
    protected float baseAttackDamage;
    public ClaymoreBaseItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, baseAttackDamage, attackSpeed, properties);
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override @NullMarked
    public void postChargedAttack(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = itemStack.getOrDefault(MCD_DataComponents.ATTACK_CHAIN_STEP, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.level().playSound(null, attacker, SoundEvents.TRIDENT_RETURN, SoundSource.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) DungeonsHelpers.modifyAttackKnockback(itemStack, 3.0);
        else DungeonsHelpers.modifyAttackKnockback(itemStack, 0.0);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        itemStack.set(MCD_DataComponents.ATTACK_CHAIN_STEP, next_chain_step);
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.makeUnrepairable(itemStack);
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponents.ATTACK_CHAIN_STEP, 1);
        if (current_chain_step == 1) return McdItem.getMcdChargedItemBarColor();
        else return McdItem.getMcdItemBarColor();
    }
    @Override @NullUnmarked
    public Tuple<@NonNull Double, Double> getAttackKnockbackPair(McdRarity mcdRarity) {
        return new Tuple<>(4d, 7d);
    }
}
