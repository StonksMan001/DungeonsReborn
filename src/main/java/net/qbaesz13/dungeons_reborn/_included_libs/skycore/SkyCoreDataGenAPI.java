package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.*;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.qbaesz13.dungeons_reborn.registries.client.MCD_Models;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SkyCoreDataGenAPI {
    public abstract static class SC_BlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public SC_BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }
        protected void createStoneSetTags(BlockFamily stoneFamily) {
            addToTagIfPresent(BlockTags.PICKAXE_MINEABLE, Set.of(
                    stoneFamily.getBaseBlock(),
                    stoneFamily.getVariant(BlockFamily.Variant.STAIRS),
                    stoneFamily.getVariant(BlockFamily.Variant.SLAB)
            ));
            addToTagIfPresent(BlockTags.STAIRS, stoneFamily.getVariant(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(BlockTags.SLABS, stoneFamily.getVariant(BlockFamily.Variant.SLAB));
            addToTagIfPresent(BlockTags.WALLS, stoneFamily.getVariant(BlockFamily.Variant.WALL));
        }
        protected void createWoodSetTags(TagKey<Block> tagForLogs, Block log, Block wood, Block strippedLog, Block strippedWood,
                                         HangingSignBlock hangingSign, WallHangingSignBlock wallHangingSign, SaplingBlock sapling,
                                         BlockFamily woodFamily, boolean isLogNatural) {
            getOrCreateTagBuilder(tagForLogs).add(log, wood, strippedLog, strippedWood);
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(tagForLogs);

            addToTagIfPresent(BlockTags.PLANKS, woodFamily.getBaseBlock());
            addToTagIfPresent(BlockTags.WOODEN_STAIRS, woodFamily.getVariant(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(BlockTags.WOODEN_SLABS, woodFamily.getVariant(BlockFamily.Variant.SLAB));
            addToTagIfPresent(BlockTags.WOODEN_FENCES, woodFamily.getVariant(BlockFamily.Variant.FENCE));
            addToTagIfPresent(BlockTags.FENCE_GATES, woodFamily.getVariant(BlockFamily.Variant.FENCE_GATE));
            addToTagIfPresent(BlockTags.WOODEN_DOORS, woodFamily.getVariant(BlockFamily.Variant.DOOR));
            addToTagIfPresent(BlockTags.WOODEN_TRAPDOORS, woodFamily.getVariant(BlockFamily.Variant.TRAPDOOR));
            addToTagIfPresent(BlockTags.WOODEN_PRESSURE_PLATES, woodFamily.getVariant(BlockFamily.Variant.PRESSURE_PLATE));
            addToTagIfPresent(BlockTags.WOODEN_BUTTONS, woodFamily.getVariant(BlockFamily.Variant.BUTTON));
            addToTagIfPresent(BlockTags.STANDING_SIGNS, woodFamily.getVariant(BlockFamily.Variant.SIGN));
            addToTagIfPresent(BlockTags.WALL_SIGNS, woodFamily.getVariant(BlockFamily.Variant.WALL_SIGN));
            addToTagIfPresent(BlockTags.CEILING_HANGING_SIGNS, hangingSign);
            addToTagIfPresent(BlockTags.WALL_HANGING_SIGNS, wallHangingSign);

            addToTagIfPresent(BlockTags.SAPLINGS, sapling);

            if (isLogNatural) getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(log);
        }
        protected void createGrassTags(Block grass) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(grass);
            getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(grass);
            getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(grass);
        }
        protected void addToTagIfPresent(TagKey<Block> tag, @Nullable Block block) {
            if (block != null) getOrCreateTagBuilder(tag).add(block);
        }
        protected void addToTagIfPresent(TagKey<Block> tag, Set<@Nullable Block> blockList) {
            for (Block block : blockList) addToTagIfPresent(tag, block);
        }
    }
    public abstract static class SC_ItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public SC_ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }
        protected void createStoneSetTags(BlockFamily stoneFamily) {
            addToTagIfPresent(ItemTags.STAIRS, stoneFamily.getVariant(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(ItemTags.SLABS, stoneFamily.getVariant(BlockFamily.Variant.SLAB));
            addToTagIfPresent(ItemTags.WALLS, stoneFamily.getVariant(BlockFamily.Variant.WALL));
        }
        protected void createWoodSetTags(TagKey<Item> tagForLogs, ItemConvertible log, ItemConvertible wood, ItemConvertible strippedLog,
                                         ItemConvertible strippedWood, ItemConvertible sapling, BlockFamily woodFamily) {
            createWoodSetTags(tagForLogs, log.asItem(), wood.asItem(), strippedLog.asItem(), strippedWood.asItem(), sapling.asItem(), woodFamily);
        }
        protected void createWoodSetTags(TagKey<Item> tagForLogs, Item log, Item wood, Item strippedLog, Item strippedWood, Item sapling,
                                         BlockFamily woodFamily) {
            getOrCreateTagBuilder(tagForLogs).add(log, wood, strippedLog, strippedWood);
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).addTag(tagForLogs);

            addToTagIfPresent(ItemTags.PLANKS, woodFamily.getBaseBlock());
            addToTagIfPresent(ItemTags.WOODEN_STAIRS, woodFamily.getVariant(BlockFamily.Variant.STAIRS));
            addToTagIfPresent(ItemTags.WOODEN_SLABS, woodFamily.getVariant(BlockFamily.Variant.SLAB));
            addToTagIfPresent(ItemTags.WOODEN_FENCES, woodFamily.getVariant(BlockFamily.Variant.FENCE));
            addToTagIfPresent(ItemTags.FENCE_GATES, woodFamily.getVariant(BlockFamily.Variant.FENCE_GATE));
            addToTagIfPresent(ItemTags.WOODEN_DOORS, woodFamily.getVariant(BlockFamily.Variant.DOOR));
            addToTagIfPresent(ItemTags.WOODEN_TRAPDOORS, woodFamily.getVariant(BlockFamily.Variant.TRAPDOOR));
            addToTagIfPresent(ItemTags.WOODEN_PRESSURE_PLATES, woodFamily.getVariant(BlockFamily.Variant.PRESSURE_PLATE));
            addToTagIfPresent(ItemTags.WOODEN_BUTTONS, woodFamily.getVariant(BlockFamily.Variant.BUTTON));
            addToTagIfPresent(ItemTags.SIGNS, woodFamily.getVariant(BlockFamily.Variant.SIGN));

            addToTagIfPresent(ItemTags.SAPLINGS, sapling);
        }
        protected void addToTagIfPresent(TagKey<Item> tag, @Nullable ItemConvertible item) {
            if (item != null) getOrCreateTagBuilder(tag).add(item.asItem());
        }
        protected void addToTagIfPresent(TagKey<Item> tag, Set<@Nullable ItemConvertible> itemList) {
            for (ItemConvertible item : itemList) addToTagIfPresent(tag, item);
        }
    }
    public abstract static class SC_BlockLootTableProvider extends FabricBlockLootTableProvider {
        protected SC_BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }
        /**
         * Based on {@link VanillaBlockLootTableGenerator#generate}
         */
        protected void addBerryBushDrops(Block berryBush, Item drop) {
            RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
            this.addDrop(
                    berryBush,
                    block -> this.applyExplosionDecay(
                            block,
                            LootTable.builder()
                                    .pool(
                                            LootPool.builder()
                                                    .conditionally(
                                                            BlockStatePropertyLootCondition.builder(berryBush).properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 3))
                                                    )
                                                    .with(ItemEntry.builder(drop))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                    )
                                    .pool(
                                            LootPool.builder()
                                                    .conditionally(
                                                            BlockStatePropertyLootCondition.builder(berryBush).properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 2))
                                                    )
                                                    .with(ItemEntry.builder(drop))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                    )
                    )
            );
        }
    }
    public abstract static class SC_ModelProvider extends FabricModelProvider {
        public SC_ModelProvider(FabricDataOutput output) {
            super(output);
        }
        protected void registerRotatedWoolAndCarpet(BlockStateModelGenerator bsmg, Block wool, Block carpet) {
            bsmg.registerRotatable(wool);
            Identifier identifier = TexturedModel.CARPET.get(wool).upload(carpet, bsmg.modelCollector);
            bsmg.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(carpet, identifier));
        }
        protected void registerBlockWith2Variants(BlockStateModelGenerator bsmg, Block block) {
            Identifier identifier1 = bsmg.createSubModel(block, "1", Models.CUBE_ALL, TextureMap::all);
            Identifier identifier2 = bsmg.createSubModel(block, "2", Models.CUBE_ALL, TextureMap::all);
            BlockStateVariant var1 = BlockStateVariant.create().put(VariantSettings.MODEL, identifier1);
            BlockStateVariant var2 = BlockStateVariant.create().put(VariantSettings.MODEL, identifier2);
            bsmg.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, var1, var2));
            bsmg.registerParentedItemModel(block, identifier1);
        }
        protected void registerBerryBushBlock(BlockStateModelGenerator bsmg, Block berryBushBlock) {
            bsmg.blockStateCollector
                    .accept(
                            VariantsBlockStateSupplier.create(berryBushBlock)
                                    .coordinate(
                                            BlockStateVariantMap.create(Properties.AGE_3)
                                                    .register(
                                                            stage -> BlockStateVariant.create()
                                                                    .put(VariantSettings.MODEL, bsmg.createSubModel(berryBushBlock, "_stage" + stage, Models.CROSS, TextureMap::cross))
                                                    )
                                    )
                    );
        }
        protected void registerTrunkBlock(BlockStateModelGenerator bsmg, Block trunk) {
            Identifier identifier = MCD_Models.TRUNK.upload(trunk, TextureMap.sideAndEndForTop(trunk), bsmg.modelCollector);
            Identifier identifier2 = MCD_Models.TRUNK_HORIZONTAL.upload(trunk, TextureMap.sideAndEndForTop(trunk), bsmg.modelCollector);
            bsmg.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(trunk, identifier, identifier2));
            bsmg.registerParentedItemModel(trunk, identifier);
        }
        protected void createStoneSetModels(BlockStateModelGenerator bsmg, BlockFamily stoneFamily) {
            BlockStateModelGenerator.BlockTexturePool texturePool = bsmg.registerCubeAllModelTexturePool(stoneFamily.getBaseBlock());
            texturePool.stairs(stoneFamily.getVariant(BlockFamily.Variant.STAIRS));
            texturePool.slab(stoneFamily.getVariant(BlockFamily.Variant.SLAB));
            texturePool.wall(stoneFamily.getVariant(BlockFamily.Variant.WALL));
        }
        protected void createWoodSetModels(BlockStateModelGenerator bsmg, Block log, Block wood, Block strippedLog, Block strippedWood,
                                           HangingSignBlock hangingSign, WallHangingSignBlock wallHangingSign, BlockFamily woodFamily) {
            bsmg.registerLog(log).log(log).wood(wood);
            bsmg.registerLog(strippedLog).log(strippedLog).wood(strippedWood);
            BlockStateModelGenerator.BlockTexturePool texturePool = bsmg.registerCubeAllModelTexturePool(woodFamily.getBaseBlock());
            texturePool.family(woodFamily);
            bsmg.registerHangingSign(strippedLog, hangingSign, wallHangingSign);
        }
    }
    public abstract static class SC_RecipeProvider extends FabricRecipeProvider {
        public SC_RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }
        protected void offerMossyVariantRecipes(RecipeExporter exporter, Block mossyBlock, Block baseBlock) {
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
}
