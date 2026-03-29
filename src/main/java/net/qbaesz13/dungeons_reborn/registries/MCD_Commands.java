package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

public class MCD_Commands {
    private static <T> void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, buildContext, commandSelection) -> {
            dispatcher.register(Commands.literal("dr_resetgamerules")
                    .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(context -> {
                        CommandSourceStack source = context.getSource();
                        ServerLevel serverLevel = source.getLevel();
                        GameRules gameRules = serverLevel.getGameRules();
                        var ref = new Object() {int count = 0;};
                        for (GameRule<?> rule : MCD_GameRules.DR_GAMERULES) {
                            if (!Objects.equals(rule.defaultValue(), gameRules.get(rule))) {
                                gameRules.set(((GameRule<T>)rule), (T)rule.defaultValue(), source.getServer());
                                ref.count++;
                            }
                        }
                        source.sendSuccess(() -> Component.translatable("command.dungeons_reborn.resetgamerules", ref.count), true);
                        return ref.count;
                    }));
        }));
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}