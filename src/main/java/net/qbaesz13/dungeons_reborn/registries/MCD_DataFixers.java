package net.qbaesz13.dungeons_reborn.registries;

import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataFixerAPI;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_DataFixers {
    private static void init() {
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:highland_moss_block", "dungeons_reborn:dried_moss_block");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:highland_moss_carpet", "dungeons_reborn:dried_moss_carpet");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:midnight_mossy_cobblestone", "dungeons_reborn:mossier_cobblestone");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:mossy_oak_planks", "dungeons_reborn:mossier_oak_planks");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:mossy_spruce_planks", "dungeons_reborn:mossier_spruce_planks");
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
