package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_BlockTags {
    public static final TagKey<Block> PALM_LOGS = SkyCore.BuiltinRegistries.createBlockTag("palm_logs");
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
