package net.qbaesz13.dungeons_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.qbaesz13.dungeons_reborn.registries.MCD_ModelTemplates;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MCD_ModelTemplates.register();
    }
}
