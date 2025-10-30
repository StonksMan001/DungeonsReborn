package net.stonksman01.dungeons_reborn.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.MossBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.stonksman01.dungeons_reborn.registries.MCD_ConfiguredFeatures;

public class MidnightMossBlock extends MossBlock {
    public MidnightMossBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.getRegistryManager()
                .getOptional(RegistryKeys.CONFIGURED_FEATURE)
                .flatMap(key -> key.getEntry(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL))
                .ifPresent(entry -> (entry.value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up()));
    }
}
