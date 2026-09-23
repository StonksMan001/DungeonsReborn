package net.qbaesz13.dungeons_reborn._included_libs.skycore.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class SC_ShortPlantBlock extends ShortPlantBlock {
    public SC_ShortPlantBlock(Settings settings) {
        super(settings);
    }
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return false;
    }
    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return false;
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {}
}
