package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.stonksman01.dungeons_reborn.blocks.MossyOakPlanksBlock;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;
import net.stonksman01.dungeons_reborn.registries.MCD_Items;
import net.stonksman01.dungeons_reborn.registries.MCD_Models;

public class MCD_ModelProvider extends FabricModelProvider {
    public MCD_ModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
        blockStateModelGenerator.registerWoolAndCarpet(MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        blockStateModelGenerator.registerWoolAndCarpet(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        blockStateModelGenerator.registerTintableCross(MCD_Blocks.MIDNIGHT_SPROUTS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        registerBerryBushBlock(blockStateModelGenerator, MCD_Blocks.SOUR_BERRY_BUSH);

        registerBlockWith2Variants(blockStateModelGenerator, MCD_Blocks.MOSSIER_OAK_PLANKS, MossyOakPlanksBlock.MOSSIER, "_2");
    }
    private void registerBlockWith2Variants(BlockStateModelGenerator blockStateModelGenerator, Block block, BooleanProperty booleanProperty, String suffix) {
        Identifier identifier = TexturedModel.CUBE_ALL.upload(block, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = blockStateModelGenerator.createSubModel(block, suffix, Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector
                .accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateModelGenerator.createBooleanModelMap(booleanProperty, identifier2, identifier)));
    }
    private void registerBerryBushBlock(BlockStateModelGenerator blockStateModelGenerator, Block berryBushBlock) {
        blockStateModelGenerator.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(berryBushBlock)
                                .coordinate(
                                        BlockStateVariantMap.create(Properties.AGE_3)
                                                .register(
                                                        stage -> BlockStateVariant.create()
                                                                .put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(berryBushBlock, "_stage" + stage, Models.CROSS, TextureMap::cross))
                                                )
                                )
                );
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(MCD_Items.ANCIENT_GOLD_INGOT, Models.GENERATED);
        itemModelGenerator.register(MCD_Items.SOUR_BERRIES, Models.GENERATED);
        itemModelGenerator.register(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, Models.GENERATED);
        itemModelGenerator.register(MCD_Items.ARTIFACT_IRON_HIDE_AMULET, Models.GENERATED);

        itemModelGenerator.register(MCD_Items.ROUGH_DIAMOND_SWORD, Models.HANDHELD);
        itemModelGenerator.register(MCD_Items.ROUGH_DIAMOND_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(MCD_Items.STEEL_MACE, Models.HANDHELD);
        itemModelGenerator.register(MCD_Items.CLAYMORE, MCD_Models.HANDHELD_CLAYMORE);
        itemModelGenerator.register(MCD_Items.BROADSWORD, MCD_Models.HANDHELD_CLAYMORE);
    }
}
