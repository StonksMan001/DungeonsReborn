package net.qbaesz13.dungeons_reborn.mixin_utils;

import net.minecraft.world.item.ItemStack;

public class ThreadLocalContainer {
    public static final ThreadLocal<ItemStack> STACK = new ThreadLocal<>();
    public static final ThreadLocal<Float> ATTACK_STRENGT_SCALE = new ThreadLocal<>();
}
