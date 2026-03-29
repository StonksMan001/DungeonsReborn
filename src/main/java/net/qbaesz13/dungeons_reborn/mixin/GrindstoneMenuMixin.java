package net.qbaesz13.dungeons_reborn.mixin;

import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn.util.GrindStoneExperienceNotDropping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/world/inventory/GrindstoneMenu$4")
public class GrindstoneMenuMixin {
    @Inject(method = "getExperienceFromItem", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void fn(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (itemStack.getItem() instanceof GrindStoneExperienceNotDropping) cir.setReturnValue(0);
    }
}
