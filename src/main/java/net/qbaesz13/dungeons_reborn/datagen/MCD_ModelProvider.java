package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import net.qbaesz13.dungeons_reborn.registries.client.MCD_Models;

public class MCD_ModelProvider extends SkyCoreDataGenAPI.SC_ModelProvider {
    public MCD_ModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsmg) {
        createStoneSetModels(bsmg, MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE);
        bsmg.registerSimpleCubeAll(MCD_Blocks.MOSSY_SPRUCE_PLANKS);
        registerRotatedWoolAndCarpet(bsmg, MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        bsmg.registerTintableCross(MCD_Blocks.MEDIUM_HIGHLAND_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);
        bsmg.registerTintableCross(MCD_Blocks.SHORT_HIGHLAND_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerRotatedWoolAndCarpet(bsmg, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        bsmg.registerTintableCross(MCD_Blocks.MIDNIGHT_SPROUTS, BlockStateModelGenerator.TintType.NOT_TINTED);
        bsmg.registerSimpleCubeAll(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        bsmg.registerSimpleCubeAll(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
        registerBerryBushBlock(bsmg, MCD_Blocks.SOUR_BERRY_BUSH);
        registerBlockWith2Variants(bsmg, MCD_Blocks.MOSSY_OAK_PLANKS);

        registerTrunkBlock(bsmg, MCD_Blocks.PALM_TRUNK);
        registerTrunkBlock(bsmg, MCD_Blocks.STRIPPED_PALM_TRUNK);
        createWoodSetModels(
                bsmg,
                MCD_Blocks.PALM_BEAM,
                MCD_Blocks.PALM_WOOD,
                MCD_Blocks.STRIPPED_PALM_BEAM,
                MCD_Blocks.STRIPPED_PALM_WOOD,
                (HangingSignBlock) MCD_Blocks.PALM_HANGING_SIGN,
                (WallHangingSignBlock) MCD_Blocks.PALM_WALL_HANGING_SIGN,
                MCD_BlockFamilies.PALM
        );
        bsmg.registerFlowerPotPlant(MCD_Blocks.PALM_SAPLING, MCD_Blocks.POTTED_PALM_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }
    @Override
    public void generateItemModels(ItemModelGenerator img) {
        img.register(MCD_Items.ANCIENT_GOLD_INGOT, Models.GENERATED);
        img.register(MCD_Items.RAW_ANCIENT_GOLD, Models.GENERATED);
        img.register(MCD_Items.SOUR_BERRIES, Models.GENERATED);
        img.register(MCD_Items.PALM_LEAVES, Models.GENERATED);
        img.register(MCD_Items.PALM_BOAT, Models.GENERATED);
        img.register(MCD_Items.PALM_CHEST_BOAT, Models.GENERATED);

        img.register(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, Models.GENERATED);
        img.register(MCD_Items.ARTIFACT_IRON_HIDE_AMULET, Models.GENERATED);

        img.register(MCD_Items.ROUGH_DIAMOND_SWORD, Models.HANDHELD);
        img.register(MCD_Items.ROUGH_DIAMOND_PICKAXE, Models.HANDHELD);
        img.register(MCD_Items.STEEL_MACE, Models.HANDHELD);
        img.register(MCD_Items.SUNS_GRACE, Models.HANDHELD);
        img.register(MCD_Items.CUTLASS, Models.HANDHELD);
        img.register(MCD_Items.LADLE, Models.HANDHELD);

        img.register(MCD_Items.CLAYMORE, MCD_Models.HANDHELD_CLAYMORE);
        img.register(MCD_Items.HEARTSTEALER, MCD_Models.HANDHELD_CLAYMORE);
        img.register(MCD_Items.BROADSWORD, MCD_Models.HANDHELD_CLAYMORE);
    }
}
