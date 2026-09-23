package net.qbaesz13.dungeons_reborn.registries.world;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.blocks.SourBerryBushBlock;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class MCD_ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_VEGETATION = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_PATCH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_PATCH_BONEMEAL = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_VEGETATION = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_PATCH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_PATCH_BONEMEAL = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUR_BERRY_BUSH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("sour_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POP_FLOWER_PATCH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("pop_flower");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter = ctx.lookup(Registries.CONFIGURED_FEATURE);

        registerMossFeatures(ctx, MIDNIGHT_MOSS_VEGETATION, MIDNIGHT_MOSS_PATCH, MIDNIGHT_MOSS_PATCH_BONEMEAL, MCD_Blocks.MIDNIGHT_MOSS_BLOCK,
                WeightedList.<BlockState>builder()
                        .add(MCD_Blocks.MIDNIGHT_MOSS_CARPET.defaultBlockState(), 25)
                        .add(MCD_Blocks.POP_FLOWER.defaultBlockState(), 54)
                        .add(MCD_Blocks.MIDNIGHT_SPROUTS.defaultBlockState(), 17).build());
        registerMossFeatures(ctx, HIGHLAND_MOSS_VEGETATION, HIGHLAND_MOSS_PATCH, HIGHLAND_MOSS_PATCH_BONEMEAL, MCD_Blocks.HIGHLAND_MOSS_BLOCK,
                WeightedList.<BlockState>builder()
                        .add(MCD_Blocks.SOUR_BERRY_BUSH.defaultBlockState().setValue(SourBerryBushBlock.AGE, 0), 6)
                        .add(MCD_Blocks.HIGHLAND_MOSS_CARPET.defaultBlockState(), 25)
                        .add(MCD_Blocks.MEDIUM_HIGHLAND_GRASS.defaultBlockState(), 50)
                        .add(MCD_Blocks.SHORT_HIGHLAND_GRASS.defaultBlockState(), 15).build());
        FeatureUtils.register(ctx, PALM, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                        List.of(
                                BlockColumnConfiguration.layer(
                                        new WeightedListInt(
                                                WeightedList.<IntProvider>builder()
                                                        .add(UniformInt.of(8, 10), 3)
                                                        .add(UniformInt.of(6, 7), 10)
                                                        .add(UniformInt.of(1, 3), 1)
                                                        .build()
                                        ),
                                        BlockStateProvider.simple(MCD_Blocks.PALM_TRUNK)
                                ),
                                BlockColumnConfiguration.layer(
                                        ConstantInt.of(1),
                                        BlockStateProvider.simple(MCD_Blocks.PALM_LEAVES.defaultBlockState()
                                                .setValue(LeavesBlock.PERSISTENT, false)
                                                .setValue(LeavesBlock.DISTANCE, 1))
                                )
                        ),
                        Direction.UP,
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        true
                )
        );
        FeatureUtils.register(ctx, SOUR_BERRY_BUSH,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(MCD_Blocks.SOUR_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3)))
        );
        FeatureUtils.register(ctx, POP_FLOWER_PATCH,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(MCD_Blocks.POP_FLOWER))
        );
    }
    /**
     * Based on {@link net.minecraft.data.worldgen.features.CaveFeatures#bootstrap}
     */
    private static void registerMossFeatures(BootstrapContext<ConfiguredFeature<?, ?>> ctx, ResourceKey<ConfiguredFeature<?, ?>> vegetation,
                                             ResourceKey<ConfiguredFeature<?, ?>> patch, ResourceKey<ConfiguredFeature<?, ?>> bonemeal_patch, Block base, WeightedList<BlockState> states) {
        FeatureUtils.register(
                ctx,
                vegetation,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(states))
        );
        FeatureUtils.register(
                ctx,
                patch,
                Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.simple(base),
                        PlacementUtils.inlinePlaced(ctx.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(vegetation)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        5,
                        0.8F,
                        UniformInt.of(4, 7),
                        0.3F
                )
        );
        FeatureUtils.register(
                ctx,
                bonemeal_patch,
                Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.simple(base),
                        PlacementUtils.inlinePlaced(ctx.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(vegetation)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        5,
                        0.6F,
                        UniformInt.of(1, 2),
                        0.75F
                )
        );
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}