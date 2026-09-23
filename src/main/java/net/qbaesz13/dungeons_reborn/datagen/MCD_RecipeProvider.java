package net.qbaesz13.dungeons_reborn.datagen;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_ItemTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

public class MCD_RecipeProvider extends SkyCoreDataGenAPI.SC_RecipeProvider {
    public MCD_RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override @NullMarked
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                nineBlockStorageRecipes(RecipeCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.ANCIENT_GOLD_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, MCD_Items.RAW_ANCIENT_GOLD, RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
                oreSmelting(ImmutableList.of(MCD_Items.RAW_ANCIENT_GOLD), RecipeCategory.MISC, CookingBookCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, 1.0F, 200, "ancient_gold_ingot");
                oreBlasting(ImmutableList.of(MCD_Items.RAW_ANCIENT_GOLD), RecipeCategory.MISC, CookingBookCategory.MISC, MCD_Items.ANCIENT_GOLD_INGOT, 1.0F, 100, "ancient_gold_ingot");

                carpet(MCD_Blocks.HIGHLAND_MOSS_CARPET, MCD_Blocks.HIGHLAND_MOSS_BLOCK);
                carpet(MCD_Blocks.MIDNIGHT_MOSS_CARPET, MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
                offerMossyVariantRecipes(this, output, MCD_Blocks.MOSSY_OAK_PLANKS, Blocks.OAK_PLANKS);
                offerMossyVariantRecipes(this, output, MCD_Blocks.MOSSY_SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS);
                shapeless(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE)
                        .requires(Blocks.COBBLESTONE)
                        .requires(MCD_Blocks.MIDNIGHT_MOSS_BLOCK)
                        .unlockedBy(getHasName(MCD_Blocks.MIDNIGHT_MOSS_BLOCK), has(MCD_Blocks.MIDNIGHT_MOSS_BLOCK))
                        .unlockedBy(getHasName(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE), has(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE))
                        .unlockedBy(getHasName(Blocks.COBBLESTONE), has(Blocks.COBBLESTONE))
                        .save(output);

                shaped(RecipeCategory.TOOLS, MCD_Items.LADLE)
                        .define('X', Items.WOODEN_SHOVEL)
                        .define('Y', Items.LEATHER)
                        .define('Z', Items.STICK)
                        .pattern("  X")
                        .pattern(" Y ")
                        .pattern("Z  ")
                        .unlockedBy(getHasName(Items.WOODEN_SHOVEL), has(Items.WOODEN_SHOVEL))
                        .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.COMBAT, MCD_Items.SHORTBOW)
                        .define('#', Items.STICK)
                        .define('X', Items.STRING)
                        .pattern("#X")
                        .pattern("X ")
                        .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                        .save(output);
                shaped(RecipeCategory.COMBAT, MCD_Items.LONGBOW)
                        .define('#', Items.STICK)
                        .define('X', Items.STRING)
                        .define('Y', Items.FLINT)
                        .pattern("Y##")
                        .pattern("# X")
                        .pattern("#X ")
                        .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                        .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                        .save(output);

                generateRecipes(MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE, FeatureFlagSet.of(FeatureFlags.VANILLA));
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_STAIRS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_WALL, MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);

                shapeless(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.PALM_PLANKS)
                        .requires(Ingredient.of(MCD_Blocks.PALM_TRUNK, MCD_Blocks.STRIPPED_PALM_TRUNK))
                        .unlockedBy(getHasName(MCD_Blocks.PALM_TRUNK), has(MCD_Blocks.PALM_TRUNK))
                        .unlockedBy(getHasName(MCD_Blocks.STRIPPED_PALM_TRUNK), has(MCD_Blocks.STRIPPED_PALM_TRUNK))
                        .save(output, "palm_planks_from_trunk");
                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.PALM_BEAM, MCD_Blocks.PALM_TRUNK);
                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, MCD_Blocks.STRIPPED_PALM_BEAM, MCD_Blocks.STRIPPED_PALM_TRUNK);
                generateRecipes(MCD_BlockFamilies.PALM, FeatureFlagSet.of(FeatureFlags.VANILLA));
                planksFromLog(MCD_Blocks.PALM_PLANKS, MCD_ItemTags.PALM_LOGS, 4);
                woodFromLogs(MCD_Blocks.PALM_WOOD, MCD_Blocks.PALM_BEAM);
                woodFromLogs(MCD_Blocks.STRIPPED_PALM_WOOD, MCD_Blocks.STRIPPED_PALM_BEAM);
                hangingSign(MCD_Items.PALM_HANGING_SIGN, MCD_Blocks.STRIPPED_PALM_BEAM);
                woodenBoat(MCD_Items.PALM_BOAT, MCD_Blocks.PALM_PLANKS);
                chestBoat(MCD_Items.PALM_CHEST_BOAT, MCD_Items.PALM_BOAT);
            }
        };
    }
    @Override
    public @NonNull String getName() {
        return this.getClass().getName();
    }

}
