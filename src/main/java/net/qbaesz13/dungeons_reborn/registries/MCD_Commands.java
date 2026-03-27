package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRules;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

public class MCD_Commands {
    private static <T> void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("dr_resetgamerules")
                    .requires(CommandManager.requirePermissionLevel(CommandManager.GAMEMASTERS_CHECK))
                    .executes(context -> {
                        ServerCommandSource source = context.getSource();
                        ServerWorld world = source.getWorld();
                        GameRules gameRules = world.getGameRules();
                        var ref = new Object() {int count = 0;};
                        for (GameRule<?> rule : MCD_GameRules.DR_GAMERULES) {
                            if (!Objects.equals(rule.getDefaultValue(), gameRules.getValue(rule))) {
                                gameRules.setValue(((GameRule<T>)rule), (T)rule.getDefaultValue(), source.getServer());
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
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
