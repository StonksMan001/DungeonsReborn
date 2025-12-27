package net.stonksman01.dungeons_reborn.block_entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.blocks.PopFlowerBlock;
import net.stonksman01.dungeons_reborn.registries.MCD_BlockEntities;
import net.stonksman01.dungeons_reborn.registries.MCD_GameRules;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;

public class PopFlowerBlockEntity extends BlockEntity {
    public PopFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(MCD_BlockEntities.POP_FLOWER_BLOCK_ENTITY, pos, state);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, PopFlowerBlockEntity blockEntity) {
        if (!world.isClient) {
            var ref = new Object() {boolean hide = false;};
            DungeonsHelpers.executeForPlayersWithinDistance(world, pos, PopFlowerBlock.HIDE_DISTANCE, (playerEntity) -> {
                ref.hide = true;
                return null;
            });
            if (ref.hide || world.isDay() || world.getGameRules().getBoolean(MCD_GameRules.POP_FLOWERS_ALWAYS_HIDE)) {
                PopFlowerBlock.decrease(world, pos);
            } else PopFlowerBlock.increase(world, pos);
        }
    }
}
