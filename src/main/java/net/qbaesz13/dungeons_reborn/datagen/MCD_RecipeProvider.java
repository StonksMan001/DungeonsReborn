package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

public class MCD_RecipeProvider extends FabricRecipeProvider {
    public MCD_RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override @NullMarked
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                nineBlockStorageRecipes(RecipeCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.ANCIENT_GOLD_BLOCK);
                carpet(MCD_Blocks.HIGHLAND_MOSS_CARPET, MCD_Blocks.HIGHLAND_MOSS_BLOCK);
                carpet(MCD_Blocks.MIDNIGHT_MOSS_CARPET, MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
                offerMossyVariantRecipes(this, output, MCD_Blocks.MOSSIER_OAK_PLANKS, Blocks.OAK_PLANKS);
                offerMossyVariantRecipes(this, output, MCD_Blocks.MOSSIER_SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS);
                shapeless(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE)
                        .requires(Blocks.COBBLESTONE)
                        .requires(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                        .unlockedBy(getHasName(MCD_Blocks.MIDNIGHT_MOSS_BLOCK), has(MCD_Blocks.MIDNIGHT_MOSS_BLOCK))
                        .unlockedBy(getHasName(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE), has(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE))
                        .unlockedBy(getHasName(Blocks.COBBLESTONE), has(Blocks.COBBLESTONE))
                        .save(output);
            }
        };
    }
    private void offerMossyVariantRecipes(RecipeProvider recipeProvider, RecipeOutput output, Block mossyBlock, Block baseBlock) {
        recipeProvider.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_COBBLESTONE)
                .requires(baseBlock)
                .requires(Blocks.VINE)
                .unlockedBy("has_vine", recipeProvider.has(Blocks.VINE))
                .save(output, RecipeProvider.getConversionRecipeName(mossyBlock, Blocks.VINE));

        recipeProvider.shapeless(RecipeCategory.BUILDING_BLOCKS, mossyBlock)
                .requires(baseBlock)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_moss_block", recipeProvider.has(Blocks.MOSS_BLOCK))
                .save(output, RecipeProvider.getConversionRecipeName(mossyBlock, Blocks.MOSS_BLOCK));
    }
    @Override
    public @NonNull String getName() {
        return this.getClass().getName();
    }

}
