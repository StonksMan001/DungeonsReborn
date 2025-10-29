package net.stonksman01.dungeons_reborn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MossyOakPlanksBlock extends Block {
    public static final BooleanProperty MOSSIER = BooleanProperty.of("mossier");
    public MossyOakPlanksBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(MOSSIER, false));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.getRandom().nextInt(2) == 1) world.setBlockState(pos, state.with(MOSSIER, true));
        super.onPlaced(world, pos, state, placer, itemStack);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MOSSIER);
    }
}