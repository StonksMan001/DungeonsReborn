package net.stonksman01.dungeons_reborn.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface AttackCooldownDependent {
    default void postChargedHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {}
    default void postChargedAttack(ItemStack stack, LivingEntity target, LivingEntity attacker) {}
}
