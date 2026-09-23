package net.qbaesz13.dungeons_reborn.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

public class PalmLeavesBlock extends LeavesBlock {
    public static final MapCodec<PalmLeavesBlock> CODEC = simpleCodec(PalmLeavesBlock::new);
    public PalmLeavesBlock(Properties properties) {
        super(0f, properties);
    }

    @Override @NullMarked
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(3.0, 0.0, 3.0, 13.0, 10.0, 13.0);
    }
    @Override @NullMarked
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        return !state.canSurvive(level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }
    @Override
    protected boolean canBeReplaced(@NonNull BlockState state, BlockPlaceContext context) {
        return !context.getItemInHand().is(MCD_Items.PALM_LEAVES);
    }
    @Override @NullMarked
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos.below());
        return blockState.is(BlockTags.LOGS) && blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y;
    }
    @Override
    public @NonNull MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }
    @Override @NullMarked
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {}
}
