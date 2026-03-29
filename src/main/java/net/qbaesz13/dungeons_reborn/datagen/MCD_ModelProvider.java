package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import net.qbaesz13.dungeons_reborn.registries.MCD_Models;
import org.jetbrains.annotations.NotNull;

public class MCD_ModelProvider extends FabricModelProvider {
    public MCD_ModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsmg) {
        bsmg.registerSimpleCubeAll(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        bsmg.registerSimpleCubeAll(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
        this.registerRotatedWoolAndCarpet(bsmg, MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        this.registerRotatedWoolAndCarpet(bsmg, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        bsmg.registerTintableCross(MCD_Blocks.MIDNIGHT_SPROUTS, BlockStateModelGenerator.CrossType.NOT_TINTED);
        bsmg.registerSimpleCubeAll(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        this.registerBerryBushBlock(bsmg, MCD_Blocks.SOUR_BERRY_BUSH);

        registerBlockWith2Variants(bsmg, MCD_Blocks.MOSSIER_OAK_PLANKS);
        bsmg.registerItemModel(MCD_Blocks.POP_FLOWER,"_0");
    }
    private void registerRotatedWoolAndCarpet(BlockStateModelGenerator bsmg, Block wool, Block carpet) {
        bsmg.registerRandomHorizontalRotations(TexturedModel.CUBE_ALL, wool);
        ModelVariant modelVariant = BlockStateModelGenerator.createModelVariant(TexturedModel.CARPET.get(wool).upload(carpet, bsmg.modelCollector));
        bsmg.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(carpet, BlockStateModelGenerator.modelWithYRotation(modelVariant)));
    }
    private void registerBlockWith2Variants(BlockStateModelGenerator bsmg, Block block) {
        Identifier identifier1 = bsmg.createSubModel(block, "1", Models.CUBE_ALL, TextureMap::all);
        Identifier identifier2 = bsmg.createSubModel(block, "2", Models.CUBE_ALL, TextureMap::all);
        bsmg.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block, BlockStateModelGenerator.createWeightedVariant(
                new ModelVariant(identifier1),
                new ModelVariant(identifier2)
        )));
        bsmg.registerParentedItemModel(block, identifier1);
    }
    private void registerBerryBushBlock(BlockStateModelGenerator bsmg, Block berryBushBlock) {
        bsmg.blockStateCollector
                .accept(
                        VariantsBlockModelDefinitionCreator.of(berryBushBlock)
                                .with(
                                        BlockStateVariantMap.models(Properties.AGE_3)
                                                .generate(stage -> BlockStateModelGenerator.createWeightedVariant(
                                                        bsmg.createSubModel(berryBushBlock, "_stage" + stage,
                                                                Models.CROSS, TextureMap::cross)))
                                )
                );
    }
    @Override
    public void generateItemModels(@NotNull ItemModelGenerator img) {
        this.registerBow(img, MCD_Items.TWIN_BOW);
        this.registerCrossbow(img, MCD_Items.AUTO_CROSSBOW);
        this.registerCrossbow(img, MCD_Items.HEAVY_CROSSBOW);

        img.register(MCD_Items.ANCIENT_GOLD_INGOT, Models.GENERATED);
        img.register(MCD_Items.SOUR_BERRIES, Models.GENERATED);
        img.register(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, Models.GENERATED);
        img.register(MCD_Items.ARTIFACT_IRON_HIDE_AMULET, Models.GENERATED);

        img.register(MCD_Items.ROUGH_DIAMOND_SWORD, Models.HANDHELD);
        img.register(MCD_Items.ROUGH_DIAMOND_PICKAXE, Models.HANDHELD);
        img.register(MCD_Items.STEEL_MACE, Models.HANDHELD);
        img.register(MCD_Items.SUNS_GRACE, Models.HANDHELD);
        img.register(MCD_Items.CLAYMORE, MCD_Models.HANDHELD_CLAYMORE);
        img.register(MCD_Items.HEARTSTEALER, MCD_Models.HANDHELD_CLAYMORE);
        img.register(MCD_Items.BROADSWORD, MCD_Models.HANDHELD_CLAYMORE);
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
