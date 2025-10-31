package net.stonksman01.dungeons_reborn.mixin;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {
    //Deprecated since 1.21.1
    /*@WrapOperation(method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean iItemTagCheck(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }*/
}
