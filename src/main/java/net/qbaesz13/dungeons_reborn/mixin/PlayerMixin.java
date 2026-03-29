package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.qbaesz13.dungeons_reborn.mixin_utils.ThreadLocalContainer;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.util.AttackCooldownDependent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }
    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;itemAttackInteraction(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/damagesource/DamageSource;Z)V"))
    private void accountForCooldownDependentItems0(Player instance, Entity entity, ItemStack attackingItemStack, DamageSource damageSource, boolean bl, Operation<Void> original, @Local(name = "magicBoost") float magicBoost) {
        if (attackingItemStack.getItem() instanceof AttackCooldownDependent) ThreadLocalContainer.MAGIC_BOOST.set(magicBoost);
        original.call(instance, entity, attackingItemStack, damageSource, bl);
    }
    @WrapOperation(method = "itemAttackInteraction", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtEnemy(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private boolean accountForCooldownDependentItems1(ItemStack instance, LivingEntity livingTarget, LivingEntity attacker, Operation<Boolean> original) {
        if (instance.getItem() instanceof AttackCooldownDependent item && ThreadLocalContainer.MAGIC_BOOST.get() == 1.0f) item.postChargedHit(instance, livingTarget, attacker);
        return original.call(instance, livingTarget, attacker);
    }
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;onAttack()V"))
    private void accountForCooldownDependentItems2(Entity target, CallbackInfo ci, @Local(name = "attackingItemStack") ItemStack attackingItemStack, @Local(name = "magicBoost") float magicBoost) {
        if (this.level() instanceof ServerLevel
                && target instanceof LivingEntity livingEntity
                && attackingItemStack.getItem() instanceof AttackCooldownDependent item
                && magicBoost == 1.0) {
            item.postChargedAttack(attackingItemStack, livingEntity, this);
        }
    }

    @WrapOperation(method = "blockUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getSecondsToDisableBlocking()F"))
    private float disableShieldOnCriticalMaceHit0(LivingEntity instance, Operation<Float> original) {
        return isAttackCharged(instance) ? MaceBaseItem.DEFAULT_DISABLE_BLOCKING_TIME : original.call(instance);
    }

    @Definition(id = "f", local = @Local(type = float.class))
    @Expression("f > 0.0")
    @ModifyExpressionValue(method = "blockUsingItem", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean disableShieldOnCriticalMaceHit1(boolean original, @Local(argsOnly = true) LivingEntity attacker) {
        return original || isAttackCharged(attacker);
    }
    @Unique
    private boolean isAttackCharged(LivingEntity attacker) {
        ItemStack itemStack = attacker.getWeaponItem();
        return this.level() instanceof ServerLevel
                && Objects.nonNull(itemStack)
                && itemStack.getItem() instanceof MaceBaseItem
                && itemStack.getOrDefault(MCD_DataComponents.ATTACK_CHAIN_STEP, 0) == 2;
    }
}
