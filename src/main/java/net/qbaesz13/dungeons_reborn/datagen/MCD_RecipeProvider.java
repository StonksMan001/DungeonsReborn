package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_ItemTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MCD_RecipeProvider extends SkyCoreDataGenAPI.SC_RecipeProvider {
    public MCD_RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.ANCIENT_GOLD_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, MCD_Items.RAW_ANCIENT_GOLD, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
        offerSmelting(exporter, List.of(MCD_Items.RAW_ANCIENT_GOLD), RecipeCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, 1.0F, 200, "ancient_gold_ingot");
        offerBlasting(exporter, List.of(MCD_Items.RAW_ANCIENT_GOLD), RecipeCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, 1.0F, 100, "ancient_gold_ingot");

        offerCarpetRecipe(exporter, MCD_Blocks.HIGHLAND_MOSS_CARPET, MCD_Blocks.HIGHLAND_MOSS_BLOCK);
        offerCarpetRecipe(exporter, MCD_Blocks.MIDNIGHT_MOSS_CARPET, MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
        offerMossyVariantRecipes(exporter, MCD_Blocks.MOSSY_OAK_PLANKS, Blocks.OAK_PLANKS);
        offerMossyVariantRecipes(exporter, MCD_Blocks.MOSSY_SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE)
                .input(Blocks.COBBLESTONE)
                .input(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                .criterion(hasItem(MCD_Blocks.MIDNIGHT_MOSS_BLOCK), conditionsFromItem(MCD_Blocks.MIDNIGHT_MOSS_BLOCK))
                .criterion(hasItem(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE), conditionsFromItem(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE))
                .criterion(hasItem(Blocks.COBBLESTONE), conditionsFromItem(Blocks.COBBLESTONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, MCD_Items.LADLE)
                .input('X', Items.WOODEN_SHOVEL)
                .input('Y', Items.LEATHER)
                .input('Z', Items.STICK)
                .pattern("  X")
                .pattern(" Y ")
                .pattern("Z  ")
                .criterion(hasItem(Items.WOODEN_SHOVEL), conditionsFromItem(Items.WOODEN_SHOVEL))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, MCD_Items.SHORTBOW)
                .input('#', Items.STICK)
                .input('X', Items.STRING)
                .pattern("#X")
                .pattern("X ")
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, MCD_Items.LONGBOW)
                .input('#', Items.STICK)
                .input('X', Items.STRING)
                .input('Y', Items.FLINT)
                .pattern("Y##")
                .pattern("# X")
                .pattern("#X ")
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .offerTo(exporter);

        generateFamily(exporter, MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE, FeatureSet.of(FeatureFlags.VANILLA));
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_STAIRS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_WALL, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.PALM_PLANKS)
                .input(Ingredient.ofItems(MCD_Blocks.PALM_TRUNK, MCD_Blocks.STRIPPED_PALM_TRUNK))
                .criterion(hasItem(MCD_Blocks.PALM_TRUNK), conditionsFromItem(MCD_Blocks.PALM_TRUNK))
                .criterion(hasItem(MCD_Blocks.STRIPPED_PALM_TRUNK), conditionsFromItem(MCD_Blocks.STRIPPED_PALM_TRUNK))
                .offerTo(exporter, "palm_planks_from_trunk");
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.PALM_BEAM, MCD_Blocks.PALM_TRUNK);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.STRIPPED_PALM_BEAM, MCD_Blocks.STRIPPED_PALM_TRUNK);
        generateFamily(exporter, MCD_BlockFamilies.PALM, FeatureSet.of(FeatureFlags.VANILLA));
        offerPlanksRecipe(exporter, MCD_Blocks.PALM_PLANKS, MCD_ItemTags.PALM_LOGS, 4);
        offerBarkBlockRecipe(exporter, MCD_Blocks.PALM_WOOD, MCD_Blocks.PALM_BEAM);
        offerBarkBlockRecipe(exporter, MCD_Blocks.STRIPPED_PALM_WOOD, MCD_Blocks.STRIPPED_PALM_BEAM);
        offerHangingSignRecipe(exporter, MCD_Items.PALM_HANGING_SIGN, MCD_Blocks.STRIPPED_PALM_BEAM);
        offerBoatRecipe(exporter, MCD_Items.PALM_BOAT, MCD_Blocks.PALM_PLANKS);
        offerChestBoatRecipe(exporter, MCD_Items.PALM_CHEST_BOAT, MCD_Items.PALM_BOAT);
    }
}
