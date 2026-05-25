package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.qbaesz13.dungeons_reborn.mixin_utils.ThreadLocalContainer;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.util.AttackCooldownDependent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;postHit(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/LivingEntity;)Z"))
    private boolean accountForCooldownDependentItems0(ItemStack instance, LivingEntity target, LivingEntity user, Operation<Boolean> original, @Local(ordinal = 2) float h) {
        if (instance.getItem() instanceof AttackCooldownDependent item && h == 1.0) item.postChargedHit(instance, target, user);
        return original.call(instance, target, user);
    }
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;resetLastAttackedTicks()V"))
    private void accountForCooldownDependentItems1(Entity target, CallbackInfo ci, @Local(ordinal = 0) ItemStack itemStack, @Local(ordinal = 2) float h) {
        if (this.getEntityWorld() instanceof ServerWorld
                && target instanceof LivingEntity livingEntity
                && itemStack.getItem() instanceof AttackCooldownDependent item
                && h == 1.0) {
            item.postChargedAttack(itemStack, livingEntity, this);
        }
    }

    @WrapOperation(method = "takeShieldHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getWeaponDisableBlockingForSeconds()F"))
    private float disableShieldOnCriticalMaceHit0(LivingEntity instance, Operation<Float> original) {
        return isAttackCharged(instance) ? MaceBaseItem.DEFAULT_DISABLE_BLOCKING_TIME : original.call(instance);
    }

    @Definition(id = "f", local = @Local(type = float.class))
    @Expression("f > 0.0")
    @ModifyExpressionValue(method = "takeShieldHit", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean disableShieldOnCriticalMaceHit1(boolean original, @Local(argsOnly = true) LivingEntity attacker) {
        return original || isAttackCharged(attacker);
    }
    @Unique
    private boolean isAttackCharged(LivingEntity attacker) {
        ItemStack itemStack = attacker.getWeaponStack();
        return this.getEntityWorld() instanceof ServerWorld
                && Objects.nonNull(itemStack)
                && itemStack.getItem() instanceof MaceBaseItem
                && itemStack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 0) == 2;
    }
}
