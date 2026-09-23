package net.qbaesz13.dungeons_reborn;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_ModelPredicateProviders;
import net.qbaesz13.dungeons_reborn.registries.client.MCD_Models;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MCD_Models.register();

        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.POP_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.MIDNIGHT_SPROUTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.MEDIUM_HIGHLAND_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.SHORT_HIGHLAND_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.SOUR_BERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.PALM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.PALM_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.POTTED_PALM_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.PALM_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.PALM_TRAPDOOR, RenderLayer.getCutout());

        TerraformBoatClientHelper.registerModelLayers(DungeonsReborn.identifierOfDungeonsReborn("palm_boat"), false);

        MCD_ModelPredicateProviders.registerModels();
    }
}
