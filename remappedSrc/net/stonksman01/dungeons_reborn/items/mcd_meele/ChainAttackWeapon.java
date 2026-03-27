package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ChainAttackWeapon {
    default @Nullable Pair<@NotNull Double, @Nullable Double> getAttackDamagePair(@Nullable McdRarity mcdRarity) {
        return null;
    }
    default @Nullable Pair<@NotNull Double, @Nullable Double> getAttackSpeedPair(@Nullable McdRarity mcdRarity) {
        return null;
    }
    default @Nullable Pair<@NotNull Double, @Nullable Double> getAttackKnockbackPair(@Nullable McdRarity mcdRarity) {
        return null;
    }
}
