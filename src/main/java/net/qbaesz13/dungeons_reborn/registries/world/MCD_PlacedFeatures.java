package net.qbaesz13.dungeons_reborn.registries.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;

import java.lang.invoke.MethodHandles;

public class MCD_PlacedFeatures {
    public static final RegistryKey<PlacedFeature> PALM_PLACED = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("palm_placed");
    public static final RegistryKey<PlacedFeature> PALM_COMMON = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("palm_common");
    public static final RegistryKey<PlacedFeature> PALM_UNCOMMON = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("palm_uncommon");
    public static final RegistryKey<PlacedFeature> PALM_RARE = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("palm_rare");
    public static final RegistryKey<PlacedFeature> SOUR_BERRY_BUSH_PATCH_COMMON = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("sour_berry_bush_patch_common");
    public static final RegistryKey<PlacedFeature> SOUR_BERRY_BUSH_PATCH_RARE = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("sour_berry_bush_patch_rare");
    public static final RegistryKey<PlacedFeature> POP_FLOWER_PATCH_COMMON = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("pop_flower_patch_common");
    public static final RegistryKey<PlacedFeature> MIDNIGHT_MOSS_PATCH_RARE = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("midnight_moss_patch_rare");
    public static final RegistryKey<PlacedFeature> MIDNIGHT_MOSS_PATCH_COMMON = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("midnight_moss_patch_common");
    public static final RegistryKey<PlacedFeature> HIGHLAND_MOSS_PATCH_RARE = SkyCore.BuiltinRegistries.ofPlacedFeatureRegistry("highland_moss_patch_rare");

    public static void bootstrap(Registerable<PlacedFeature> ctx) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistries = ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(ctx, PALM_PLACED, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.PALM),
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.getDefaultState(), BlockPos.ORIGIN)),
                BlockFilterPlacementModifier.of(BlockPredicate.not(BlockPredicate.wouldSurvive(MCD_Blocks.PALM_SAPLING.getDefaultState(), BlockPos.ORIGIN.up())))
        );
        PlacedFeatures.register(ctx, PALM_COMMON, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.PALM_PATCH_LARGE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(4), MCD_Blocks.PALM_SAPLING));
        PlacedFeatures.register(ctx, PALM_UNCOMMON, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.PALM_PATCH),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(37), MCD_Blocks.PALM_SAPLING));
        PlacedFeatures.register(ctx, PALM_RARE, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.PALM_PATCH),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(59), MCD_Blocks.PALM_SAPLING));
        PlacedFeatures.register(ctx, SOUR_BERRY_BUSH_PATCH_COMMON, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.SOUR_BERRY_BUSH_PATCH),
                RarityFilterPlacementModifier.of(32),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        PlacedFeatures.register(ctx, SOUR_BERRY_BUSH_PATCH_RARE, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.SOUR_BERRY_BUSH_PATCH),
                RarityFilterPlacementModifier.of(384),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        PlacedFeatures.register(ctx, POP_FLOWER_PATCH_COMMON, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.POP_FLOWER_PATCH),
                RarityFilterPlacementModifier.of(32),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        PlacedFeatures.register(ctx, MIDNIGHT_MOSS_PATCH_COMMON, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL),
                RarityFilterPlacementModifier.of(32),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        PlacedFeatures.register(ctx, MIDNIGHT_MOSS_PATCH_RARE, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL),
                RarityFilterPlacementModifier.of(384),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        PlacedFeatures.register(ctx, HIGHLAND_MOSS_PATCH_RARE, configuredFeatureRegistries.getOrThrow(MCD_ConfiguredFeatures.HIGHLAND_MOSS_PATCH_BONEMEAL),
                RarityFilterPlacementModifier.of(384),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}