package net.stonksman01.dungeons_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;
import net.stonksman01.dungeons_reborn.registries.MCD_ModelPredicateProviders;

public class DungeonsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.POP_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCD_Blocks.MIDNIGHT_SPROUTS, RenderLayer.getCutout());
        
        MCD_ModelPredicateProviders.registerModels();
    }
}
