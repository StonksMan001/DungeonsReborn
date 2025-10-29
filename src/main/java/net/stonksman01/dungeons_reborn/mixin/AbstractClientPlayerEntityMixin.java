package net.stonksman01.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
    @WrapOperation(method = "getFovMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    public boolean customBowFovMultipliers(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
}
