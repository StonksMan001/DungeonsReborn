package net.qbaesz13.dungeons_reborn.registries;

import com.google.common.base.CaseFormat;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import org.jspecify.annotations.Nullable;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class MCD_GameRules {
    public static final ArrayList<GameRule<?>> DR_GAMERULES = new ArrayList<>();

    public static final GameRule<Boolean> POP_FLOWERS_ALWAYS_HIDE = registerBooleanRule("popFlowersAlwaysHide", GameRuleCategory.UPDATES, false);

    public static final GameRule<Integer> ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_DURATION = registerCappedIntRule("artifactDeathCapMushroomCommonDuration", GameRuleCategory.MISC, 200, 0, null);
    public static final GameRule<Integer> ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_AMPLIFIER = registerCappedIntRule("artifactDeathCapMushroomCommonAmplifier", GameRuleCategory.MISC, 0, 0, 255);
    public static final GameRule<Integer> ARTIFACT_DEATH_CAP_MUSHROOM_COMMON_COOLDOWN = registerCappedIntRule("artifactDeathCapMushroomCommonCooldown", GameRuleCategory.MISC, 600, 0, null);
    public static final GameRule<Integer> ARTIFACT_DEATH_CAP_MUSHROOM_RARE_DURATION = registerCappedIntRule("artifactDeathCapMushroomRareDuration", GameRuleCategory.MISC, 300, 0, null);
    public static final GameRule<Integer> ARTIFACT_DEATH_CAP_MUSHROOM_RARE_AMPLIFIER = registerCappedIntRule("artifactDeathCapMushroomRareAmplifier", GameRuleCategory.MISC, 1, 0, 255);
    public static final GameRule<Integer> ARTIFACT_DEATH_CAP_MUSHROOM_RARE_COOLDOWN = registerCappedIntRule("artifactDeathCapMushroomRareCooldown", GameRuleCategory.MISC, 600, 0, null);

    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_COMMON_RANGE = registerCappedIntRule("artifactIronHideAmuletCommonRange", GameRuleCategory.MISC, 10, 0, 100);
    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_COMMON_COOLDOWN = registerCappedIntRule("artifactIronHideAmuletCommonCooldown", GameRuleCategory.MISC, 500, 0, null);
    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_COMMON_AMPLIFIER = registerCappedIntRule("artifactIronHideAmuletCommonAmplifier", GameRuleCategory.MISC, 2, 0, 255);
    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_COMMON_DURATION = registerCappedIntRule("artifactIronHideAmuletCommonDuration", GameRuleCategory.MISC, 200, 0, null);
    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_RARE_RANGE = registerCappedIntRule("artifactIronHideAmuletRareRange", GameRuleCategory.MISC, 15, 0, 100);
    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_RARE_COOLDOWN = registerCappedIntRule("artifactIronHideAmuletRareCooldown", GameRuleCategory.MISC, 500, 0, null);
    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_RARE_AMPLIFIER = registerCappedIntRule("artifactIronHideAmuletRareAmplifier", GameRuleCategory.MISC, 2, 0, 255);
    public static final GameRule<Integer> ARTIFACT_IRON_HIDE_AMULET_RARE_DURATION = registerCappedIntRule("artifactIronHideAmuletRareDuration", GameRuleCategory.MISC, 260, 0, null);

    public static final GameRule<Integer> RADIANCE_HEAL = registerCappedIntRule("radianceHeal", GameRuleCategory.MISC, 4, null, null);
    public static final GameRule<Integer> RADIANCE_RANGE = registerCappedIntRule("radianceRange", GameRuleCategory.MISC, 5, 0, 100);
    public static final GameRule<Integer> RADIANCE_TRIGGER_PROBABILITY = registerCappedIntRule("radianceTriggerProbability", GameRuleCategory.MISC, 25, 0, 100);
    public static final GameRule<Integer> PROSPECTOR_MINIMUM_TRIGGER_PROBABILITY = registerCappedIntRule("prospectorMinimumTriggerProbability", GameRuleCategory.MISC, 0, 0, 100);

    public static final GameRule<Integer> LEECHING_HP_STEAL_PERCENTAGE = registerCappedIntRule("leechingHpStealPercentage", GameRuleCategory.MISC, 20, null, null);

    private static GameRule<Integer> registerCappedIntRule(String id, GameRuleCategory category, int defaultValue, @Nullable Integer min, @Nullable Integer max) {
        id = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, id);
        GameRule<Integer> cappedIntRule = SkyCore.BuiltinRegistries.registerCappedIntRule(id, category, defaultValue,
                Objects.nonNull(min) ? min : Integer.MIN_VALUE, Objects.nonNull(max) ? max : Integer.MAX_VALUE);
        DR_GAMERULES.add(cappedIntRule);
        return cappedIntRule;
    }

    private static GameRule<Boolean> registerBooleanRule(String id, GameRuleCategory category, boolean defaultValue) {
        id = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, id);
        GameRule<Boolean> booleanRule = SkyCore.BuiltinRegistries.registerBooleanGameRule(id, category, defaultValue);
        DR_GAMERULES.add(booleanRule);
        return booleanRule;
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
