package net.stonksman01.dungeons_reborn._included_libs.skycore.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MossBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.stonksman01.dungeons_reborn.registries.MCD_ConfiguredFeatures;

public class SC_MossBlock extends MossBlock {
    private final RegistryKey<ConfiguredFeature<?, ?>> mossPatchFeature;
    public SC_MossBlock(AbstractBlock.Settings settings, RegistryKey<ConfiguredFeature<?, ?>> mossPatchFeature) {
        super(settings);
        this.mossPatchFeature = mossPatchFeature;
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.getRegistryManager()
                .getOptional(RegistryKeys.CONFIGURED_FEATURE)
                .flatMap(key -> key.getEntry(mossPatchFeature))
                .ifPresent(entry -> (entry.value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up()));
    }
}
