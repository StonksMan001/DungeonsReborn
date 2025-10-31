package net.stonksman01.dungeons_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayer;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(MCD_Blocks.POP_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MCD_Blocks.MIDNIGHT_SPROUTS, BlockRenderLayer.CUTOUT);
    }
}
