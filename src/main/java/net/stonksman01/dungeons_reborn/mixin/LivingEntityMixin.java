package net.stonksman01.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
