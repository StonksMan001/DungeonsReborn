package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.block.entity.BlockEntityType;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.block_entities.PopFlowerBlockEntity;

import java.lang.invoke.MethodHandles;

public class MCD_BlockEntities {
    public static final BlockEntityType<PopFlowerBlockEntity> POP_FLOWER_BLOCK_ENTITY = SkyCore.BuiltinRegistries.registerBlockEntityType("pop_flower_block_entity",
            BlockEntityType.Builder.create(PopFlowerBlockEntity::new, MCD_Blocks.POP_FLOWER).build());
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
