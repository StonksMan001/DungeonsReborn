package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;

import java.util.concurrent.CompletableFuture;

public class MCD_BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MCD_BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(MCD_Blocks.MOSSIER_OAK_PLANKS)
                .add(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .add(MCD_Blocks.MIDNIGHT_MOSS_CARPET)
                .add(MCD_Blocks.DRIED_MOSS_BLOCK)
                .add(MCD_Blocks.DRIED_MOSS_CARPET);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(MCD_Blocks.MOSSIER_COBBLESTONE)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);
        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .add(MCD_Blocks.DRIED_MOSS_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK);
    }
}
