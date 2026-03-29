package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.AutoCrossbowItem;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.HeavyCrossbowItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(CrossbowItem.class)
public abstract class CrossBowItemMixin {
    @WrapOperation(method = "getChargeDuration", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;floor(F)I"))
    private static int modifyPullTime(float value, Operation<Integer> original, @Local(argsOnly = true) ItemStack itemStack) {
        if (itemStack.getItem() instanceof AutoCrossbowItem) {
            return original.call(value * itemStack.getOrDefault(MCD_DataComponents.ACCELERATE_RELOAD_BONUS, 1.0f));
        }
        if (itemStack.getItem() instanceof HeavyCrossbowItem) {
            McdRarity rarity = itemStack.get(MCD_DataComponents.MCD_RARITY);
            float coefficient = Objects.isNull(rarity) || rarity == McdRarity.COMMON ? 2.25f : 2.0f;
            return original.call(value * coefficient);
        }
        return original.call(value);
    }
}
