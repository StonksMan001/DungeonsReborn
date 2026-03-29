package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

public class MCD_RecipeProvider extends FabricRecipeProvider {
    public MCD_RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override @NullMarked
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                offerReversibleCompactingRecipes(RecipeCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.ANCIENT_GOLD_BLOCK);
                offerCarpetRecipe(MCD_Blocks.HIGHLAND_MOSS_CARPET, MCD_Blocks.HIGHLAND_MOSS_BLOCK);
                offerCarpetRecipe(MCD_Blocks.MIDNIGHT_MOSS_CARPET, MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
                offerMossyVariantRecipes(this, exporter, MCD_Blocks.MOSSIER_OAK_PLANKS, Blocks.OAK_PLANKS);
                offerMossyVariantRecipes(this, exporter, MCD_Blocks.MOSSIER_SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS);
                createShapeless(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE)
                        .input(Blocks.COBBLESTONE)
                        .input(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                        .criterion(hasItem(MCD_Blocks.MIDNIGHT_MOSS_BLOCK), conditionsFromItem(MCD_Blocks.MIDNIGHT_MOSS_BLOCK))
                        .criterion(hasItem(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE), conditionsFromItem(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE))
                        .criterion(hasItem(Blocks.COBBLESTONE), conditionsFromItem(Blocks.COBBLESTONE))
                        .offerTo(exporter);
            }
        };
    }
    private void offerMossyVariantRecipes(RecipeGenerator recipeGenerator, RecipeExporter exporter, Block mossyBlock, Block baseBlock) {
        recipeGenerator.createShapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_COBBLESTONE)
                .input(baseBlock)
                .input(Blocks.VINE)
                .criterion("has_vine", recipeGenerator.conditionsFromItem(Blocks.VINE))
                .offerTo(exporter, RecipeGenerator.convertBetween(mossyBlock, Blocks.VINE));

        recipeGenerator.createShapeless(RecipeCategory.BUILDING_BLOCKS, mossyBlock)
                .input(baseBlock)
                .input(Blocks.MOSS_BLOCK)
                .criterion("has_moss_block", recipeGenerator.conditionsFromItem(Blocks.MOSS_BLOCK))
                .offerTo(exporter, RecipeGenerator.convertBetween(mossyBlock, Blocks.MOSS_BLOCK));
    }
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
