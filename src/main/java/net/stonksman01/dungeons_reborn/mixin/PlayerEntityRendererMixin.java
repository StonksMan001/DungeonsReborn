package net.stonksman01.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {
    @WrapOperation(method = "getArmPose(Lnet/minecraft/entity/PlayerLikeEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean iItemTagCheck(ItemStack instance, Item item, Operation<Boolean> original) {
        return DungeonsHelpers.wrapRangedWeaponHardcodedCallsIfPresent(instance, item, original);
    }
}
