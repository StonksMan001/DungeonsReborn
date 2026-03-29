package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import org.jspecify.annotations.NonNull;

public class ShortBowItem extends SC_BowItem {
    public ShortBowItem(Properties properties) {
        super(properties);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}

