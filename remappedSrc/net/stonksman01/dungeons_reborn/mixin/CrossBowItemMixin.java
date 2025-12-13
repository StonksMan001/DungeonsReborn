package net.stonksman01.dungeons_reborn.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.stonksman01.dungeons_reborn.items.mcd_ranged.AutoCrossbowItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public abstract class CrossBowItemMixin {
    @Inject(method = "getPullTime", at = @At(value = "RETURN", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void applyBonusIfAccelerateIsPresent(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir, float f) {
        if (stack.getItem() instanceof AutoCrossbowItem) {
            cir.setReturnValue((int)(MathHelper.floor(f * 20.0F) * stack.getOrDefault(MCD_DataComponentTypes.ACCELERATE_RELOAD_BONUS, 1.0f)));
            return;
        }
    }
}
