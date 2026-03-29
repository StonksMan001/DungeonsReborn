package net.qbaesz13.dungeons_reborn.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.qbaesz13.dungeons_reborn.blocks.PopFlowerBlock;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockEntityTypes;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

public class PopFlowerBlockEntity extends BlockEntity {
    public PopFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(MCD_BlockEntityTypes.POP_FLOWER_BLOCK_ENTITY, pos, state);
    }
    public static void serverTick(Level level, BlockPos pos, BlockState state, PopFlowerBlockEntity blockEntity) {
        if (level instanceof ServerLevel serverLevel) {
            var ref = new Object() {boolean hide = false;};
            DungeonsHelpers.executeForPlayersWithinDistance(serverLevel, pos, PopFlowerBlock.HIDE_DISTANCE, (playerEntity) -> {
                ref.hide = true;
                return null;
            });
            if (ref.hide || serverLevel.isBrightOutside() || serverLevel.getGameRules().get(MCD_GameRules.POP_FLOWERS_ALWAYS_HIDE)) {
                PopFlowerBlock.decrease(serverLevel, pos);
            } else PopFlowerBlock.increase(serverLevel, pos);
        }
    }
}
