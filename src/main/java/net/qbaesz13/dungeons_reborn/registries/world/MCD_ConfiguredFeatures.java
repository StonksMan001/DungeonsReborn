package net.qbaesz13.dungeons_reborn.registries.world;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
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
    public static final ResourceKey<Feature> MIDNIGHT_MOSS_VEGETATION = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_vegetation");
    public static final ResourceKey<Feature> MIDNIGHT_MOSS_PATCH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_patch");
    public static final ResourceKey<Feature> MIDNIGHT_MOSS_PATCH_BONEMEAL = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_patch_bonemeal");
    public static final ResourceKey<Feature> HIGHLAND_MOSS_VEGETATION = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_vegetation");
    public static final ResourceKey<Feature> HIGHLAND_MOSS_PATCH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_patch");
    public static final ResourceKey<Feature> HIGHLAND_MOSS_PATCH_BONEMEAL = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_patch_bonemeal");
    public static final ResourceKey<Feature> PALM = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("palm");
    public static final ResourceKey<Feature> SOUR_BERRY_BUSH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("sour_berry_bush");
    public static final ResourceKey<Feature> POP_FLOWER_PATCH = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("pop_flower");

    public static void bootstrap(BootstrapContext<Feature> ctx) {
        HolderGetter<Feature> configuredFeatureHolderGetter = ctx.lookup(Registries.FEATURE);

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
        ctx.register(PALM, new BlockColumnFeature(
                        List.of(
                                BlockColumnFeature.layer(
                                        new WeightedListInt(
                                                WeightedList.<IntProvider>builder()
                                                        .add(UniformInt.of(8, 10), 3)
                                                        .add(UniformInt.of(6, 7), 10)
                                                        .add(UniformInt.of(1, 3), 1)
                                                        .build()
                                        ),
                                        BlockStateProvider.of(MCD_Blocks.PALM_TRUNK)
                                ),
                                BlockColumnFeature.layer(
                                        ConstantInt.of(1),
                                        BlockStateProvider.of(MCD_Blocks.PALM_LEAVES.defaultBlockState()
                                                .setValue(LeavesBlock.PERSISTENT, false)
                                                .setValue(LeavesBlock.DISTANCE, 1))
                                )
                        ),
                        Direction.UP,
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        true
                )
        );
        ctx.register(SOUR_BERRY_BUSH, new SimpleBlockFeature(BlockStateProvider.of(MCD_Blocks.SOUR_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))));
        ctx.register(POP_FLOWER_PATCH, new SimpleBlockFeature(BlockStateProvider.of(MCD_Blocks.POP_FLOWER)));
    }
    /**
     * Based on {@link net.minecraft.data.worldgen.features.CaveFeatures#bootstrap}
     */
    private static void registerMossFeatures(BootstrapContext<Feature> ctx, ResourceKey<Feature> vegetation, ResourceKey<Feature> patch,
                                             ResourceKey<Feature> bonemeal_patch, Block base, WeightedList<BlockState> states) {
        ctx.register(
                vegetation,
                new SimpleBlockFeature(new WeightedStateProvider(states))
        );
        ctx.register(
                patch,
                new VegetationPatchFeature(
                        ctx.lookup(Registries.BLOCK).getOrThrow(BlockTags.MOSS_REPLACEABLE),
                        BlockStateProvider.holderOf(base),
                        PlacementUtils.inlinePlaced(ctx.lookup(Registries.FEATURE).getOrThrow(vegetation)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0f,
                        5,
                        0.8f,
                        UniformInt.of(4, 7),
                        0.3f
                )
        );
        ctx.register(
                bonemeal_patch,
                new VegetationPatchFeature(
                        ctx.lookup(Registries.BLOCK).getOrThrow(BlockTags.MOSS_REPLACEABLE),
                        BlockStateProvider.holderOf(base),
                        PlacementUtils.inlinePlaced(ctx.lookup(Registries.FEATURE).getOrThrow(vegetation)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0f,
                        5,
                        0.6f,
                        UniformInt.of(1, 2),
                        0.75f
                )
        );
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}