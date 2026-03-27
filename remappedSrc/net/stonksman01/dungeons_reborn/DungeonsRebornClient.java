package net.qbaesz13.dungeons_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayer;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Models;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MCD_Models.register();

        BlockRenderLayerMap.putBlock(MCD_Blocks.POP_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MCD_Blocks.MIDNIGHT_SPROUTS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MCD_Blocks.SOUR_BERRY_BUSH, BlockRenderLayer.CUTOUT);
    }
}
