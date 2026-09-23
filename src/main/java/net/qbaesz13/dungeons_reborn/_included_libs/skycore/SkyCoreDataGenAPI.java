package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.qbaesz13.dungeons_reborn.registries.client.MCD_ModelTemplates;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SkyCoreDataGenAPI {
    public abstract static class SC_BlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
        public SC_BlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
            super(output, registryLookupFuture);
        }
        protected void createStoneSetTags(BlockFamily stoneFamily) {
            addToTagIfPresent(BlockTags.MINEABLE_WITH_PICKAXE, Set.of(
                    stoneFamily.getBaseBlock(),
                    stoneFamily.get(BlockFamily.Variant.STAIRS),
                    stoneFamily.get(BlockFamily.Variant.SLAB)
            ));
            addToTagIfPresent(BlockTags.STAIRS, stoneFamily.get(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(BlockTags.SLABS, stoneFamily.get(BlockFamily.Variant.SLAB));
            addToTagIfPresent(BlockTags.WALLS, stoneFamily.get(BlockFamily.Variant.WALL));
        }
        protected void createWoodSetTags(TagKey<Block> tagForLogs, Block log, Block wood, Block strippedLog, Block strippedWood,
                                         CeilingHangingSignBlock ceilingHangingSign, WallHangingSignBlock wallHangingSign, SaplingBlock sapling,
                                         BlockFamily woodFamily, boolean isLogNatural) {
            valueLookupBuilder(tagForLogs).add(log, wood, strippedLog, strippedWood);
            valueLookupBuilder(BlockTags.LOGS_THAT_BURN).addTag(tagForLogs);

            addToTagIfPresent(BlockTags.PLANKS, woodFamily.getBaseBlock());
            addToTagIfPresent(BlockTags.WOODEN_STAIRS, woodFamily.get(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(BlockTags.WOODEN_SLABS, woodFamily.get(BlockFamily.Variant.SLAB));
            addToTagIfPresent(BlockTags.WOODEN_FENCES, woodFamily.get(BlockFamily.Variant.FENCE));
            addToTagIfPresent(BlockTags.FENCE_GATES, woodFamily.get(BlockFamily.Variant.FENCE_GATE));
            addToTagIfPresent(BlockTags.WOODEN_DOORS, woodFamily.get(BlockFamily.Variant.DOOR));
            addToTagIfPresent(BlockTags.WOODEN_TRAPDOORS, woodFamily.get(BlockFamily.Variant.TRAPDOOR));
            addToTagIfPresent(BlockTags.WOODEN_PRESSURE_PLATES, woodFamily.get(BlockFamily.Variant.PRESSURE_PLATE));
            addToTagIfPresent(BlockTags.WOODEN_BUTTONS, woodFamily.get(BlockFamily.Variant.BUTTON));
            addToTagIfPresent(BlockTags.STANDING_SIGNS, woodFamily.get(BlockFamily.Variant.SIGN));
            addToTagIfPresent(BlockTags.WALL_SIGNS, woodFamily.get(BlockFamily.Variant.WALL_SIGN));
            addToTagIfPresent(BlockTags.CEILING_HANGING_SIGNS, ceilingHangingSign);
            addToTagIfPresent(BlockTags.WALL_HANGING_SIGNS, wallHangingSign);

            addToTagIfPresent(BlockTags.SAPLINGS, sapling);

            if (isLogNatural) valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(log);
        }
        protected void createGrassTags(Block grass) {
            valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add(grass);
            valueLookupBuilder(BlockTags.SWORD_EFFICIENT).add(grass);
            valueLookupBuilder(BlockTags.REPLACEABLE_BY_TREES).add(grass);
        }
        protected void addToTagIfPresent(TagKey<Block> tag, @Nullable Block block) {
            if (block != null) valueLookupBuilder(tag).add(block);
        }
        protected void addToTagIfPresent(TagKey<Block> tag, Set<@Nullable Block> blockList) {
            for (Block block : blockList) addToTagIfPresent(tag, block);
        }
    }
    public abstract static class SC_ItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
        public SC_ItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
            super(output, registryLookupFuture);
        }
        protected void createStoneSetTags(BlockFamily stoneFamily) {
            addToTagIfPresent(ItemTags.STAIRS, stoneFamily.get(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(ItemTags.SLABS, stoneFamily.get(BlockFamily.Variant.SLAB));
            addToTagIfPresent(ItemTags.WALLS, stoneFamily.get(BlockFamily.Variant.WALL));
        }
        protected void createWoodSetTags(TagKey<Item> tagForLogs, ItemLike log, ItemLike wood, ItemLike strippedLog,
                                         ItemLike strippedWood, ItemLike sapling, BlockFamily woodFamily) {
            createWoodSetTags(tagForLogs, log.asItem(), wood.asItem(), strippedLog.asItem(), strippedWood.asItem(), sapling.asItem(), woodFamily);
        }
        protected void createWoodSetTags(TagKey<Item> tagForLogs, Item log, Item wood, Item strippedLog, Item strippedWood, Item sapling,
                                         BlockFamily woodFamily) {
            valueLookupBuilder(tagForLogs).add(log, wood, strippedLog, strippedWood);
            valueLookupBuilder(ItemTags.LOGS_THAT_BURN).addTag(tagForLogs);

            addToTagIfPresent(ItemTags.PLANKS, woodFamily.getBaseBlock());
            addToTagIfPresent(ItemTags.WOODEN_STAIRS, woodFamily.get(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(ItemTags.WOODEN_SLABS, woodFamily.get(BlockFamily.Variant.SLAB));
            addToTagIfPresent(ItemTags.WOODEN_FENCES, woodFamily.get(BlockFamily.Variant.FENCE));
            addToTagIfPresent(ItemTags.FENCE_GATES, woodFamily.get(BlockFamily.Variant.FENCE_GATE));
            addToTagIfPresent(ItemTags.WOODEN_DOORS, woodFamily.get(BlockFamily.Variant.DOOR));
            addToTagIfPresent(ItemTags.WOODEN_TRAPDOORS, woodFamily.get(BlockFamily.Variant.TRAPDOOR));
            addToTagIfPresent(ItemTags.WOODEN_PRESSURE_PLATES, woodFamily.get(BlockFamily.Variant.PRESSURE_PLATE));
            addToTagIfPresent(ItemTags.WOODEN_BUTTONS, woodFamily.get(BlockFamily.Variant.BUTTON));
            addToTagIfPresent(ItemTags.SIGNS, woodFamily.get(BlockFamily.Variant.SIGN));

            addToTagIfPresent(ItemTags.SAPLINGS, sapling);
        }
        protected void addToTagIfPresent(TagKey<Item> tag, @Nullable ItemLike item) {
            if (item != null) valueLookupBuilder(tag).add(item.asItem());
        }
        protected void addToTagIfPresent(TagKey<Item> tag, Set<@Nullable ItemLike> itemList) {
            for (ItemLike item : itemList) addToTagIfPresent(tag, item);
        }
    }
    public abstract static class SC_BlockLootSubProvider extends FabricBlockLootSubProvider {
        protected SC_BlockLootSubProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(packOutput, registriesFuture);
        }
        /**
         * Based on {@link VanillaBlockLoot#generate}
         */
        protected void addBerryBushDrops(Block berryBush, Item drop) {
            HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
            this.add(
                    berryBush,
                    block -> this.applyExplosionDecay(
                            block,
                            LootTable.lootTable()
                                    .withPool(
                                            LootPool.lootPool()
                                                    .when(
                                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(berryBush)
                                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                                    )
                                                    .add(LootItem.lootTableItem(drop))
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                                    .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                    )
                                    .withPool(
                                            LootPool.lootPool()
                                                    .when(
                                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(berryBush)
                                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                                    )
                                                    .add(LootItem.lootTableItem(drop))
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                    .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                    )
                    )
            );
        }
    }
    public abstract static class SC_ModelProvider extends FabricModelProvider {
        public SC_ModelProvider(FabricPackOutput output) {
            super(output);
        }
        protected void registerRotatedWoolAndCarpet(BlockModelGenerators bmg, Block wool, Block carpet) {
            bmg.createColoredBlockWithRandomRotations(TexturedModel.CUBE, wool);
            Variant variant = BlockModelGenerators.plainModel(TexturedModel.CARPET.get(wool).create(carpet, bmg.modelOutput));
            bmg.blockStateOutput.accept(MultiVariantGenerator.dispatch(carpet, BlockModelGenerators.createRotatedVariants(variant)));
        }
        protected void registerBlockWith2Variants(BlockModelGenerators bmg, Block block) {
            Identifier identifier1 = bmg.createSuffixedVariant(block, "1", ModelTemplates.CUBE_ALL, TextureMapping::cube);
            Identifier identifier2 = bmg.createSuffixedVariant(block, "2", ModelTemplates.CUBE_ALL, TextureMapping::cube);
            bmg.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.variants(
                    new Variant(identifier1),
                    new Variant(identifier2)
            )));
            bmg.registerSimpleItemModel(block, identifier1);
        }
        protected void registerBerryBushBlock(BlockModelGenerators bmg, Block berryBushBlock) {
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
        protected void registerTrunkBlock(BlockModelGenerators bmg, Block trunk) {
            Identifier model = MCD_ModelTemplates.TRUNK.create(trunk, TextureMapping.logColumn(trunk), bmg.modelOutput);
            MultiVariant horizontalModel = BlockModelGenerators.plainVariant(
                    MCD_ModelTemplates.TRUNK_HORIZONTAL.create(trunk, TextureMapping.logColumn(trunk), bmg.modelOutput)
            );
            bmg.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(trunk, BlockModelGenerators.plainVariant(model), horizontalModel));
            bmg.registerSimpleItemModel(trunk, model);
        }
        protected void createStoneSetModels(BlockModelGenerators bmg, BlockFamily stoneFamily) {
            BlockModelGenerators.BlockFamilyProvider texturePool = bmg.family(stoneFamily.getBaseBlock());
            texturePool.stairs(stoneFamily.get(BlockFamily.Variant.STAIRS));
            texturePool.slab(stoneFamily.get(BlockFamily.Variant.SLAB));
            texturePool.wall(stoneFamily.get(BlockFamily.Variant.WALL));
        }
        protected void createWoodSetModels(BlockModelGenerators bmg, Block log, Block wood, Block strippedLog, Block strippedWood,
                                           CeilingHangingSignBlock ceilingHangingSign, WallHangingSignBlock wallHangingSign, BlockFamily woodFamily) {
            bmg.woodProvider(log).log(log).wood(wood);
            bmg.woodProvider(strippedLog).log(strippedLog).wood(strippedWood);
            BlockModelGenerators.BlockFamilyProvider texturePool = bmg.family(woodFamily.getBaseBlock());
            texturePool.generateFor(woodFamily);
            bmg.createHangingSign(strippedLog, ceilingHangingSign, wallHangingSign);
        }
    }
    public abstract static class SC_RecipeProvider extends FabricRecipeProvider {
        public SC_RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }
        protected void offerMossyVariantRecipes(RecipeProvider recipeProvider, RecipeOutput output, Block mossyBlock, Block baseBlock) {
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
    }
}
