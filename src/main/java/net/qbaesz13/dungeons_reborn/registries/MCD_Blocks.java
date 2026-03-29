package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.blocks.PopFlowerBlock;
import net.qbaesz13.dungeons_reborn.blocks.SourBerryBushBlock;

import java.lang.invoke.MethodHandles;

public class MCD_Blocks {
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE = SkyCore.RegistryPresets.registerBlockAndItem("midnight_mossy_cobblestone",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.COBBLESTONE)
                    .strength(2.0F, 6.0F)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MOSSIER_OAK_PLANKS = SkyCore.RegistryPresets.registerBlockAndItem("mossy_oak_planks",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.WOOD));
    public static final Block MOSSIER_SPRUCE_PLANKS = SkyCore.RegistryPresets.registerBlockAndItem("mossy_spruce_planks",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.SPRUCE_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.PODZOL));
    public static final Block HIGHLAND_MOSS_BLOCK = SkyCore.RegistryPresets.registerBlockAndItem("highland_moss_block",
            settings -> new BonemealableFeaturePlacerBlock(MCD_ConfiguredFeatures.HIGHLAND_MOSS_PATCH_BONEMEAL, settings), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.COLOR_ORANGE));
    public static final Block HIGHLAND_MOSS_CARPET = SkyCore.RegistryPresets.registerBlockAndItem("highland_moss_carpet",
            CarpetBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.COLOR_ORANGE));
    public static final Block SOUR_BERRY_BUSH = SkyCore.RegistryPresets.registerBlock("sour_berry_bush",
            SourBerryBushBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final Block MIDNIGHT_MOSS_BLOCK = SkyCore.RegistryPresets.registerBlockAndItem("midnight_moss_block",
            settings -> new BonemealableFeaturePlacerBlock(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL, settings), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MIDNIGHT_MOSS_CARPET = SkyCore.RegistryPresets.registerBlockAndItem("midnight_moss_carpet",
            CarpetBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MIDNIGHT_SPROUTS = SkyCore.RegistryPresets.registerBlockAndItem("midnight_sprouts",
            TallGrassBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.NETHER_SPROUTS)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block POP_FLOWER = SkyCore.RegistryPresets.registerBlockAndItem("pop_flower",
            PopFlowerBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.WARPED_ROOTS)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block ANCIENT_GOLD_BLOCK = SkyCore.RegistryPresets.registerBlockAndItem("ancient_gold_block",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.GOLD_BLOCK)
                    .strength(Blocks.NETHERITE_BLOCK.defaultDestroyTime(), Blocks.NETHERITE_BLOCK.getExplosionResistance())
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}