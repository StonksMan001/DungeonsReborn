package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.block_entities.PopFlowerBlockEntity;

import java.lang.invoke.MethodHandles;

public class MCD_BlockEntityTypes {
    public static final BlockEntityType<PopFlowerBlockEntity> POP_FLOWER_BLOCK_ENTITY = SkyCore.RegistryPresets.registerBlockEntityType("pop_flower_block_entity",
            FabricBlockEntityTypeBuilder.create(PopFlowerBlockEntity::new, MCD_Blocks.POP_FLOWER).build());
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
