package net.qbaesz13.dungeons_reborn.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {
    @WrapOperation(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    public boolean customBowFovMultipliers(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item)o, original);
    }
}
