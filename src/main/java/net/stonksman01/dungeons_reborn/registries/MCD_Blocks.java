package net.stonksman01.dungeons_reborn.registries;

import net.minecraft.block.*;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCore;
import net.stonksman01.dungeons_reborn.blocks.*;

public class MCD_Blocks {
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_mossy_cobblestone",
            Block::new, AbstractBlock.Settings
                    .copy(Blocks.COBBLESTONE)
                    .strength(2.0F, 6.0F)
                    .mapColor(MapColor.CYAN));
    public static final Block MOSSIER_OAK_PLANKS = SkyCore.BuiltinRegistries.registerBlockAndItem("mossy_oak_planks",
            MossyOakPlanksBlock::new, AbstractBlock.Settings
                    .copy(Blocks.OAK_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.OAK_TAN));
    public static final Block MOSSIER_SPRUCE_PLANKS = SkyCore.BuiltinRegistries.registerBlockAndItem("mossy_spruce_planks",
            Block::new, AbstractBlock.Settings
                    .copy(Blocks.SPRUCE_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.SPRUCE_BROWN));
    public static final Block HIGHLAND_MOSS_BLOCK = SkyCore.BuiltinRegistries.registerBlockAndItem("highland_moss_block",
            settings -> new MossBlock(MCD_ConfiguredFeatures.HIGHLAND_MOSS_PATCH_BONEMEAL, settings), AbstractBlock.Settings
                    .copy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.ORANGE));
    public static final Block HIGHLAND_MOSS_CARPET = SkyCore.BuiltinRegistries.registerBlockAndItem("highland_moss_carpet",
            CarpetBlock::new, AbstractBlock.Settings
                    .copy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.ORANGE));
    public static final Block SOUR_BERRY_BUSH = SkyCore.BuiltinRegistries.registerBlock("sour_berry_bush",
            SourBerryBushBlock::new, AbstractBlock.Settings
                    .copy(Blocks.SWEET_BERRY_BUSH));
    public static final Block MIDNIGHT_MOSS_BLOCK = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_moss_block",
            settings -> new MossBlock(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL, settings), AbstractBlock.Settings
                    .copy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.CYAN));
    public static final Block MIDNIGHT_MOSS_CARPET = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_moss_carpet",
            CarpetBlock::new, AbstractBlock.Settings
                    .copy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.CYAN));
    public static final Block MIDNIGHT_SPROUTS = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_sprouts",
            ShortPlantBlock::new, AbstractBlock.Settings
                    .copy(Blocks.NETHER_SPROUTS)
                    .mapColor(MapColor.CYAN));
    public static final Block POP_FLOWER = SkyCore.BuiltinRegistries.registerBlockAndItem("pop_flower",
            PopFlowerBlock::new, AbstractBlock.Settings
                    .copy(Blocks.WARPED_ROOTS)
                    .mapColor(MapColor.CYAN));
    public static final Block ANCIENT_GOLD_BLOCK = SkyCore.BuiltinRegistries.registerBlockAndItem("ancient_gold_block",
            Block::new, AbstractBlock.Settings
                    .copy(Blocks.GOLD_BLOCK)
                    .strength(Blocks.NETHERITE_BLOCK.getHardness(), Blocks.NETHERITE_BLOCK.getBlastResistance())
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Blocks");
    }
}
