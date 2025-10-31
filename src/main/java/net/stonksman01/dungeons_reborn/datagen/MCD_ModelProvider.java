package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;
import net.stonksman01.dungeons_reborn.registries.MCD_Items;

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

        blockStateModelGenerator.registerItemModel(MCD_Blocks.POP_FLOWER,"_0");
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.upload(MCD_Items.TWIN_BOW, Models.BOW);
        itemModelGenerator.registerBow(MCD_Items.TWIN_BOW);
        itemModelGenerator.upload(MCD_Items.AUTO_CROSSBOW, Models.CROSSBOW);
        itemModelGenerator.registerCrossbow(MCD_Items.AUTO_CROSSBOW);

        itemModelGenerator.register(MCD_Items.ANCIENT_GOLD_INGOT, Models.GENERATED);
        itemModelGenerator.register(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, Models.GENERATED);
        itemModelGenerator.register(MCD_Items.ARTIFACT_IRON_HIDE_AMULET, Models.GENERATED);

        itemModelGenerator.register(MCD_Items.ROUGH_DIAMOND_SWORD, Models.HANDHELD);
        itemModelGenerator.register(MCD_Items.STEEL_MACE, Models.HANDHELD);
    }
}
