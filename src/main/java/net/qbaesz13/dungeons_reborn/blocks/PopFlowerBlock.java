package net.qbaesz13.dungeons_reborn.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreBlockEntityAPI;
import net.qbaesz13.dungeons_reborn.block_entities.PopFlowerBlockEntity;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockEntityTypes;
import org.jspecify.annotations.Nullable;

public class PopFlowerBlock extends PlantBlock implements BlockEntityProvider {
    public static final MapCodec<PopFlowerBlock> CODEC = createCodec(PopFlowerBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
    public static final IntProperty STAGE = IntProperty.of("stage", 0, 7);
    public static final BooleanProperty CUT = BooleanProperty.of("cut");
    public static final int HIDE_DISTANCE = 5;

    public PopFlowerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(STAGE, 0).with(CUT, false));
    }

    public static void decrease(World world, BlockPos pos) {
        int stage = world.getBlockState(pos).get(STAGE);
        if (stage != 0) world.setBlockState(pos, world.getBlockState(pos).with(STAGE, stage - 1));
    }
    public static void increase(World world, BlockPos pos) {
        int stage = world.getBlockState(pos).get(STAGE);
        if (stage != 7 && !world.getBlockState(pos).get(CUT)) world.setBlockState(pos, world.getBlockState(pos).with(STAGE, stage + 1));
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(Items.SHEARS) && !world.getBlockState(pos).get(CUT)) {
            world.setBlockState(pos, state.with(CUT, true));
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return SkyCoreBlockEntityAPI.createTickerHelper(type, MCD_BlockEntityTypes.POP_FLOWER_BLOCK_ENTITY, PopFlowerBlockEntity::serverTick);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STAGE, CUT);
    }
    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PopFlowerBlockEntity(pos, state);
    }
}
