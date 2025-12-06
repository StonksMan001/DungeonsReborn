package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.item.Item;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.stonksman01.dungeons_reborn.blocks.MossyOakPlanksBlock;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;
import net.stonksman01.dungeons_reborn.registries.MCD_Items;
import net.stonksman01.dungeons_reborn.registries.MCD_Models;
import org.jetbrains.annotations.NotNull;

public class MCD_ModelProvider extends FabricModelProvider {
    public MCD_ModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.MOSSIER_COBBLESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
        blockStateModelGenerator.registerWoolAndCarpet(MCD_Blocks.DRIED_MOSS_BLOCK, MCD_Blocks.DRIED_MOSS_CARPET);
        blockStateModelGenerator.registerWoolAndCarpet(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        blockStateModelGenerator.registerTintableCross(MCD_Blocks.MIDNIGHT_SPROUTS, BlockStateModelGenerator.CrossType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        this.registerBerryBushBlock(blockStateModelGenerator, MCD_Blocks.SOUR_BERRY_BUSH);

        blockStateModelGenerator.registerItemModel(MCD_Blocks.POP_FLOWER,"_0");
        this.registerBlockWith2Variants(blockStateModelGenerator, MCD_Blocks.MOSSIER_OAK_PLANKS, MossyOakPlanksBlock.MOSSIER, "_2");
    }
    private void registerBlockWith2Variants(BlockStateModelGenerator blockStateModelGenerator, Block block, BooleanProperty booleanProperty, String suffix) {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_ALL.upload(block, blockStateModelGenerator.modelCollector));
        WeightedVariant weightedVariant2 = BlockStateModelGenerator.createWeightedVariant(blockStateModelGenerator.createSubModel(block, suffix, Models.CUBE_ALL, TextureMap::all));
        blockStateModelGenerator.blockStateCollector
                .accept(VariantsBlockModelDefinitionCreator.of(block).with(BlockStateModelGenerator.createBooleanModelMap(booleanProperty, weightedVariant2, weightedVariant)));
    }
    private void registerBerryBushBlock(BlockStateModelGenerator blockStateModelGenerator, Block berryBushBlock) {
        blockStateModelGenerator.blockStateCollector
                .accept(
                        VariantsBlockModelDefinitionCreator.of(berryBushBlock)
                                .with(
                                        BlockStateVariantMap.models(Properties.AGE_3)
                                                .generate(stage -> BlockStateModelGenerator.createWeightedVariant(
                                                        blockStateModelGenerator.createSubModel(berryBushBlock, "_stage" + stage,
                                                                Models.CROSS, TextureMap::cross)))
                                )
                );
    }
    @Override
    public void generateItemModels(@NotNull ItemModelGenerator itemModelGenerator) {
        this.registerBow(itemModelGenerator, MCD_Items.TWIN_BOW);
        this.registerCrossbow(itemModelGenerator, MCD_Items.AUTO_CROSSBOW);
        this.registerCrossbow(itemModelGenerator, MCD_Items.HEAVY_CROSSBOW);

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
    private void registerBow(ItemModelGenerator itemModelGenerator, Item bow) {
        itemModelGenerator.upload(bow, Models.BOW);
        itemModelGenerator.registerBow(bow);
    }
    private void registerCrossbow(ItemModelGenerator itemModelGenerator, Item crossbow) {
        Models.CROSSBOW.upload(ModelIds.getItemModelId(crossbow), TextureMap.layer0(TextureMap.getSubId(crossbow, "_standby")), itemModelGenerator.modelCollector);
        itemModelGenerator.registerCrossbow(crossbow);
    }
}
