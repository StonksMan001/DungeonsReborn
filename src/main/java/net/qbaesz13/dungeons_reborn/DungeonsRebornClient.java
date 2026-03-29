package net.qbaesz13.dungeons_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.qbaesz13.dungeons_reborn.registries.MCD_ModelTemplates;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MCD_ModelTemplates.register();

        /* TODO?
        ChunkSectionLayerMap.putBlock(MCD_Blocks.POP_FLOWER, BlockRenderLayer.CUTOUT);
        ChunkSectionLayerMap.putBlock(MCD_Blocks.MIDNIGHT_SPROUTS, BlockRenderLayer.CUTOUT);
        ChunkSectionLayerMap.putBlock(MCD_Blocks.SOUR_BERRY_BUSH, BlockRenderLayer.CUTOUT);
        */
    }
}
