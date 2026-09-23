package net.qbaesz13.dungeons_reborn.registries.world;

import net.minecraft.block.*;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.blocks.SourBerryBushBlock;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class MCD_ConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_VEGETATION = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("midnight_moss_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_PATCH = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("midnight_moss_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_PATCH_BONEMEAL = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("midnight_moss_patch_bonemeal");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_VEGETATION = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("highland_moss_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_PATCH = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("highland_moss_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_PATCH_BONEMEAL = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("highland_moss_patch_bonemeal");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALM = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("palm");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALM_PATCH = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("palm_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALM_PATCH_LARGE = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("palm_patch_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SOUR_BERRY_BUSH_PATCH = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("sour_berry_bush_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> POP_FLOWER_PATCH = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("pop_flower_patch");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> ctx) {
        RegistryEntryLookup<PlacedFeature> placedFeatureRegistries = ctx.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        registerMossFeatures(ctx, MIDNIGHT_MOSS_VEGETATION, MIDNIGHT_MOSS_PATCH, MIDNIGHT_MOSS_PATCH_BONEMEAL, MCD_Blocks.MIDNIGHT_MOSS_BLOCK,
                DataPool.<BlockState>builder()
                        .add(MCD_Blocks.MIDNIGHT_MOSS_CARPET.getDefaultState(), 25)
                        .add(MCD_Blocks.POP_FLOWER.getDefaultState(), 54)
                        .add(MCD_Blocks.MIDNIGHT_SPROUTS.getDefaultState(), 17));
        registerMossFeatures(ctx, HIGHLAND_MOSS_VEGETATION, HIGHLAND_MOSS_PATCH, HIGHLAND_MOSS_PATCH_BONEMEAL, MCD_Blocks.HIGHLAND_MOSS_BLOCK,
                DataPool.<BlockState>builder()
                        .add(MCD_Blocks.SOUR_BERRY_BUSH.getDefaultState().with(SourBerryBushBlock.AGE, 0), 6)
                        .add(MCD_Blocks.HIGHLAND_MOSS_CARPET.getDefaultState(), 25)
                        .add(MCD_Blocks.MEDIUM_HIGHLAND_GRASS.getDefaultState(), 50)
                        .add(MCD_Blocks.SHORT_HIGHLAND_GRASS.getDefaultState(), 15));
        ConfiguredFeatures.register(ctx, PALM, Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(
                        List.of(
                                BlockColumnFeatureConfig.createLayer(
                                        new WeightedListIntProvider(
                                                DataPool.<IntProvider>builder()
                                                        .add(UniformIntProvider.create(8, 10), 3)
                                                        .add(UniformIntProvider.create(6, 7), 10)
                                                        .add(UniformIntProvider.create(1, 3), 1)
                                                        .build()
                                        ),
                                        BlockStateProvider.of(MCD_Blocks.PALM_TRUNK)
                                ),
                                BlockColumnFeatureConfig.createLayer(
                                        ConstantIntProvider.create(1),
                                        BlockStateProvider.of(MCD_Blocks.PALM_LEAVES.getDefaultState()
                                                .with(LeavesBlock.PERSISTENT, false)
                                                .with(LeavesBlock.DISTANCE, 1))
                                )
                        ),
                        Direction.UP,
                        BlockPredicate.IS_AIR,
                        true
                )
        );
        ConfiguredFeatures.register(ctx, PALM_PATCH, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(
                25,
                12,
                4,
                placedFeatureRegistries.getOrThrow(MCD_PlacedFeatures.PALM_PLACED)
        ));
        ConfiguredFeatures.register(ctx, PALM_PATCH_LARGE, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(
                58,
                16,
                4,
                placedFeatureRegistries.getOrThrow(MCD_PlacedFeatures.PALM_PLACED)
        ));
        ConfiguredFeatures.register(ctx, SOUR_BERRY_BUSH_PATCH, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MCD_Blocks.SOUR_BERRY_BUSH.getDefaultState().with(SweetBerryBushBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK)
                )
        );
        ConfiguredFeatures.register(ctx, POP_FLOWER_PATCH, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MCD_Blocks.POP_FLOWER))
                )
        );
    }
    /**
     * Based on {@link UndergroundConfiguredFeatures#bootstrap}
     */
    private static void registerMossFeatures(Registerable<ConfiguredFeature<?, ?>> ctx, RegistryKey<ConfiguredFeature<?, ?>> vegetation,
                                             RegistryKey<ConfiguredFeature<?, ?>> patch, RegistryKey<ConfiguredFeature<?, ?>> bonemeal_patch, Block base, DataPool.Builder<BlockState> states) {
        ConfiguredFeatures.register(
                ctx,
                vegetation,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(states))
        );
        ConfiguredFeatures.register(
                ctx,
                patch,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(base),
                        PlacedFeatures.createEntry(ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(vegetation)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0F,
                        5,
                        0.8F,
                        UniformIntProvider.create(4, 7),
                        0.3F
                )
        );
        SkyCore.BuiltinRegistries.registerConfiguredFeature(
                ctx,
                bonemeal_patch,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(base),
                        PlacedFeatures.createEntry(ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(vegetation)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0F,
                        5,
                        0.6F,
                        UniformIntProvider.create(1, 2),
                        0.75F
                )
        );
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
