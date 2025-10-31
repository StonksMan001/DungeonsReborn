package net.stonksman01.dungeons_reborn.registries;

import net.minecraft.block.BlockState;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;

public class MCD_ConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_PATCH_BONEMEAL = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("midnight_moss_parch_bonemeal");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_VEGETATION = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("midnight_moss_vegetation");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> ctx) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        ConfiguredFeatures.register(
                ctx,
                MIDNIGHT_MOSS_VEGETATION,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                DataPool.<BlockState>builder()
                                        .add(MCD_Blocks.MIDNIGHT_MOSS_CARPET.getDefaultState(), 25)
                                        .add(MCD_Blocks.MIDNIGHT_SPROUTS.getDefaultState(), 50)
                                        .add(MCD_Blocks.POP_FLOWER.getDefaultState(), 10)
                        )
                )
        );
        SkyCore.BuiltinRegistries.registerConfiguredFeature(
                ctx,
                MIDNIGHT_MOSS_PATCH_BONEMEAL,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(MCD_Blocks.MIDNIGHT_MOSS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(MIDNIGHT_MOSS_VEGETATION)),
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
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering ConfiguredFeatures");
    }
}
