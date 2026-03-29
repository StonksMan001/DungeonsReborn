package net.qbaesz13.dungeons_reborn.util;

import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullUnmarked;

public interface ChainAttackWeapon {
    @NullUnmarked
    default Pair<@NonNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        return null;
    }
    @NullUnmarked
    default Pair<@NonNull Double, Double> getAttackSpeedPair(McdRarity mcdRarity) {
        return null;
    }
    @NullUnmarked
    default Pair<@NonNull Double, Double> getAttackKnockbackPair(McdRarity mcdRarity) {
        return null;
    }
}
