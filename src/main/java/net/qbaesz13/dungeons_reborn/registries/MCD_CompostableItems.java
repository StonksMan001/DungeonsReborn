package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

import java.lang.invoke.MethodHandles;

public class MCD_CompostableItems {
    private static void init() {
        CompostableRegistry.INSTANCE.add(MCD_Blocks.HIGHLAND_MOSS_BLOCK, DungeonsHelpers.getCompostingValue(Blocks.MOSS_BLOCK));
        CompostableRegistry.INSTANCE.add(MCD_Blocks.HIGHLAND_MOSS_CARPET, DungeonsHelpers.getCompostingValue(Blocks.MOSS_CARPET));
        CompostableRegistry.INSTANCE.add(MCD_Items.SOUR_BERRIES, DungeonsHelpers.getCompostingValue(Items.SWEET_BERRIES));
        CompostableRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, DungeonsHelpers.getCompostingValue(Blocks.MOSS_BLOCK));
        CompostableRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_MOSS_CARPET, DungeonsHelpers.getCompostingValue(Blocks.MOSS_CARPET));
        CompostableRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_SPROUTS, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
        CompostableRegistry.INSTANCE.add(MCD_Blocks.POP_FLOWER, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}