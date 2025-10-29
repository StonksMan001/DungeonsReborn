package net.stonksman01.dungeons_reborn.registries;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.stonksman01.dungeons_reborn.DungeonsReborn;

public class MCD_GameRules {
    public static final GameRules.Key<GameRules.BooleanRule> POP_FLOWERS_ALWAYS_HIDE = GameRuleRegistry.register("popFlowersAlwaysHide", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(false));
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering GameRules");
    }
}
