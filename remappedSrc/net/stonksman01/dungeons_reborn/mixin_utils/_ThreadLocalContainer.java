package net.stonksman01.dungeons_reborn.mixin_utils;

import net.minecraft.item.ItemStack;

public class _ThreadLocalContainer {
    public static final ThreadLocal<ItemStack> STACK = new ThreadLocal<>();
}
