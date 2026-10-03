package net.qbaesz13.dungeons_reborn.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FirstPersonHandsAndItemsRenderer.class)
public abstract class FirstPersonHandsAndItemsRendererMixin {
    @WrapOperation(method = "submitArmWithItem", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean itemTagCheck(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
}
