package net.stonksman01.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @WrapOperation(method = "getHandRenderType", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean iItemTagCheck1(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
    @WrapOperation(method = "getHandRenderType", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean itemTagCheck2(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
    @WrapOperation(method = "getHandRenderType", at = @At(value = "INVOKE", ordinal = 2, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean itemTagCheck3(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
    @WrapOperation(method = "getHandRenderType", at = @At(value = "INVOKE", ordinal = 3, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean itemTagCheck4(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
    @WrapOperation(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean itemTagCheck5(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
    @WrapOperation(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean itemTagCheck6(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
    @WrapOperation(method = "isChargedCrossbow", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean itemTagCheck7(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
    @WrapOperation(method = "renderFirstPersonItem", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean itemTagCheck8(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
}
