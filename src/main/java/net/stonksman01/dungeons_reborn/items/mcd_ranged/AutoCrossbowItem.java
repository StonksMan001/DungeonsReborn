package net.stonksman01.dungeons_reborn.items.mcd_ranged;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn._included_libs.skycore.items.SC_CrossbowItem;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.mixin.CrossbowItemAccessors;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;

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
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.auto_crossbow.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.auto_crossbow.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        tooltip.add(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
        tooltip.add(Text.translatable("enchantment.dungeons_reborn.accelerate").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN)));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }

}
