package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_CrossbowItem;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.mixin.accessors.CrossbowItemAccessors;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

import java.util.List;

public class AutoCrossbowItem extends SC_CrossbowItem {
    private static final float RELOAD_DECREASE_PERCENT = 0.09f;
    private static final float MIN_RELOAD_TIME = 0.10f;

    public AutoCrossbowItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        long currentTime = world.getTime();
        long lastShotTime = itemStack.getOrDefault(MCD_DataComponentTypes.ACCELERATE_LAST_SHOT_TIME, 0L);
        if (currentTime - lastShotTime > 50) itemStack.set(MCD_DataComponentTypes.ACCELERATE_RELOAD_BONUS, 1.0f);

        ChargedProjectilesComponent chargedProjectilesComponent = itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
            this.shootAll(world, user, hand, itemStack, CrossbowItemAccessors.getSpeed(chargedProjectilesComponent), 1.0F, null);
            itemStack.set(MCD_DataComponentTypes.ACCELERATE_LAST_SHOT_TIME, currentTime);
            itemStack.set(MCD_DataComponentTypes.ACCELERATE_RELOAD_BONUS, Math.max(MIN_RELOAD_TIME, itemStack.getOrDefault(MCD_DataComponentTypes.ACCELERATE_RELOAD_BONUS, 1.0f) - RELOAD_DECREASE_PERCENT));
            return TypedActionResult.consume(itemStack);
        } else if (!user.getProjectileType(itemStack).isEmpty()) {
            ((CrossbowItemAccessors) this).setCharged(false);
            ((CrossbowItemAccessors) this).setLoaded(false);
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.auto_crossbow"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.accelerate"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}
