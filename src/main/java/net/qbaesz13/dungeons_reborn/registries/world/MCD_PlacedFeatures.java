package net.qbaesz13.dungeons_reborn.registries.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;

import java.lang.invoke.MethodHandles;

public class MCD_PlacedFeatures {
    public static final ResourceKey<PlacedFeature> PALM_COMMON = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("palm_common");
    public static final ResourceKey<PlacedFeature> PALM_UNCOMMON = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("palm_uncommon");
    public static final ResourceKey<PlacedFeature> PALM_RARE = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("palm_rare");
    public static final ResourceKey<PlacedFeature> SOUR_BERRY_BUSH_PATCH_COMMON = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("sour_berry_bush_patch_common");
    public static final ResourceKey<PlacedFeature> SOUR_BERRY_BUSH_PATCH_RARE = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("sour_berry_bush_patch_rare");
    public static final ResourceKey<PlacedFeature> POP_FLOWER_PATCH_COMMON = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("pop_flower_patch_common");
    public static final ResourceKey<PlacedFeature> MIDNIGHT_MOSS_PATCH_RARE = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("midnight_moss_patch_rare");
    public static final ResourceKey<PlacedFeature> MIDNIGHT_MOSS_PATCH_COMMON = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("midnight_moss_patch_common");
    public static final ResourceKey<PlacedFeature> HIGHLAND_MOSS_PATCH_RARE = SkyCore.RegistryPresets.createPlacedFeatureResourceKey("highland_moss_patch_rare");

    public static void bootstrap(BootstrapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter = ctx.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(ctx, PALM_COMMON, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.PALM),
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                CountPlacement.of(58),
                RandomOffsetPlacement.ofTriangle(16, 4),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.defaultBlockState(), BlockPos.ZERO)),
                BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.defaultBlockState(), BlockPos.ZERO.above())))
        );
        PlacementUtils.register(ctx, PALM_UNCOMMON, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.PALM),
                RarityFilter.onAverageOnceEvery(37),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                CountPlacement.of(25),
                RandomOffsetPlacement.ofTriangle(12, 4),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.defaultBlockState(), BlockPos.ZERO)),
                BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.defaultBlockState(), BlockPos.ZERO.above())))
        );
        PlacementUtils.register(ctx, PALM_RARE, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.PALM),
                RarityFilter.onAverageOnceEvery(59),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                CountPlacement.of(25),
                RandomOffsetPlacement.ofTriangle(12, 4),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.defaultBlockState(), BlockPos.ZERO)),
                BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.defaultBlockState(), BlockPos.ZERO.above())))
        );
        PlacementUtils.register(ctx, SOUR_BERRY_BUSH_PATCH_COMMON, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.SOUR_BERRY_BUSH),
                RarityFilter.onAverageOnceEvery(32),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(96),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.GRASS_BLOCK))
                )
        );
        PlacementUtils.register(ctx, SOUR_BERRY_BUSH_PATCH_RARE, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.SOUR_BERRY_BUSH),
                RarityFilter.onAverageOnceEvery(384),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(96),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.GRASS_BLOCK))
                )
        );
        PlacementUtils.register(ctx, POP_FLOWER_PATCH_COMMON, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.POP_FLOWER_PATCH),
                RarityFilter.onAverageOnceEvery(32),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(96),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.MYCELIUM))
                )
        );
        PlacementUtils.register(ctx, MIDNIGHT_MOSS_PATCH_COMMON, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL),
                RarityFilter.onAverageOnceEvery(32),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_NO_LEAVES,
                BiomeFilter.biome()
        );
        PlacementUtils.register(ctx, MIDNIGHT_MOSS_PATCH_RARE, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL),
                RarityFilter.onAverageOnceEvery(384),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_NO_LEAVES,
                BiomeFilter.biome()
        );
        PlacementUtils.register(ctx, HIGHLAND_MOSS_PATCH_RARE, configuredFeatureHolderGetter.getOrThrow(MCD_ConfiguredFeatures.HIGHLAND_MOSS_PATCH_BONEMEAL),
                RarityFilter.onAverageOnceEvery(384),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_NO_LEAVES,
                BiomeFilter.biome()
        );
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
