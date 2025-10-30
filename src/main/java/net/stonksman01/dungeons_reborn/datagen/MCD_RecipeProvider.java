package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;
import net.stonksman01.dungeons_reborn.registries.MCD_Items;

import java.util.concurrent.CompletableFuture;

public class MCD_RecipeProvider extends FabricRecipeProvider {
    public MCD_RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.ANCIENT_GOLD_BLOCK);
        offerCarpetRecipe(exporter, MCD_Blocks.DRIED_MOSS_CARPET, MCD_Blocks.DRIED_MOSS_BLOCK);
        offerCarpetRecipe(exporter, MCD_Blocks.MIDNIGHT_MOSS_CARPET, MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
        offerMossyVariantRecipes(exporter, MCD_Blocks.MOSSIER_OAK_PLANKS, Blocks.OAK_PLANKS);
        offerMossyVariantRecipes(exporter, MCD_Blocks.MOSSIER_SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MOSSIER_COBBLESTONE)
                .input(Blocks.COBBLESTONE)
                .input(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .criterion(hasItem(MCD_Blocks.MIDNIGHT_MOSS_BLOCK), conditionsFromItem(MCD_Blocks.MIDNIGHT_MOSS_BLOCK))
                .criterion(hasItem(MCD_Blocks.MOSSIER_COBBLESTONE), conditionsFromItem(MCD_Blocks.MOSSIER_COBBLESTONE))
                .criterion(hasItem(Blocks.COBBLESTONE), conditionsFromItem(Blocks.COBBLESTONE))
                .offerTo(exporter);
    }
    private void offerMossyVariantRecipes(RecipeExporter exporter, Block mossyBlock, Block baseBlock) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, mossyBlock)
                .input(baseBlock)
                .input(Blocks.VINE)
                .criterion("has_vine", conditionsFromItem(Blocks.VINE))
                .offerTo(exporter, convertBetween(mossyBlock, Blocks.VINE));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, mossyBlock)
                .input(baseBlock)
                .input(Blocks.MOSS_BLOCK)
                .criterion("has_moss_block", conditionsFromItem(Blocks.MOSS_BLOCK))
                .offerTo(exporter, convertBetween(mossyBlock, Blocks.MOSS_BLOCK));
    }
}
