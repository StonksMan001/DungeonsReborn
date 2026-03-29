package net.qbaesz13.dungeons_reborn.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @WrapOperation(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean iItemTagCheck1(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
    @WrapOperation(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean itemTagCheck2(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
    @WrapOperation(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", ordinal = 2, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean itemTagCheck3(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
    @WrapOperation(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", ordinal = 3, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean itemTagCheck4(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
    @WrapOperation(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean itemTagCheck5(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
    @WrapOperation(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean itemTagCheck6(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
    @WrapOperation(method = "isChargedCrossbow", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean itemTagCheck7(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
    @WrapOperation(method = "renderArmWithItem", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean itemTagCheck8(ItemStack instance, Object o, Operation<Boolean> original) {
        return SkyCore.wrapRangedWeaponHardcodedCallsIfPresent(instance, (Item) o, original);
    }
}
