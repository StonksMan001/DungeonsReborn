package net.stonksman01.dungeons_reborn.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class McdItem extends Item {
    private static final int ITEM_BAR_COLOR = 65535;
    private static final int CHARGED_ITEM_BAR_COLOR = 16745472;
    public McdItem(Settings settings) {
        super(settings);
    }
    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }
    public static int getMcdItemBarColor() {
        return ITEM_BAR_COLOR;
    }
    public static int getMcdChargedItemBarColor() {
        return CHARGED_ITEM_BAR_COLOR;
    }
}