package net.qbaesz13.dungeons_reborn.util;

import net.minecraft.util.Tuple;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullUnmarked;

public interface ChainAttackWeapon {
    @NullUnmarked
    default Tuple<@NonNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        return null;
    }
    @NullUnmarked
    default Tuple<@NonNull Double, Double> getAttackSpeedPair(McdRarity mcdRarity) {
        return null;
    }
    @NullUnmarked
    default Tuple<@NonNull Double, Double> getAttackKnockbackPair(McdRarity mcdRarity) {
        return null;
    }
}
