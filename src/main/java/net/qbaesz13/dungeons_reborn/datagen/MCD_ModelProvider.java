package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import net.qbaesz13.dungeons_reborn.registries.MCD_ModelTemplates;
import org.jspecify.annotations.NonNull;

public class MCD_ModelProvider extends FabricModelProvider {
    public MCD_ModelProvider(FabricPackOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockModelGenerators bmg) {
        bmg.createTrivialCube(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        bmg.createTrivialCube(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
        this.registerRotatedWoolAndCarpet(bmg, MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        this.registerRotatedWoolAndCarpet(bmg, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        bmg.createCrossBlockWithDefaultItem(MCD_Blocks.MIDNIGHT_SPROUTS, BlockModelGenerators.PlantType.NOT_TINTED);
        bmg.createTrivialCube(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        this.registerBerryBushBlock(bmg, MCD_Blocks.SOUR_BERRY_BUSH);

        registerBlockWith2Variants(bmg, MCD_Blocks.MOSSIER_OAK_PLANKS);
        bmg.registerSimpleFlatItemModel(MCD_Blocks.POP_FLOWER,"_0");
    }
    private void registerRotatedWoolAndCarpet(BlockModelGenerators bmg, Block wool, Block carpet) {
        bmg.createColoredBlockWithRandomRotations(TexturedModel.CUBE, wool);
        Variant variant = BlockModelGenerators.plainModel(TexturedModel.CARPET.get(wool).create(carpet, bmg.modelOutput));
        bmg.blockStateOutput.accept(MultiVariantGenerator.dispatch(carpet, BlockModelGenerators.createRotatedVariants(variant)));
    }
    private void registerBlockWith2Variants(BlockModelGenerators bmg, Block block) {
        Identifier identifier1 = bmg.createSuffixedVariant(block, "1", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        Identifier identifier2 = bmg.createSuffixedVariant(block, "2", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        bmg.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.variants(
                new Variant(identifier1),
                new Variant(identifier2)
        )));
        bmg.registerSimpleItemModel(block, identifier1);
    }
    private void registerBerryBushBlock(BlockModelGenerators bmg, Block berryBushBlock) {
        bmg.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(berryBushBlock)
                                .with(
                                        PropertyDispatch.initial(BlockStateProperties.AGE_3)
                                                .generate(age -> BlockModelGenerators.plainVariant(
                                                        bmg.createSuffixedVariant(berryBushBlock, "_stage" + age,
                                                        ModelTemplates.CROSS, TextureMapping::cross)))
                                )
                );
    }
    @Override
    public void generateItemModels(@NonNull ItemModelGenerators img) {
        this.registerBow(img, MCD_Items.TWIN_BOW);
        this.registerCrossbow(img, MCD_Items.AUTO_CROSSBOW);
        this.registerCrossbow(img, MCD_Items.HEAVY_CROSSBOW);

        img.generateFlatItem(MCD_Items.ANCIENT_GOLD_INGOT, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.SOUR_BERRIES, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.ARTIFACT_IRON_HIDE_AMULET, ModelTemplates.FLAT_ITEM);

        img.generateFlatItem(MCD_Items.ROUGH_DIAMOND_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.ROUGH_DIAMOND_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.STEEL_MACE, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.SUNS_GRACE, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.CLAYMORE, MCD_ModelTemplates.HANDHELD_CLAYMORE);
        img.generateFlatItem(MCD_Items.HEARTSTEALER, MCD_ModelTemplates.HANDHELD_CLAYMORE);
        img.generateFlatItem(MCD_Items.BROADSWORD, MCD_ModelTemplates.HANDHELD_CLAYMORE);
    }
    private void registerBow(ItemModelGenerators img, Item bow) {
        img.createFlatItemModel(bow, ModelTemplates.BOW);
        img.generateBow(bow);
    }
    private void registerCrossbow(ItemModelGenerators img, Item crossbow) {
        ModelTemplates.CROSSBOW.create(ModelLocationUtils.getModelLocation(crossbow), TextureMapping.layer0(TextureMapping.getItemTexture(crossbow, "_standby")), img.modelOutput);
        img.generateCrossbow(crossbow);
    }
}
