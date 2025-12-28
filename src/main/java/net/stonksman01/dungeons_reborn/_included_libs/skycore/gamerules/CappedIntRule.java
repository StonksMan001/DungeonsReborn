package net.stonksman01.dungeons_reborn._included_libs.skycore.gamerules;

import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record CappedIntRule(GameRules.Key<GameRules.IntRule> rule, @Nullable Integer min, @Nullable Integer max) {
    private static int nullSafeClamp(int value, @Nullable Integer min, @Nullable Integer max) {
        if (Objects.nonNull(min)) value = Math.max(value, min);
        if (Objects.nonNull(max)) value = Math.min(value, max);
        return value;
    }
    public int getValue(World world) {
        return nullSafeClamp(world.getGameRules().getInt(rule), min, max);
    }
}
