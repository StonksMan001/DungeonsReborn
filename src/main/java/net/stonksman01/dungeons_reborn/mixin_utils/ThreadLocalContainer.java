package net.stonksman01.dungeons_reborn.mixin_utils;

import net.minecraft.item.ItemStack;

public class ThreadLocalContainer {
    public static final ThreadLocal<ItemStack> STACK = new ThreadLocal<>();
    public static final ThreadLocal<Float> H = new ThreadLocal<>();
}
