package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_CrossbowItem;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.mixin.accessors.CrossbowItemAccessor;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.function.Consumer;

public class AutoCrossbowItem extends SC_CrossbowItem {
    private static final float RELOAD_DECREASE_PERCENT = 0.09f;
    private static final float MIN_RELOAD_TIME = 0.10f;

    public AutoCrossbowItem(Properties properties) {
        super(properties);
    }
    @Override @NullMarked
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);

        long currentTime = level.getGameTime();
        long lastShotTime = itemStack.getOrDefault(MCD_DataComponents.ACCELERATE_LAST_SHOT_TIME, 0L);
        if (currentTime - lastShotTime > 50) itemStack.set(MCD_DataComponents.ACCELERATE_RELOAD_BONUS, 1.0f);

        ChargedProjectiles chargedProjectilesComponent = itemStack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
            this.performShooting(level, user, hand, itemStack, CrossbowItemAccessor.getShootingPower(chargedProjectilesComponent), 1.0F, null);
            itemStack.set(MCD_DataComponents.ACCELERATE_LAST_SHOT_TIME, currentTime);
            itemStack.set(MCD_DataComponents.ACCELERATE_RELOAD_BONUS, Math.max(MIN_RELOAD_TIME, itemStack.getOrDefault(MCD_DataComponents.ACCELERATE_RELOAD_BONUS, 1.0f) - RELOAD_DECREASE_PERCENT));
            return InteractionResult.CONSUME;
        } else if (!user.getProjectile(itemStack).isEmpty()) {
            ((CrossbowItemAccessor) this).setStartSoundPlayed(false);
            ((CrossbowItemAccessor) this).setMidLoadSoundPlayed(false);
            user.startUsingItem(hand);
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.FAIL;
        }
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.auto_crossbow"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.accelerate"), false);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}
