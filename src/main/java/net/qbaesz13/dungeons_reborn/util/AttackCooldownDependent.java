package net.qbaesz13.dungeons_reborn.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface AttackCooldownDependent {
    default void postChargedHit(final ItemStack itemStack, final LivingEntity target, final LivingEntity attacker) {}
    default void postChargedAttack(final ItemStack itemStack, final LivingEntity target, final LivingEntity attacker) {}
}
