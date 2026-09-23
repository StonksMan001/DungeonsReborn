package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.mixin.accessors.AbstractArrowAccessors;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class ShortbowItem extends SC_BowItem {
    public ShortbowItem(Properties properties) {
        super(properties);
        this.pullTime = super.pullTime * (1.0f / 1.3f);
    }
    @Override
    protected void shootProjectile(@NonNull LivingEntity shooter, Projectile projectileEntity, int index, float power, float uncertainty, float angle, @Nullable LivingEntity targetOverrride) {
        projectileEntity.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + angle, 0.0F, power * (1.0f / 1.3f), uncertainty);
        ((AbstractArrow)projectileEntity).setBaseDamage(((AbstractArrowAccessors)projectileEntity).getBaseDamage() * (1.0f / 1.3f));
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.shortbow"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, McdRarity.COMMON);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}

