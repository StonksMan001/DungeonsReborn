package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_BlockFamilies {
    public static final BlockFamily MIDNIGHT_MOSSY_COBBLESTONE = BlockFamilies.familyBuilder(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE)
            .wall(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_WALL)
            .stairs(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_STAIRS)
            .slab(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB)
            .getFamily();
    public static final BlockFamily PALM = BlockFamilies.familyBuilder(MCD_Blocks.PALM_PLANKS)
            .button(MCD_Blocks.PALM_BUTTON)
            .fence(MCD_Blocks.PALM_FENCE)
            .fenceGate(MCD_Blocks.PALM_FENCE_GATE)
            .pressurePlate(MCD_Blocks.PALM_PRESSURE_PLATE)
            .sign(MCD_Blocks.PALM_SIGN, MCD_Blocks.PALM_WALL_SIGN)
            .slab(MCD_Blocks.PALM_SLAB)
            .stairs(MCD_Blocks.PALM_STAIRS)
            .door(MCD_Blocks.PALM_DOOR)
            .trapdoor(MCD_Blocks.PALM_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
