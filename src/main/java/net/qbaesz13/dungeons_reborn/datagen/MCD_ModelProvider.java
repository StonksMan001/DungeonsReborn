package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import net.qbaesz13.dungeons_reborn.registries.MCD_Models;

public class MCD_ModelProvider extends FabricModelProvider {
    public MCD_ModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
        registerRotatedWoolAndCarpet(blockStateModelGenerator, MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        registerRotatedWoolAndCarpet(blockStateModelGenerator, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        blockStateModelGenerator.registerTintableCross(MCD_Blocks.MIDNIGHT_SPROUTS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        registerBerryBushBlock(blockStateModelGenerator, MCD_Blocks.SOUR_BERRY_BUSH);

        registerBlockWith2Variants(blockStateModelGenerator, MCD_Blocks.MOSSIER_OAK_PLANKS);

    }
    private void registerRotatedWoolAndCarpet(BlockStateModelGenerator blockStateModelGenerator, Block wool, Block carpet) {
        blockStateModelGenerator.registerRotatable(wool);
        Identifier identifier = TexturedModel.CARPET.get(wool).upload(carpet, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(carpet, identifier));
    }
    private void registerBlockWith2Variants(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier identifier1 = blockStateModelGenerator.createSubModel(block, "1", Models.CUBE_ALL, TextureMap::all);
        Identifier identifier2 = blockStateModelGenerator.createSubModel(block, "2", Models.CUBE_ALL, TextureMap::all);
        BlockStateVariant var1 = BlockStateVariant.create().put(VariantSettings.MODEL, identifier1);
        BlockStateVariant var2 = BlockStateVariant.create().put(VariantSettings.MODEL, identifier2);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, var1, var2));
        blockStateModelGenerator.registerParentedItemModel(block, identifier1);
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
        itemModelGenerator.register(MCD_Items.SUNS_GRACE, Models.HANDHELD);
        itemModelGenerator.register(MCD_Items.CLAYMORE, MCD_Models.HANDHELD_CLAYMORE);
        itemModelGenerator.register(MCD_Items.HEARTSTEALER, MCD_Models.HANDHELD_CLAYMORE);
        itemModelGenerator.register(MCD_Items.BROADSWORD, MCD_Models.HANDHELD_CLAYMORE);
    }
}
