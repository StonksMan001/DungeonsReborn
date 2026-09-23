package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

/**
 * Container class for all block/item related registries, that define behavior with other blocks/items (such as {@link net.minecraft.block.ComposterBlock}s and {@link net.minecraft.block.FurnaceBlock}s)
 */
public class MCD_SpecialProperties {
    public static class FuelItems {
        private static void init() {
            FuelRegistry.INSTANCE.add(MCD_Blocks.PALM_TRUNK, 75);
            FuelRegistry.INSTANCE.add(MCD_Blocks.STRIPPED_PALM_TRUNK, 75);
        }
    }
    public static class CompostableItems {
        private static void init() {
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, DungeonsHelpers.getCompostingValue(Blocks.MOSS_BLOCK));
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_MOSS_CARPET, DungeonsHelpers.getCompostingValue(Blocks.MOSS_CARPET));
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_SPROUTS, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.POP_FLOWER, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));

            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.HIGHLAND_MOSS_BLOCK, DungeonsHelpers.getCompostingValue(Blocks.MOSS_BLOCK));
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.HIGHLAND_MOSS_CARPET, DungeonsHelpers.getCompostingValue(Blocks.MOSS_CARPET));
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.MEDIUM_HIGHLAND_GRASS, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.SHORT_HIGHLAND_GRASS, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
            CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.POP_FLOWER, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
            CompostingChanceRegistry.INSTANCE.add(MCD_Items.SOUR_BERRIES, DungeonsHelpers.getCompostingValue(Items.SWEET_BERRIES));

            CompostingChanceRegistry.INSTANCE.add(MCD_Items.PALM_LEAVES, DungeonsHelpers.getCompostingValue(Items.JUNGLE_LEAVES));
            CompostingChanceRegistry.INSTANCE.add(MCD_Items.PALM_SAPLING, DungeonsHelpers.getCompostingValue(Items.JUNGLE_SAPLING));
        }
    }
    public static class FlammableBlocks {
        private static void init() {
            createFlammableBlockInstance(MCD_Blocks.PALM_TRUNK, Blocks.JUNGLE_LOG);
            createFlammableBlockInstance(MCD_Blocks.PALM_BEAM, Blocks.JUNGLE_LOG);
            createFlammableBlockInstance(MCD_Blocks.PALM_WOOD, Blocks.JUNGLE_WOOD);
            createFlammableBlockInstance(MCD_Blocks.STRIPPED_PALM_TRUNK, Blocks.STRIPPED_JUNGLE_LOG);
            createFlammableBlockInstance(MCD_Blocks.STRIPPED_PALM_BEAM, Blocks.STRIPPED_JUNGLE_LOG);
            createFlammableBlockInstance(MCD_Blocks.STRIPPED_PALM_WOOD, Blocks.STRIPPED_JUNGLE_WOOD);
            createFlammableBlockInstance(MCD_Blocks.PALM_PLANKS, Blocks.JUNGLE_PLANKS);
            createFlammableBlockInstance(MCD_Blocks.PALM_STAIRS, Blocks.JUNGLE_STAIRS);
            createFlammableBlockInstance(MCD_Blocks.PALM_SLAB, Blocks.JUNGLE_SLAB);
            createFlammableBlockInstance(MCD_Blocks.PALM_FENCE, Blocks.JUNGLE_FENCE);
            createFlammableBlockInstance(MCD_Blocks.PALM_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE);
            createFlammableBlockInstance(MCD_Blocks.PALM_DOOR, Blocks.JUNGLE_DOOR);
            createFlammableBlockInstance(MCD_Blocks.PALM_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR);
            createFlammableBlockInstance(MCD_Blocks.PALM_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE);
            createFlammableBlockInstance(MCD_Blocks.PALM_BUTTON, Blocks.JUNGLE_BUTTON);
            createFlammableBlockInstance(MCD_Blocks.PALM_LEAVES, Blocks.JUNGLE_LEAVES);
            createFlammableBlockInstance(MCD_Blocks.PALM_SAPLING, Blocks.JUNGLE_SAPLING);

            createFlammableBlockInstance(MCD_Blocks.MOSSY_OAK_PLANKS, Blocks.OAK_PLANKS);
            createFlammableBlockInstance(MCD_Blocks.MOSSY_SPRUCE_PLANKS, Blocks.OAK_PLANKS);

            createFlammableBlockInstance(MCD_Blocks.MIDNIGHT_SPROUTS, Blocks.SHORT_GRASS);
            createFlammableBlockInstance(MCD_Blocks.POP_FLOWER, Blocks.SHORT_GRASS);

            createFlammableBlockInstance(MCD_Blocks.MEDIUM_HIGHLAND_GRASS, Blocks.SHORT_GRASS);
            createFlammableBlockInstance(MCD_Blocks.SHORT_HIGHLAND_GRASS, Blocks.SHORT_GRASS);
            createFlammableBlockInstance(MCD_Blocks.SOUR_BERRY_BUSH, Blocks.SWEET_BERRY_BUSH);
        }
        private static void createFlammableBlockInstance(Block flammableBlock, Block parent) {
            FlammableBlockRegistry.getDefaultInstance().add(flammableBlock, DungeonsHelpers.getBurnChance(parent), DungeonsHelpers.getSpreadChance(parent));
        }
    }
    public static class StrippableBlocks {
        private static void init() {
            StrippableBlockRegistry.register(MCD_Blocks.PALM_TRUNK, MCD_Blocks.STRIPPED_PALM_TRUNK);
            StrippableBlockRegistry.register(MCD_Blocks.PALM_BEAM, MCD_Blocks.STRIPPED_PALM_BEAM);
            StrippableBlockRegistry.register(MCD_Blocks.PALM_WOOD, MCD_Blocks.STRIPPED_PALM_WOOD);
        }
    }
    public static void register() {
        FuelItems.init();
        SkyCoreRegistryHelper.register(FuelItems.class, DungeonsReborn.LOGGER);
        CompostableItems.init();
        SkyCoreRegistryHelper.register(CompostableItems.class, DungeonsReborn.LOGGER);
        FlammableBlocks.init();
        SkyCoreRegistryHelper.register(FlammableBlocks.class, DungeonsReborn.LOGGER);
        StrippableBlocks.init();
        SkyCoreRegistryHelper.register(StrippableBlocks.class, DungeonsReborn.LOGGER);
    }
}
