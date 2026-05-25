package net.qbaesz13.dungeons_reborn._included_libs.skycore.gamerules;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;

public record CappedIntRule(GameRules.Key<GameRules.IntRule> rule, @Nullable Integer min, @Nullable Integer max) {
    private static int nullSafeClamp(int value, @Nullable Integer min, @Nullable Integer max) {
        if (min != null) value = Math.max(value, min);
        if (max != null) value = Math.min(value, max);
        return value;
    }
    public int getValue(ServerWorld serverWorld) {
        return nullSafeClamp(serverWorld.getGameRules().getInt(rule), min, max);
    }
}
