package net.qbaesz13.dungeons_reborn._included_libs.skycore.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class SC_ShortPlantBlock extends TallGrassBlock {
    public SC_ShortPlantBlock(Properties properties) {
        super(properties);
    }
    @Override @NullMarked
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return false;
    }
    @Override @NullMarked
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }
    @Override @NullMarked
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {}
}
