package net.qbaesz13.dungeons_reborn.mixin;

import net.minecraft.item.ItemStack;
import net.qbaesz13.dungeons_reborn.util.GrindStoneExperienceNotDropping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/screen/GrindstoneScreenHandler$4")
public class GrindStoneScreenHandlerMixin {
    @Inject(method = "getExperience(Lnet/minecraft/item/ItemStack;)I", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void test(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof GrindStoneExperienceNotDropping) cir.setReturnValue(0);
    }
}
