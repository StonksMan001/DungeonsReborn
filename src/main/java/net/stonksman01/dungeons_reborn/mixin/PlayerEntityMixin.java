package net.stonksman01.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.stonksman01.dungeons_reborn.mixin_utils.ThreadLocalContainer;
import net.stonksman01.dungeons_reborn.util.AttackCooldownDependent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;onTargetDamaged(Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/damage/DamageSource;Z)V"))
    private void accountForCooldownDependentItems0(PlayerEntity instance, Entity target, ItemStack stack, DamageSource damageSource, boolean runEnchantmentEffects, Operation<Void> original, @Local(ordinal = 2) float h) {
        if (stack.getItem() instanceof AttackCooldownDependent) ThreadLocalContainer.H.set(h);
        original.call(instance, target, stack, damageSource, runEnchantmentEffects);
    }
    @WrapOperation(method = "onTargetDamaged", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;postHit(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/LivingEntity;)Z"))
    private boolean accountForCooldownDependentItems1(ItemStack instance, LivingEntity target, LivingEntity user, Operation<Boolean> original) {
        if (instance.getItem() instanceof AttackCooldownDependent item && ThreadLocalContainer.H.get() == 1.0f) item.postChargedHit(instance, target, user);
        return original.call(instance, target, user);
    }
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;beforePlayerAttack()V"))
    private void accountForCooldownDependentItems2(Entity target, CallbackInfo ci, @Local ItemStack itemStack, @Local(ordinal = 2) float h) {
        if (((PlayerEntity)(Object) this).getEntityWorld() instanceof ServerWorld serverWorld
                && target instanceof LivingEntity livingEntity
                && itemStack.getItem() instanceof AttackCooldownDependent item
                && h == 1.0) {
            item.postChargedAttack(itemStack, livingEntity, (PlayerEntity)(Object) this);
        }
    }
}
