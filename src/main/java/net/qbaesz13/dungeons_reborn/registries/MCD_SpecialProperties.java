package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

/**
 * Container class for all block/item related registries, that define behavior with other blocks/items (such as {@link net.minecraft.world.level.block.ComposterBlock}s and {@link net.minecraft.world.level.block.FurnaceBlock}s)
 */
public class MCD_SpecialProperties {
    public static class FuelItems {
        private static void init() {
            DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
                register(modifyContext, MCD_Blocks.PALM_TRUNK, 75);
                register(modifyContext, MCD_Blocks.STRIPPED_PALM_TRUNK, 75);

                register(modifyContext, MCD_Blocks.PALM_BEAM, 300);
                register(modifyContext, MCD_Blocks.PALM_WOOD, 300);
                register(modifyContext, MCD_Blocks.STRIPPED_PALM_BEAM, 300);
                register(modifyContext, MCD_Blocks.STRIPPED_PALM_WOOD, 300);
                register(modifyContext, MCD_Blocks.PALM_PLANKS, 300);
                register(modifyContext, MCD_Blocks.PALM_STAIRS, 300);
                register(modifyContext, MCD_Blocks.PALM_SLAB, 150);
                register(modifyContext, MCD_Blocks.PALM_FENCE, 150);
                register(modifyContext, MCD_Blocks.PALM_FENCE_GATE, 150);
                register(modifyContext, MCD_Blocks.PALM_DOOR, 200);
                register(modifyContext, MCD_Blocks.PALM_TRAPDOOR, 300);
                register(modifyContext, MCD_Blocks.PALM_PRESSURE_PLATE, 300);
                register(modifyContext, MCD_Blocks.PALM_BUTTON, 100);
                register(modifyContext, MCD_Blocks.PALM_SIGN, 200);
                register(modifyContext, MCD_Blocks.PALM_HANGING_SIGN, 800);
                register(modifyContext, MCD_Items.PALM_BOAT, 1200);
                register(modifyContext, MCD_Items.PALM_CHEST_BOAT, 1200);

                register(modifyContext, MCD_Items.PALM_SAPLING, 100);

                register(modifyContext, MCD_Blocks.MOSSY_OAK_PLANKS, 300);
                register(modifyContext, MCD_Blocks.MOSSY_SPRUCE_PLANKS, 300);
            });
        }
        private static void register(DefaultItemComponentEvents.ModifyContext modifyContext, ItemLike itemLike, int value) {
            modifyContext.modify(itemLike.asItem(), builder -> {
                builder.set(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(value), new ResolvableFloat.Constant(1)));
            });
        }
    }
    public static class CompostableItems {
        private static void init() {
            DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
                register(modifyContext, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, 65);
                register(modifyContext, MCD_Blocks.MIDNIGHT_MOSS_CARPET, 30);
                register(modifyContext, MCD_Blocks.MIDNIGHT_SPROUTS, 30);
                register(modifyContext, MCD_Blocks.POP_FLOWER, 30);

                register(modifyContext, MCD_Blocks.HIGHLAND_MOSS_BLOCK, 65);
                register(modifyContext, MCD_Blocks.HIGHLAND_MOSS_CARPET, 30);
                register(modifyContext, MCD_Blocks.MEDIUM_HIGHLAND_GRASS, 30);
                register(modifyContext, MCD_Blocks.SHORT_HIGHLAND_GRASS, 30);
                register(modifyContext, MCD_Items.SOUR_BERRIES, 30);

                register(modifyContext, MCD_Items.PALM_LEAVES, 30);
                register(modifyContext, MCD_Items.PALM_SAPLING, 30);
            });
        }
        private static void register(DefaultItemComponentEvents.ModifyContext modifyContext, ItemLike itemLike, int value) {
            modifyContext.modify(itemLike.asItem(), builder -> {
                builder.set(DataComponents.COMPOSTABLE, new Compostable(new ResolvableInt.Constant(value)));
            });
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
            FlammableBlockRegistry.getDefaultInstance().add(flammableBlock, DungeonsHelpers.getIgniteChance(parent), DungeonsHelpers.getBurnChance(parent));
        }
    }
    public static class StrippableBlocks {
        private static void init() {
            BlockTransformerHelper.registerStripping(MCD_Blocks.PALM_TRUNK, MCD_Blocks.STRIPPED_PALM_TRUNK);
            BlockTransformerHelper.registerStripping(MCD_Blocks.PALM_BEAM, MCD_Blocks.STRIPPED_PALM_BEAM);
            BlockTransformerHelper.registerStripping(MCD_Blocks.PALM_WOOD, MCD_Blocks.STRIPPED_PALM_WOOD);
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