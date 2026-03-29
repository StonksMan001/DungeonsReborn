package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MCD_BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public MCD_BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }
    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(MCD_Blocks.MOSSIER_OAK_PLANKS)
                .add(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .add(MCD_Blocks.MIDNIGHT_MOSS_CARPET)
                .add(MCD_Blocks.HIGHLAND_MOSS_BLOCK)
                .add(MCD_Blocks.HIGHLAND_MOSS_CARPET);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL);
        valueLookupBuilder(BlockTags.DIRT)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .add(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK);
    }
}
