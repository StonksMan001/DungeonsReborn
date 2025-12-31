package net.stonksman01.dungeons_reborn.registries;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.skycore.gamerules.CappedIntRule;

public class MCD_Commands {
    private static void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("dr_resetgamerules")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(context -> {
                        ServerCommandSource source = context.getSource();
                        ServerWorld world = source.getWorld();
                        GameRules gameRules = world.getGameRules();
                        var ref = new Object() {int count = 0;};
                        for (CappedIntRule cappedIntRule : MCD_GameRules.CAPPED_INT_RULES.keySet()) {
                            GameRules.IntRule intRule = gameRules.get(cappedIntRule.rule());
                            int defaultValue = MCD_GameRules.CAPPED_INT_RULES.get(cappedIntRule);
                            if (defaultValue != intRule.get()) {
                                intRule.set(defaultValue, source.getServer());
                                ref.count++;
                            }
                        }
                        for (GameRules.Key<GameRules.BooleanRule> booleanRule : MCD_GameRules.BOOLEAN_RULES.keySet()) {
                            GameRules.BooleanRule boolRule = gameRules.get(booleanRule);
                            boolean defaultValue = MCD_GameRules.BOOLEAN_RULES.get(booleanRule);
                            if (defaultValue != boolRule.get()) {
                                boolRule.set(defaultValue, source.getServer());
                                ref.count++;
                            }
                        }
                        source.sendFeedback(() -> Text.translatable("command.dungeons_reborn.resetgamerules", ref.count), true);
                        return ref.count;
                    }));
        }));
    }
    public static void register() {
        init();
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Commands");
    }
}
