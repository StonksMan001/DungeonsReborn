package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SaplingBlock;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MCD_BlockTagsProvider extends SkyCoreDataGenAPI.SC_BlockTagsProvider {
    public MCD_BlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }
    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE);
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(SkyCore.getResourceKeys(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET))
                .add(SkyCore.getResourceKeys(MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET));
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SkyCore.getResourceKey(MCD_Blocks.ANCIENT_GOLD_BLOCK))
                .add(SkyCore.getResourceKey(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK));
        tag(BlockTags.MINEABLE_WITH_SHOVEL);
        tag(BlockTags.DIRT)
                .add(SkyCore.getResourceKey(MCD_Blocks.MIDNIGHT_MOSS_BLOCK))
                .add(SkyCore.getResourceKey(MCD_Blocks.HIGHLAND_MOSS_BLOCK));
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(SkyCore.getResourceKey(MCD_Blocks.ANCIENT_GOLD_BLOCK))
                .add(SkyCore.getResourceKey(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK));

        createGrassTags(MCD_Blocks.MIDNIGHT_SPROUTS);
        createGrassTags(MCD_Blocks.POP_FLOWER);
        createGrassTags(MCD_Blocks.SHORT_HIGHLAND_GRASS);
        createGrassTags(MCD_Blocks.MEDIUM_HIGHLAND_GRASS);

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(SkyCore.getResourceKey(MCD_Blocks.PALM_TRUNK));
        tag(BlockTags.LOGS)
                .add(SkyCore.getResourceKeys(MCD_Blocks.PALM_TRUNK, MCD_Blocks.STRIPPED_PALM_TRUNK));
        tag(BlockTags.PLANKS)
                .add(SkyCore.getResourceKeys(MCD_Blocks.MOSSY_SPRUCE_PLANKS, MCD_Blocks.MOSSY_OAK_PLANKS));

        createStoneSetTags(MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE);
        createWoodSetTags(
                MCD_BlockTags.PALM_LOGS,
                MCD_Blocks.PALM_WOOD,
                MCD_Blocks.STRIPPED_PALM_WOOD,
                (SaplingBlock) MCD_Blocks.PALM_SAPLING,
                MCD_BlockFamilies.PALM,
                false
        );
        tag(BlockTags.FLOWER_POTS)
                .add(SkyCore.getResourceKeys(MCD_Blocks.POTTED_PALM_SAPLING));
    }
}
