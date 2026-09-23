package net.qbaesz13.dungeons_reborn.registries;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.blocks.SC_MossBlock;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.blocks.SC_ShortPlantBlock;
import net.qbaesz13.dungeons_reborn.blocks.PalmLeavesBlock;
import net.qbaesz13.dungeons_reborn.blocks.PalmTrunkBlock;
import net.qbaesz13.dungeons_reborn.blocks.PopFlowerBlock;
import net.qbaesz13.dungeons_reborn.blocks.SourBerryBushBlock;
import net.qbaesz13.dungeons_reborn.registries.world.MCD_ConfiguredFeatures;

import java.lang.invoke.MethodHandles;
import java.util.Optional;
import java.util.stream.IntStream;

public class MCD_Blocks {
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_mossy_cobblestone",
            new Block(AbstractBlock.Settings
                    .copy(Blocks.MOSSY_COBBLESTONE)
                    .mapColor(MapColor.CYAN)));
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE_STAIRS = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_mossy_cobblestone_stairs",
            new StairsBlock(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE.getDefaultState(), AbstractBlock.Settings
                    .copy(Blocks.MOSSY_COBBLESTONE_STAIRS)
                    .mapColor(MapColor.CYAN)));
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE_SLAB = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_mossy_cobblestone_slab",
            new SlabBlock(AbstractBlock.Settings
                    .copy(Blocks.MOSSY_COBBLESTONE_SLAB)
                    .mapColor(MapColor.CYAN)));
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE_WALL = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_mossy_cobblestone_wall",
            new WallBlock(AbstractBlock.Settings
                    .copy(Blocks.MOSSY_COBBLESTONE_WALL)
                    .mapColor(MapColor.CYAN)));
    public static final Block MOSSY_OAK_PLANKS = SkyCore.BuiltinRegistries.registerBlockAndItem("mossy_oak_planks",
            new Block(AbstractBlock.Settings
                    .copy(Blocks.OAK_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.OAK_TAN)));
    public static final Block MOSSY_SPRUCE_PLANKS = SkyCore.BuiltinRegistries.registerBlockAndItem("mossy_spruce_planks",
            new Block(AbstractBlock.Settings
                    .copy(Blocks.SPRUCE_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.SPRUCE_BROWN)));
    public static final Block HIGHLAND_MOSS_BLOCK = SkyCore.BuiltinRegistries.registerBlockAndItem("highland_moss_block",
            new SC_MossBlock(AbstractBlock.Settings
                    .copy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.ORANGE), MCD_ConfiguredFeatures.HIGHLAND_MOSS_PATCH_BONEMEAL));
    public static final Block HIGHLAND_MOSS_CARPET = SkyCore.BuiltinRegistries.registerBlockAndItem("highland_moss_carpet",
            new CarpetBlock(AbstractBlock.Settings
                    .copy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.ORANGE)));
    public static final Block MEDIUM_HIGHLAND_GRASS = SkyCore.BuiltinRegistries.registerBlockAndItem("medium_highland_grass",
            new SC_ShortPlantBlock(AbstractBlock.Settings
                    .copy(Blocks.SHORT_GRASS)
                    .mapColor(MapColor.ORANGE)));
    public static final Block SHORT_HIGHLAND_GRASS = SkyCore.BuiltinRegistries.registerBlockAndItem("short_highland_grass",
            new SC_ShortPlantBlock(AbstractBlock.Settings
                    .copy(Blocks.SHORT_GRASS)
                    .mapColor(MapColor.ORANGE)) {
                @Override
                protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 3.0, 14.0);
                }
            });
    public static final Block SOUR_BERRY_BUSH = SkyCore.BuiltinRegistries.registerBlock("sour_berry_bush",
            new SourBerryBushBlock(AbstractBlock.Settings
                    .copy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block MIDNIGHT_MOSS_BLOCK = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_moss_block",
            new SC_MossBlock(AbstractBlock.Settings
                    .copy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.CYAN), MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL));
    public static final Block MIDNIGHT_MOSS_CARPET = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_moss_carpet",
            new CarpetBlock(AbstractBlock.Settings
                    .copy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.CYAN)));
    public static final Block MIDNIGHT_SPROUTS = SkyCore.BuiltinRegistries.registerBlockAndItem("midnight_sprouts",
            new SC_ShortPlantBlock(AbstractBlock.Settings
                    .copy(Blocks.NETHER_SPROUTS)
                    .mapColor(MapColor.CYAN)));
    public static final Block POP_FLOWER = SkyCore.BuiltinRegistries.registerBlockAndItem("pop_flower",
            new PopFlowerBlock(AbstractBlock.Settings
                    .copy(Blocks.WARPED_ROOTS)
                    .mapColor(MapColor.CYAN)));
    public static final Block ANCIENT_GOLD_BLOCK = SkyCore.BuiltinRegistries.registerBlockAndItem("ancient_gold_block",
            new Block(AbstractBlock.Settings
                    .copy(Blocks.GOLD_BLOCK)
                    .strength(Blocks.NETHERITE_BLOCK.getHardness(), Blocks.NETHERITE_BLOCK.getBlastResistance())
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block RAW_ANCIENT_GOLD_BLOCK = SkyCore.BuiltinRegistries.registerBlockAndItem("raw_ancient_gold_block",
            new Block(AbstractBlock.Settings
                    .copy(Blocks.RAW_GOLD_BLOCK)
                    .strength(Blocks.NETHERITE_BLOCK.getHardness(), Blocks.NETHERITE_BLOCK.getBlastResistance())
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_TRUNK = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_trunk",
            new PalmTrunkBlock(AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_LOG)
                    .mapColor(MapColor.SPRUCE_BROWN)));
    public static final Block STRIPPED_PALM_TRUNK = SkyCore.BuiltinRegistries.registerBlockAndItem("stripped_palm_trunk",
            new PalmTrunkBlock(AbstractBlock.Settings
                    .copy(Blocks.STRIPPED_JUNGLE_LOG)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_BEAM = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_beam",
            new PillarBlock(AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_LOG)
                    .mapColor(MapColor.SPRUCE_BROWN)));
    public static final Block STRIPPED_PALM_BEAM = SkyCore.BuiltinRegistries.registerBlockAndItem("stripped_palm_beam",
            new PillarBlock(AbstractBlock.Settings
                    .copy(Blocks.STRIPPED_JUNGLE_LOG)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_WOOD = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_wood",
            new PillarBlock(AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_WOOD)
                    .mapColor(MapColor.SPRUCE_BROWN)));
    public static final Block STRIPPED_PALM_WOOD = SkyCore.BuiltinRegistries.registerBlockAndItem("stripped_palm_wood",
            new PillarBlock(AbstractBlock.Settings
                    .copy(Blocks.STRIPPED_JUNGLE_WOOD)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_PLANKS = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_planks",
            new Block(AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_STAIRS = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_stairs",
            new StairsBlock(MCD_Blocks.PALM_PLANKS.getDefaultState(), AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_STAIRS)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_SLAB = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_slab",
            new SlabBlock(AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_SLAB)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_FENCE = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_fence",
            new FenceBlock(AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_FENCE)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_FENCE_GATE = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_fence_gate",
            new FenceGateBlock(WoodType.JUNGLE, AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_FENCE_GATE)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_DOOR = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_door",
            new DoorBlock(BlockSetType.JUNGLE, AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_DOOR)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_TRAPDOOR = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_trapdoor",
            new TrapdoorBlock(BlockSetType.JUNGLE, AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_TRAPDOOR)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_PRESSURE_PLATE = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_pressure_plate",
            new PressurePlateBlock(BlockSetType.JUNGLE, AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_PRESSURE_PLATE)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_BUTTON = SkyCore.BuiltinRegistries.registerBlockAndItem("palm_button",
            new ButtonBlock(BlockSetType.JUNGLE, 30, AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_BUTTON)));
    public static final Block PALM_SIGN = SkyCore.BuiltinRegistries.registerBlock("palm_sign",
            new TerraformSignBlock(
                    DungeonsReborn.identifierOfDungeonsReborn("entity/signs/palm"),
                    AbstractBlock.Settings
                            .copy(Blocks.JUNGLE_SIGN)
                            .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_WALL_SIGN = SkyCore.BuiltinRegistries.registerBlock("palm_wall_sign",
            new TerraformWallSignBlock(
                    DungeonsReborn.identifierOfDungeonsReborn("entity/signs/palm"),
                    AbstractBlock.Settings
                            .copy(Blocks.JUNGLE_WALL_SIGN)
                            .mapColor(MapColor.TERRACOTTA_ORANGE)
                            .dropsLike(MCD_Blocks.PALM_SIGN)));
    public static final Block PALM_HANGING_SIGN = SkyCore.BuiltinRegistries.registerBlock("palm_hanging_sign",
            new TerraformHangingSignBlock(
                    DungeonsReborn.identifierOfDungeonsReborn("entity/signs/hanging/palm"),
                    DungeonsReborn.identifierOfDungeonsReborn("textures/gui/hanging_signs/palm"),
                    AbstractBlock.Settings
                            .copy(Blocks.JUNGLE_HANGING_SIGN)
                            .mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block PALM_WALL_HANGING_SIGN = SkyCore.BuiltinRegistries.registerBlock("palm_wall_hanging_sign",
            new TerraformWallHangingSignBlock(
                    DungeonsReborn.identifierOfDungeonsReborn("entity/signs/hanging/palm"),
                    DungeonsReborn.identifierOfDungeonsReborn("textures/gui/hanging_signs/palm"),
                    AbstractBlock.Settings
                            .copy(Blocks.JUNGLE_WALL_HANGING_SIGN)
                            .mapColor(MapColor.TERRACOTTA_ORANGE)
                            .dropsLike(MCD_Blocks.PALM_HANGING_SIGN)));
    public static final Block PALM_LEAVES = SkyCore.BuiltinRegistries.registerBlock("palm_leaves",
            new PalmLeavesBlock(AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_LEAVES)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.SPORE_BLOSSOM)
                    .mapColor(MapColor.PALE_GREEN)));
    public static final Block PALM_SAPLING = SkyCore.BuiltinRegistries.registerBlock("palm_sapling",
            new SaplingBlock(new SaplingGenerator(
                    "palm",
                    Optional.empty(),
                    Optional.of(MCD_ConfiguredFeatures.PALM),
                    Optional.empty()
            ), AbstractBlock.Settings
                    .copy(Blocks.JUNGLE_SAPLING)
                    .sounds(BlockSoundGroup.SPORE_BLOSSOM)
                    .mapColor(MapColor.PALE_GREEN)) {
                @Override
                protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
                    return world.getBlockState(pos).isIn(BlockTags.SAND) || super.canPlantOnTop(floor, world, pos);
                }
                @Override
                public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
                    return super.canGrow(world, random, pos, state) && IntStream.rangeClosed(1, 4).allMatch(i -> world.getBlockState(pos.up(i)).isAir());
                }
            });
    public static final Block POTTED_PALM_SAPLING = SkyCore.BuiltinRegistries.registerBlock("potted_palm_sapling",
            new FlowerPotBlock(MCD_Blocks.PALM_SAPLING, AbstractBlock.Settings
                    .copy(Blocks.POTTED_JUNGLE_SAPLING)));
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
