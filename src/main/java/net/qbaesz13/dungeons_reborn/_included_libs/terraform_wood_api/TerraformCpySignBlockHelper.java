package net.qbaesz13.dungeons_reborn._included_libs.terraform_wood_api;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;

import java.util.function.Function;

public class TerraformCpySignBlockHelper {
    public static Block registerSignBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = SkyCore.RegistryPresets.registerBlock(name, factory, properties);

        if (block instanceof StandingSignBlock || block instanceof WallSignBlock) {
            BlockEntityTypes.SIGN.addValidBlock(block);
        } else if (block instanceof CeilingHangingSignBlock || block instanceof WallHangingSignBlock) {
            BlockEntityTypes.HANGING_SIGN.addValidBlock(block);
        } else {
            throw new IllegalArgumentException("This method only accepts vanilla sign blocks and descendants!");
        }

        return block;
    }
}
