package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
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
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET)
                .add(MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK)
                .add(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL);
        valueLookupBuilder(BlockTags.DIRT)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .add(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK)
                .add(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);

        createGrassTags(MCD_Blocks.MIDNIGHT_SPROUTS);
        createGrassTags(MCD_Blocks.POP_FLOWER);
        createGrassTags(MCD_Blocks.SHORT_HIGHLAND_GRASS);
        createGrassTags(MCD_Blocks.MEDIUM_HIGHLAND_GRASS);

        valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(MCD_Blocks.PALM_TRUNK);
        valueLookupBuilder(BlockTags.LOGS)
                .add(MCD_Blocks.PALM_TRUNK, MCD_Blocks.STRIPPED_PALM_TRUNK);
        valueLookupBuilder(BlockTags.PLANKS)
                .add(MCD_Blocks.MOSSY_SPRUCE_PLANKS, MCD_Blocks.MOSSY_OAK_PLANKS);

        createStoneSetTags(MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE);
        createWoodSetTags(
                MCD_BlockTags.PALM_LOGS,
                MCD_Blocks.PALM_BEAM,
                MCD_Blocks.PALM_WOOD,
                MCD_Blocks.STRIPPED_PALM_BEAM,
                MCD_Blocks.STRIPPED_PALM_WOOD,
                (CeilingHangingSignBlock) MCD_Blocks.PALM_HANGING_SIGN,
                (WallHangingSignBlock) MCD_Blocks.PALM_WALL_HANGING_SIGN,
                (SaplingBlock) MCD_Blocks.PALM_SAPLING,
                MCD_BlockFamilies.PALM,
                false
        );
        valueLookupBuilder(BlockTags.FLOWER_POTS)
                .add(MCD_Blocks.POTTED_PALM_SAPLING);
    }
}
