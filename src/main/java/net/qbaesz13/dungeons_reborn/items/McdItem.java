package net.qbaesz13.dungeons_reborn.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class McdItem extends Item {
    private static final int ITEM_BAR_COLOR = 65535;
    private static final int CHARGED_ITEM_BAR_COLOR = 16745472;
    public McdItem(Properties properties) {
        super(properties);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return ITEM_BAR_COLOR;
    }
    public static int getMcdItemBarColor() {
        return ITEM_BAR_COLOR;
    }
    public static int getMcdChargedItemBarColor() {
        return CHARGED_ITEM_BAR_COLOR;
    }
}