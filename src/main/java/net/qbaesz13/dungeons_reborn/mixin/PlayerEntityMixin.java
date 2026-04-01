package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.qbaesz13.dungeons_reborn.util.AttackCooldownDependent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;postHit(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/player/PlayerEntity;)Z"))
    private boolean accountForCooldownDependentItems0(ItemStack instance, LivingEntity target, PlayerEntity player, Operation<Boolean> original, @Local(ordinal = 2) float h) {
        if (instance.getItem() instanceof AttackCooldownDependent item && h == 1.0) item.postChargedHit(instance, target, player);
        return original.call(instance, target, player);
    }
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;resetLastAttackedTicks()V"))
    private void accountForCooldownDependentItems1(Entity target, CallbackInfo ci, @Local ItemStack itemStack, @Local(ordinal = 2) float h) {
        if (((PlayerEntity)(Object) this).getWorld() instanceof ServerWorld serverWorld
                && target instanceof LivingEntity livingEntity
                && itemStack.getItem() instanceof AttackCooldownDependent item
                && h == 1.0) {
            item.postChargedAttack(itemStack, livingEntity, (PlayerEntity)(Object) this);
        }
    }
}
