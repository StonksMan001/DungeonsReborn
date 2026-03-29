package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.HeavyCrossbowItem;
import net.qbaesz13.dungeons_reborn.mixin.accessors.AbstractArrowAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ProjectileWeaponItem.class)
public abstract class ProjectileWeaponItemMixin {
    @WrapOperation(method = "shoot", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ProjectileWeaponItem;createProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/projectile/Projectile;"))
    private Projectile fn(ProjectileWeaponItem instance, Level level, LivingEntity shooter, ItemStack weapon, ItemStack projectile, boolean isCrit, Operation<Projectile> original) {
        Projectile projectile1 = original.call(instance, level, shooter, weapon, projectile, isCrit);
        if (((Item)(Object)this) instanceof HeavyCrossbowItem) {
            if (projectile1 instanceof AbstractArrow abstractArrow) {
                abstractArrow.setBaseDamage(((AbstractArrowAccessor)abstractArrow).getBaseDamage() * 1.75);
                return abstractArrow;
            }
        }
        return projectile1;
    }
}