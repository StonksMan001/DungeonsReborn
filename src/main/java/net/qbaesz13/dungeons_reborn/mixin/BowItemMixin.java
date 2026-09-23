package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.BowItem;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BowItem.class)
public abstract class BowItemMixin {
    @WrapOperation(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"))
    private float getPullTimeOfSkyCoreBows(int useTicks, Operation<Float> original) {
        if (((Object)this) instanceof SC_BowItem scBowItem) return scBowItem.getCustomPullProgress(useTicks);
        return original.call(useTicks);
    }
}
