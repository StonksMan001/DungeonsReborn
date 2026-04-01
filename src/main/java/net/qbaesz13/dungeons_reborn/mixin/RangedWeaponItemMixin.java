package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.HeavyCrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponItemMixin {
    @WrapOperation(method = "shootAll", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/RangedWeaponItem;createArrowEntity(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/projectile/ProjectileEntity;"))
    private ProjectileEntity foo(RangedWeaponItem instance, World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical, Operation<ProjectileEntity> original) {
        if (((Item)(Object)this) instanceof HeavyCrossbowItem) {
            ProjectileEntity projectile = original.call(instance, world, shooter, weaponStack, projectileStack, critical);
            if (projectile instanceof PersistentProjectileEntity persistentProjectileEntity) {
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() * 1.75);
                return persistentProjectileEntity;
            }
        }
        return original.call(instance, world, shooter, weaponStack, projectileStack, critical);
    }
}