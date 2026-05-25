package net.qbaesz13.dungeons_reborn.registries;

import com.google.common.base.CaseFormat;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.minecraft.world.GameRules;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.gamerules.CappedIntRule;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class MCD_GameRules {
    public static final Map<CappedIntRule, Integer> CAPPED_INT_RULES = new HashMap<>();
    public static final Map<GameRules.Key<GameRules.BooleanRule>, Boolean> BOOLEAN_RULES = new HashMap<>();

    public static final GameRules.Key<GameRules.BooleanRule> POP_FLOWERS_ALWAYS_HIDE = registerBooleanRule("popFlowersAlwaysHide", GameRules.Category.UPDATES, false);

    public static final CappedIntRule ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_DURATION = registerCappedIntRule("artifactDeathCapMushroomCommonDuration", GameRules.Category.MISC, 200, 0, null);
    public static final CappedIntRule ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_AMPLIFIER = registerCappedIntRule("artifactDeathCapMushroomCommonAmplifier", GameRules.Category.MISC, 0, 0, 255);
    public static final CappedIntRule ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_COOLDOWN = registerCappedIntRule("artifactDeathCapMushroomCommonCooldown", GameRules.Category.MISC, 600, 0, null);
    public static final CappedIntRule ARTIFACT_DEATH_CAP_MUSHROOM_RARE_DURATION = registerCappedIntRule("artifactDeathCapMushroomRareDuration", GameRules.Category.MISC, 300, 0, null);
    public static final CappedIntRule ARTIFACT_DEATH_CAP_MUSHROOM_RARE_AMPLIFIER = registerCappedIntRule("artifactDeathCapMushroomRareAmplifier", GameRules.Category.MISC, 1, 0, 255);
    public static final CappedIntRule ARTIFACT_DEATH_CAP_MUSHROOM_RARE_COOLDOWN = registerCappedIntRule("artifactDeathCapMushroomRareCooldown", GameRules.Category.MISC, 600, 0, null);

    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_COMMON_RANGE = registerCappedIntRule("artifactIronHideAmuletCommonRange", GameRules.Category.MISC, 10, 0, 100);
    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_COMMON_COOLDOWN = registerCappedIntRule("artifactIronHideAmuletCommonCooldown", GameRules.Category.MISC, 500, 0, null);
    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_COMMON_AMPLIFIER = registerCappedIntRule("artifactIronHideAmuletCommonAmplifier", GameRules.Category.MISC, 2, 0, 255);
    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_COMMON_DURATION = registerCappedIntRule("artifactIronHideAmuletCommonDuration", GameRules.Category.MISC, 200, 0, null);
    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_RARE_RANGE = registerCappedIntRule("artifactIronHideAmuletRareRange", GameRules.Category.MISC, 15, 0, 100);
    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_RARE_COOLDOWN = registerCappedIntRule("artifactIronHideAmuletRareCooldown", GameRules.Category.MISC, 500, 0, null);
    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_RARE_AMPLIFIER = registerCappedIntRule("artifactIronHideAmuletRareAmplifier", GameRules.Category.MISC, 2, 0, 255);
    public static final CappedIntRule ARTIFACT_IRON_HIDE_AMULET_RARE_DURATION = registerCappedIntRule("artifactIronHideAmuletRareDuration", GameRules.Category.MISC, 260, 0, null);

    public static final CappedIntRule RADIANCE_HEAL = registerCappedIntRule("radianceHeal", GameRules.Category.MISC, 4, null, null);
    public static final CappedIntRule RADIANCE_RANGE = registerCappedIntRule("radianceRange", GameRules.Category.MISC, 5, 0, 100);
    public static final CappedIntRule RADIANCE_TRIGGER_PROBABILITY = registerCappedIntRule("radianceTriggerProbability", GameRules.Category.MISC, 25, 0, 100);
    public static final CappedIntRule LEECHING_HP_STEAL_PERCENTAGE = registerCappedIntRule("leechingHpStealPercentage", GameRules.Category.MISC, 20, null, null);

    public static final CappedIntRule PROSPECTOR_MINIMUM_TRIGGER_PROBABILITY = registerCappedIntRule("prospectorMinimumTriggerProbability", GameRules.Category.MISC, 0, 0, 100);

    private static CappedIntRule registerCappedIntRule(String id, GameRules.Category category, int defaultValue, @Nullable Integer min, @Nullable Integer max) {
        CappedIntRule cappedIntRule = SkyCore.CustomRegistries.registerCappedIntRule(id, category, defaultValue, min, max);
        CAPPED_INT_RULES.put(cappedIntRule, defaultValue);
        return cappedIntRule;
    }

    private static GameRules.Key<GameRules.BooleanRule> registerBooleanRule(String id, GameRules.Category category, boolean defaultValue) {
        GameRules.Key<GameRules.BooleanRule> booleanRule = SkyCore.BuiltinRegistries.registerGameRule(id, category, GameRuleFactory.createBooleanRule(defaultValue));
        BOOLEAN_RULES.put(booleanRule, defaultValue);
        return booleanRule;
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
