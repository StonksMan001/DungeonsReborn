package net.qbaesz13.dungeons_reborn;

import com.terraformersmc.terraform.boat.api.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.qbaesz13.dungeons_reborn.registries.client.MCD_ModelTemplates;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MCD_ModelTemplates.register();
        TerraformBoatClientHelper.registerModelLayers(DungeonsReborn.identifierOfDungeonsReborn("palm"));
    }
}
