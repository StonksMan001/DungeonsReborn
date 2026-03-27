package net.qbaesz13.dungeons_reborn.block_entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.blocks.PopFlowerBlock;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockEntityTypes;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

public class PopFlowerBlockEntity extends BlockEntity {
    public PopFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(MCD_BlockEntityTypes.POP_FLOWER_BLOCK_ENTITY, pos, state);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, PopFlowerBlockEntity blockEntity) {
        if (world instanceof ServerWorld serverWorld) {
            var ref = new Object() {boolean hide = false;};
            DungeonsHelpers.executeForPlayersWithinDistance(serverWorld, pos, PopFlowerBlock.HIDE_DISTANCE, (playerEntity) -> {
                ref.hide = true;
                return null;
            });
            if (ref.hide || serverWorld.isDay() || serverWorld.getGameRules().getValue(MCD_GameRules.POP_FLOWERS_ALWAYS_HIDE)) {
                PopFlowerBlock.decrease(serverWorld, pos);
            } else PopFlowerBlock.increase(serverWorld, pos);
        }
    }
}
