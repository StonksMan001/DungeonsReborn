package net.qbaesz13.dungeons_reborn.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreBlockEntityAPI;
import net.qbaesz13.dungeons_reborn.block_entities.PopFlowerBlockEntity;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockEntityTypes;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public class PopFlowerBlock extends VegetationBlock implements EntityBlock {
    public static final MapCodec<PopFlowerBlock> CODEC = simpleCodec(PopFlowerBlock::new);
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 7);
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final int HIDE_DISTANCE = 5;

    public PopFlowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(STAGE, 0).setValue(CUT, false));
    }

    public static void decrease(Level level, BlockPos pos) {
        int stage = level.getBlockState(pos).getValue(STAGE);
        if (stage != 0) level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(STAGE, stage - 1));
    }
    public static void increase(Level level, BlockPos pos) {
        int stage = level.getBlockState(pos).getValue(STAGE);
        if (stage != 7 && !level.getBlockState(pos).getValue(CUT)) level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(STAGE, stage + 1));
    }
    @Override @NullMarked
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    @Override @NullMarked
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
                                          Player playerEntity, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(Items.SHEARS) && !level.getBlockState(pos).getValue(CUT)) {
            level.setBlockAndUpdate(pos, state.setValue(CUT, true));
            level.playSound(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(itemStack, state, level, pos, playerEntity, hand, hitResult);
    }

    @Override @NullMarked
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return SkyCoreBlockEntityAPI.createTickerHelper(type, MCD_BlockEntityTypes.POP_FLOWER_BLOCK_ENTITY, PopFlowerBlockEntity::serverTick);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        builder.add(STAGE, CUT);
    }
    @Override
    protected @NonNull MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }
    @Override @NullMarked
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new PopFlowerBlockEntity(worldPosition, blockState);
    }
}
