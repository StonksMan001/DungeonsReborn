package net.qbaesz13.dungeons_reborn.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;

public class SourBerryBushBlock extends SweetBerryBushBlock {
    public SourBerryBushBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(MCD_Items.SOUR_BERRIES);
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (state.get(AGE) > 1) {
            if (world instanceof ServerWorld serverWorld) {
                int j = 1 + world.random.nextInt(2);
                dropStack(world, pos, new ItemStack(MCD_Items.SOUR_BERRIES, j + (state.get(AGE) == 3 ? 1 : 0)));
                serverWorld.playSound((Entity)null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + serverWorld.random.nextFloat() * 0.4F);
                BlockState blockState = (BlockState)state.with(AGE, 1);
                serverWorld.setBlockState(pos, blockState, 2);
                serverWorld.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            }
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }
}
