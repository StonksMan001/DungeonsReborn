package net.qbaesz13.dungeons_reborn.registries.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_BiomeModifications {
    private static void init() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.PALM_COMMON);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.PALM_UNCOMMON);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.PALM_RARE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.SOUR_BERRY_BUSH_PATCH_COMMON);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.SOUR_BERRY_BUSH_PATCH_RARE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.STONY_PEAKS),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.HIGHLAND_MOSS_PATCH_RARE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MUSHROOM_FIELDS),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.MIDNIGHT_MOSS_PATCH_COMMON);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MUSHROOM_FIELDS),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.POP_FLOWER_PATCH_COMMON);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION, MCD_PlacedFeatures.MIDNIGHT_MOSS_PATCH_RARE);
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}