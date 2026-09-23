package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;

import java.util.concurrent.CompletableFuture;

public class MCD_BlockTagProvider extends SkyCoreDataGenAPI.SC_BlockTagProvider {
    public MCD_BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET)
                .add(MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK)
                .add(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);
        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .add(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(MCD_Blocks.ANCIENT_GOLD_BLOCK)
                .add(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);

        createGrassTags(MCD_Blocks.MIDNIGHT_SPROUTS);
        createGrassTags(MCD_Blocks.POP_FLOWER);
        createGrassTags(MCD_Blocks.SHORT_HIGHLAND_GRASS);
        createGrassTags(MCD_Blocks.MEDIUM_HIGHLAND_GRASS);

        getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(MCD_Blocks.PALM_TRUNK);
        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(MCD_Blocks.PALM_TRUNK, MCD_Blocks.STRIPPED_PALM_TRUNK);
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(MCD_Blocks.MOSSY_SPRUCE_PLANKS, MCD_Blocks.MOSSY_OAK_PLANKS);

        createStoneSetTags(MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE);
        createWoodSetTags(
                MCD_BlockTags.PALM_LOGS,
                MCD_Blocks.PALM_BEAM,
                MCD_Blocks.PALM_WOOD,
                MCD_Blocks.STRIPPED_PALM_BEAM,
                MCD_Blocks.STRIPPED_PALM_WOOD,
                (HangingSignBlock) MCD_Blocks.PALM_HANGING_SIGN,
                (WallHangingSignBlock) MCD_Blocks.PALM_WALL_HANGING_SIGN,
                (SaplingBlock) MCD_Blocks.PALM_SAPLING,
                MCD_BlockFamilies.PALM,
                false
        );
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(MCD_Blocks.POTTED_PALM_SAPLING);
    }
}
