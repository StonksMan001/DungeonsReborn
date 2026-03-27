package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.blocks.SourBerryBushBlock;

import java.lang.invoke.MethodHandles;

public class MCD_ConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_VEGETATION = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("midnight_moss_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_MOSS_PATCH_BONEMEAL = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("midnight_moss_parch_bonemeal");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_VEGETATION = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("highland_moss_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIGHLAND_MOSS_PATCH_BONEMEAL = SkyCore.BuiltinRegistries.ofConfiguredFeatureRegistry("highland_moss_parch_bonemeal");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> ctx) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        registerMossFeatures(ctx, MIDNIGHT_MOSS_VEGETATION, MIDNIGHT_MOSS_PATCH_BONEMEAL, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, Pool.<BlockState>builder()
                .add(MCD_Blocks.MIDNIGHT_MOSS_CARPET.getDefaultState(), 25)
                .add(MCD_Blocks.MIDNIGHT_SPROUTS.getDefaultState(), 50)
                .add(MCD_Blocks.POP_FLOWER.getDefaultState(), 10).build());
        registerMossFeatures(ctx, HIGHLAND_MOSS_VEGETATION, HIGHLAND_MOSS_PATCH_BONEMEAL, MCD_Blocks.HIGHLAND_MOSS_BLOCK, Pool.<BlockState>builder()
                .add(MCD_Blocks.HIGHLAND_MOSS_CARPET.getDefaultState(), 25)
                .add(Blocks.SHORT_GRASS.getDefaultState(), 50)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.getDefaultState().with(SourBerryBushBlock.AGE, 0), 5)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.getDefaultState().with(SourBerryBushBlock.AGE, 1), 2)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.getDefaultState().with(SourBerryBushBlock.AGE, 2), 2)
                .add(MCD_Blocks.SOUR_BERRY_BUSH.getDefaultState().with(SourBerryBushBlock.AGE, 3), 1).build());
    }
    private static void registerMossFeatures(Registerable<ConfiguredFeature<?, ?>> ctx, RegistryKey<ConfiguredFeature<?, ?>> vegetation,
                                             RegistryKey<ConfiguredFeature<?, ?>> bonemeal_patch, Block base, Pool<BlockState> states) {
        ConfiguredFeatures.register(
                ctx,
                vegetation,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(states))
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
