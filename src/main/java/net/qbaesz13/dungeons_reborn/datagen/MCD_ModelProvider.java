package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import net.qbaesz13.dungeons_reborn.registries.client.MCD_ModelTemplates;
import org.jspecify.annotations.NonNull;

public class MCD_ModelProvider extends SkyCoreDataGenAPI.SC_ModelProvider {
    public MCD_ModelProvider(FabricPackOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators bmg) {
        createStoneSetModels(bmg, MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE);
        bmg.createTrivialCube(MCD_Blocks.MOSSY_SPRUCE_PLANKS);
        registerRotatedWoolAndCarpet(bmg, MCD_Blocks.HIGHLAND_MOSS_BLOCK, MCD_Blocks.HIGHLAND_MOSS_CARPET);
        bmg.createCrossBlockWithDefaultItem(MCD_Blocks.MEDIUM_HIGHLAND_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        bmg.createCrossBlockWithDefaultItem(MCD_Blocks.SHORT_HIGHLAND_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        registerRotatedWoolAndCarpet(bmg, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        bmg.createCrossBlockWithDefaultItem(MCD_Blocks.MIDNIGHT_SPROUTS, BlockModelGenerators.PlantType.NOT_TINTED);
        bmg.createTrivialCube(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        bmg.createTrivialCube(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
        registerBerryBushBlock(bmg, MCD_Blocks.SOUR_BERRY_BUSH);
        registerBlockWith2Variants(bmg, MCD_Blocks.MOSSY_OAK_PLANKS);

        registerTrunkBlock(bmg, MCD_Blocks.PALM_TRUNK);
        registerTrunkBlock(bmg, MCD_Blocks.STRIPPED_PALM_TRUNK);
        createWoodSetModels(
                bmg,
                MCD_Blocks.PALM_BEAM,
                MCD_Blocks.PALM_WOOD,
                MCD_Blocks.STRIPPED_PALM_BEAM,
                MCD_Blocks.STRIPPED_PALM_WOOD,
                (CeilingHangingSignBlock) MCD_Blocks.PALM_HANGING_SIGN,
                (WallHangingSignBlock) MCD_Blocks.PALM_WALL_HANGING_SIGN,
                MCD_BlockFamilies.PALM
        );
        bmg.createPlantWithDefaultItem(MCD_Blocks.PALM_SAPLING, MCD_Blocks.POTTED_PALM_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        bmg.registerSimpleFlatItemModel(MCD_Blocks.POP_FLOWER,"_0"); // Doesn't appear in 1.21.1
    }
    @Override
    public void generateItemModels(@NonNull ItemModelGenerators img) {
        this.registerBow(img, MCD_Items.TWIN_BOW);
        this.registerBow(img, MCD_Items.SHORTBOW);
        this.registerBow(img, MCD_Items.LONGBOW);
        this.registerCrossbow(img, MCD_Items.AUTO_CROSSBOW);
        this.registerCrossbow(img, MCD_Items.HEAVY_CROSSBOW);

        img.generateFlatItem(MCD_Items.ANCIENT_GOLD_INGOT, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.RAW_ANCIENT_GOLD, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.SOUR_BERRIES, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.PALM_LEAVES, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.PALM_BOAT, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.PALM_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        img.generateFlatItem(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, ModelTemplates.FLAT_ITEM);
        img.generateFlatItem(MCD_Items.ARTIFACT_IRON_HIDE_AMULET, ModelTemplates.FLAT_ITEM);

        img.generateFlatItem(MCD_Items.ROUGH_DIAMOND_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.ROUGH_DIAMOND_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.STEEL_MACE, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.SUNS_GRACE, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.CUTLASS, ModelTemplates.FLAT_HANDHELD_ITEM);
        img.generateFlatItem(MCD_Items.LADLE, ModelTemplates.FLAT_HANDHELD_ITEM);

        img.generateFlatItem(MCD_Items.CLAYMORE, MCD_ModelTemplates.HANDHELD_CLAYMORE);
        img.generateFlatItem(MCD_Items.HEARTSTEALER, MCD_ModelTemplates.HANDHELD_CLAYMORE);
        img.generateFlatItem(MCD_Items.BROADSWORD, MCD_ModelTemplates.HANDHELD_CLAYMORE);
    }
    private void registerBow(ItemModelGenerators img, Item bow) {
        img.createFlatItemModel(bow, ModelTemplates.BOW);
        if (bow instanceof SC_BowItem scBow) {
            float scalingFactor = scBow.pullTime / 20.0F;
            ItemModel.Unbaked bowModel = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(bow));
            ItemModel.Unbaked pulling0 = ItemModelUtils.plainModel(img.createFlatItemModel(bow, "_pulling_0", ModelTemplates.BOW));
            ItemModel.Unbaked pulling1 = ItemModelUtils.plainModel(img.createFlatItemModel(bow, "_pulling_1", ModelTemplates.BOW));
            ItemModel.Unbaked pulling2 = ItemModelUtils.plainModel(img.createFlatItemModel(bow, "_pulling_2", ModelTemplates.BOW));
            img.itemModelOutput
                    .accept(
                            bow,
                            ItemModelUtils.conditional(
                                    ItemModelUtils.isUsingItem(),
                                    ItemModelUtils.rangeSelect(
                                            new UseDuration(false),
                                            0.05F,
                                            pulling0,
                                            ItemModelUtils.override(pulling1, 0.65F * scalingFactor),
                                            ItemModelUtils.override(pulling2, 0.9F * scalingFactor)),
                                    bowModel
                            )
                    );
        } else img.generateBow(bow);
    }
    private void registerCrossbow(ItemModelGenerators img, Item crossbow) {
        ModelTemplates.CROSSBOW.create(ModelLocationUtils.getModelLocation(crossbow), TextureMapping.layer0(TextureMapping.getItemTexture(crossbow, "_standby")), img.modelOutput);
        img.generateCrossbow(crossbow);
    }
}
