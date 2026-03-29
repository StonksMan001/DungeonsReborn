package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_ToolMaterials {
    public static final ToolMaterial DEFAULT = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1000, 9.0F, 4.0F, 15, ItemTags.IRON_TOOL_MATERIALS);

    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
