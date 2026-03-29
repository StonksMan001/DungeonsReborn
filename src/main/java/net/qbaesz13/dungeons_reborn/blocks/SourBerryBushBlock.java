package net.qbaesz13.dungeons_reborn.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import org.jspecify.annotations.NullMarked;

public class SourBerryBushBlock extends SweetBerryBushBlock {
    public SourBerryBushBlock(Properties properties) {
        super(properties);
    }

    @Override @NullMarked
    protected ItemStack getCloneItemStack(final LevelReader level, final BlockPos pos, final BlockState state, final boolean includeData) {
        return new ItemStack(MCD_Items.SOUR_BERRIES);
    }
    /**
     * Identical to {@link SweetBerryBushBlock#useWithoutItem} except Section#1
     */
    @Override @NullMarked
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player playerEntity, BlockHitResult hitResult) {
        if (state.getValue(AGE) > 1) {
            if (level instanceof ServerLevel serverLevel) {
                /* <Section#1> */
                int j = 1 + level.getRandom().nextInt(2);
                popResource(level, pos, new ItemStack(MCD_Items.SOUR_BERRIES, j + (state.getValue(AGE) == 3 ? 1 : 0)));
                /* </Section#1> */
                serverLevel.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + serverLevel.getRandom().nextFloat() * 0.4F);
                BlockState newState = state.setValue(AGE, 1);
                serverLevel.setBlock(pos, newState, 2);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(playerEntity, newState));
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, playerEntity, hitResult);
        }
    }
}
