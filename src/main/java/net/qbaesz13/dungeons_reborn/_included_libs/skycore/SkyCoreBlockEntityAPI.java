package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

public class SkyCoreBlockEntityAPI {
    public static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> thisType, BlockEntityType<E> targetType, BlockEntityTicker<? super E> ticker) {
        return targetType == thisType ? (BlockEntityTicker<A>) ticker : null;
    }
}
