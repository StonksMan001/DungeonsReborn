package net.qbaesz13.dungeons_reborn.util;

import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import org.jetbrains.annotations.NotNull;

public interface ChainAttackWeapon {
    default Pair<@NotNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        return null;
    }
    default Pair<@NotNull Double, Double> getAttackSpeedPair(McdRarity mcdRarity) {
        return null;
    }
    default Pair<@NotNull Double, Double> getAttackKnockbackPair(McdRarity mcdRarity) {
        return null;
    }
}
