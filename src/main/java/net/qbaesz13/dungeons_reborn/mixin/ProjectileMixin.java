package net.qbaesz13.dungeons_reborn.mixin;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.TwinBowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Projectile.class)
public abstract class ProjectileMixin {
    @Inject(method = "onDeflection", at = @At(value = "HEAD"))
    public void discardIfWeaponIsTwinBow(boolean bl, CallbackInfo ci) {
        if ((Object)this instanceof AbstractArrow abstractArrow) {
            ItemStack weaponStack = abstractArrow.getWeaponItem();
            if (Objects.nonNull(weaponStack) && weaponStack.getItem() instanceof TwinBowItem) {
                abstractArrow.discard();
            }
        }
    }
}
