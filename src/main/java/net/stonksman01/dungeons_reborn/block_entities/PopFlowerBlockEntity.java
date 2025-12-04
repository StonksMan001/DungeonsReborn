package net.stonksman01.dungeons_reborn.block_entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.blocks.PopFlowerBlock;
import net.stonksman01.dungeons_reborn.registries.MCD_BlockEntities;
import net.stonksman01.dungeons_reborn.registries.MCD_GameRules;

public class PopFlowerBlockEntity extends BlockEntity {
    public PopFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(MCD_BlockEntities.POP_FLOWER_BLOCK_ENTITY, pos, state);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, PopFlowerBlockEntity blockEntity) {
        if (world instanceof ServerWorld serverWorld) {
            boolean hide = false;
            Box box = new Box(pos).expand(PopFlowerBlock.HIDE_DISTANCE);
            for (PlayerEntity playerEntity : world.getNonSpectatingEntities(PlayerEntity.class, box)) {
                if (Vec3d.ofCenter(pos).squaredDistanceTo(playerEntity.getEntityPos()) <= Math.pow(PopFlowerBlock.HIDE_DISTANCE, 2))
                    hide = true;
            }
            if (hide || world.isDay() || serverWorld.getGameRules().getBoolean(MCD_GameRules.POP_FLOWERS_ALWAYS_HIDE)) {
                PopFlowerBlock.decrease(world, pos);
            } else PopFlowerBlock.increase(world, pos);
        }
    }
}
