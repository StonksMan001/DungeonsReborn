package net.qbaesz13.dungeons_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.qbaesz13.dungeons_reborn._included_libs.terraform_wood_api.client.TerraformCpyBoatClientHelper;
import net.qbaesz13.dungeons_reborn.registries.client.MCD_ModelTemplates;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MCD_ModelTemplates.register();
        TerraformCpyBoatClientHelper.registerModelLayers(DungeonsReborn.identifierOfDungeonsReborn("palm"));
    }
}