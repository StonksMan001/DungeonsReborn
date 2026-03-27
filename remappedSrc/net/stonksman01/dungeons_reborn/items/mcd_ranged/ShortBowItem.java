/*
 * Decompiled with CFR 0.2.0 (FabricMC d28b102d).
 */
package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.qbaesz13.dungeons_reborn.items.McdItem;

public class ShortBowItem extends SC_BowItem {
    public ShortBowItem(net.minecraft.item.Item.Settings settings) {
        super(settings);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}

