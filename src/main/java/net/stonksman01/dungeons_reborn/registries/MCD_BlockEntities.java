package net.stonksman01.dungeons_reborn.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;
import net.stonksman01.dungeons_reborn.block_entities.PopFlowerBlockEntity;

public class MCD_BlockEntities {
    public static final BlockEntityType<PopFlowerBlockEntity> POP_FLOWER_BLOCK_ENTITY = SkyCore.BuiltinRegistries.registerBlockEntityType("pop_flower_block_entity",
            FabricBlockEntityTypeBuilder.create(PopFlowerBlockEntity::new, MCD_Blocks.POP_FLOWER).build());
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering BlockEntities");
    }
}
