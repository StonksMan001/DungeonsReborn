package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.blocks.SourBerryBushBlock;

import java.lang.invoke.MethodHandles;

public class MCD_ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_VEGETATION = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_PATCH_BONEMEAL = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("midnight_moss_parch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_VEGETATION = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_PATCH_BONEMEAL = SkyCore.RegistryPresets.createConfiguredFeatureResourceKey("highland_moss_parch_bonemeal");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = ctx.lookup(Registries.CONFIGURED_FEATURE);
        registerMossFeatures(ctx, MIDNIGHT_MOSS_VEGETATION, MIDNIGHT_MOSS_PATCH_BONEMEAL, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, WeightedList.<BlockState>builder()
                .add(MCD_Blocks.MIDNIGHT_MOSS_CARPET.defaultBlockState(), 25)
                .add(MCD_Blocks.MIDNIGHT_SPROUTS.defaultBlockState(), 50)
                .add(MCD_Blocks.POP_FLOWER.defaultBlockState(), 10).build());
        registerMossFeatures(ctx, HIGHLAND_MOSS_VEGETATION, HIGHLAND_MOSS_PATCH_BONEMEAL, MCD_Blocks.HIGHLAND_MOSS_BLOCK, WeightedList.<BlockState>builder()
                .add(MCD_Blocks.HIGHLAND_MOSS_CARPET.defaultBlockState(), 25)
                .add(Blocks.SHORT_GRASS.defaultBlockState(), 50)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.defaultBlockState().setValue(SourBerryBushBlock.AGE, 0), 5)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.defaultBlockState().setValue(SourBerryBushBlock.AGE, 1), 2)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.defaultBlockState().setValue(SourBerryBushBlock.AGE, 2), 2)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.defaultBlockState().setValue(SourBerryBushBlock.AGE, 3), 1).build());
    }
    private static void registerMossFeatures(BootstrapContext<ConfiguredFeature<?, ?>> ctx, ResourceKey<ConfiguredFeature<?, ?>> vegetation,
                                             ResourceKey<ConfiguredFeature<?, ?>> bonemeal_patch, Block base, WeightedList<BlockState> states) {
        FeatureUtils.register(
                ctx,
                vegetation,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(states))
        );
        SkyCore.RegistryPresets.registerConfiguredFeature(
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