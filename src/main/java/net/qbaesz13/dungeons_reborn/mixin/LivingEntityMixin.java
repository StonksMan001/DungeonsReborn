package net.qbaesz13.dungeons_reborn.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract @NotNull ItemStack getWeaponStack();

    //TODO
    /*
    @Inject(method = "disablesShield", at = @At(value = "HEAD"), cancellable = true)
    private void disableShieldOnCiriticalMaceHit(CallbackInfoReturnable<Boolean> cir) {
        if (this.getWeaponStack().getItem() instanceof MaceBaseItem && this.getWeaponStack().getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 0) == 2) {
            cir.setReturnValue(true);
        }
    }
    */
}
