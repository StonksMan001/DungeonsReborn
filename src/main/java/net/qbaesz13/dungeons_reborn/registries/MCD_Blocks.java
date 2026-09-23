package net.qbaesz13.dungeons_reborn.registries;

import com.terraformersmc.terraform.sign.api.block.TerraformSignBlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.blocks.SC_ShortPlantBlock;
import net.qbaesz13.dungeons_reborn.blocks.PalmLeavesBlock;
import net.qbaesz13.dungeons_reborn.blocks.PalmTrunkBlock;
import net.qbaesz13.dungeons_reborn.blocks.PopFlowerBlock;
import net.qbaesz13.dungeons_reborn.blocks.SourBerryBushBlock;
import net.qbaesz13.dungeons_reborn.registries.world.MCD_ConfiguredFeatures;
import org.jspecify.annotations.NullMarked;

import java.lang.invoke.MethodHandles;
import java.util.Optional;
import java.util.stream.IntStream;

public class MCD_Blocks {
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE = SkyCore.RegistryPresets.registerBlockAndItem("midnight_mossy_cobblestone",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.COBBLESTONE)
                    .strength(2.0F, 6.0F)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE_STAIRS = SkyCore.RegistryPresets.registerBlockAndItem("midnight_mossy_cobblestone_stairs",
            properties -> new StairBlock(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE.defaultBlockState(), properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSSY_COBBLESTONE_STAIRS)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE_SLAB = SkyCore.RegistryPresets.registerBlockAndItem("midnight_mossy_cobblestone_slab",
            SlabBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSSY_COBBLESTONE_SLAB)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MIDNIGHT_MOSSY_COBBLESTONE_WALL = SkyCore.RegistryPresets.registerBlockAndItem("midnight_mossy_cobblestone_wall",
            WallBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSSY_COBBLESTONE_WALL)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MOSSY_OAK_PLANKS = SkyCore.RegistryPresets.registerBlockAndItem("mossy_oak_planks",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.WOOD));
    public static final Block MOSSY_SPRUCE_PLANKS = SkyCore.RegistryPresets.registerBlockAndItem("mossy_spruce_planks",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.SPRUCE_PLANKS)
                    .strength(2.0f, 3.0f)
                    .mapColor(MapColor.PODZOL));
    public static final Block HIGHLAND_MOSS_BLOCK = SkyCore.RegistryPresets.registerBlockAndItem("highland_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(MCD_ConfiguredFeatures.HIGHLAND_MOSS_PATCH_BONEMEAL, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.COLOR_ORANGE));
    public static final Block HIGHLAND_MOSS_CARPET = SkyCore.RegistryPresets.registerBlockAndItem("highland_moss_carpet",
            CarpetBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.COLOR_ORANGE));
    public static final Block MEDIUM_HIGHLAND_GRASS = SkyCore.RegistryPresets.registerBlockAndItem("medium_highland_grass",
            SC_ShortPlantBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.SHORT_GRASS)
                    .mapColor(MapColor.COLOR_ORANGE));
    public static final Block SHORT_HIGHLAND_GRASS = SkyCore.RegistryPresets.registerBlockAndItem("short_highland_grass",
            properties -> new SC_ShortPlantBlock(properties) {
                @Override @NullMarked
                protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return Block.box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0);
                }
            }, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.SHORT_GRASS)
                    .mapColor(MapColor.COLOR_ORANGE));
    public static final Block SOUR_BERRY_BUSH = SkyCore.RegistryPresets.registerBlock("sour_berry_bush",
            SourBerryBushBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final Block MIDNIGHT_MOSS_BLOCK = SkyCore.RegistryPresets.registerBlockAndItem("midnight_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(MCD_ConfiguredFeatures.MIDNIGHT_MOSS_PATCH_BONEMEAL, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_BLOCK)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MIDNIGHT_MOSS_CARPET = SkyCore.RegistryPresets.registerBlockAndItem("midnight_moss_carpet",
            CarpetBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.MOSS_CARPET)
                    .mapColor(MapColor.COLOR_CYAN));
    public static final Block MIDNIGHT_SPROUTS = SkyCore.RegistryPresets.registerBlockAndItem("midnight_sprouts",
            SC_ShortPlantBlock::new, BlockBehaviour.Properties
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
    public static final Block RAW_ANCIENT_GOLD_BLOCK = SkyCore.RegistryPresets.registerBlockAndItem("raw_ancient_gold_block",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.RAW_GOLD_BLOCK)
                    .strength(Blocks.NETHERITE_BLOCK.defaultDestroyTime(), Blocks.NETHERITE_BLOCK.getExplosionResistance())
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_TRUNK = SkyCore.RegistryPresets.registerBlockAndItem("palm_trunk",
            PalmTrunkBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_LOG)
                    .mapColor(MapColor.PODZOL));
    public static final Block STRIPPED_PALM_TRUNK = SkyCore.RegistryPresets.registerBlockAndItem("stripped_palm_trunk",
            PalmTrunkBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.STRIPPED_JUNGLE_LOG)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_BEAM = SkyCore.RegistryPresets.registerBlockAndItem("palm_beam",
            RotatedPillarBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_LOG)
                    .mapColor(MapColor.PODZOL));
    public static final Block STRIPPED_PALM_BEAM = SkyCore.RegistryPresets.registerBlockAndItem("stripped_palm_beam",
            RotatedPillarBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.STRIPPED_JUNGLE_LOG)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_WOOD = SkyCore.RegistryPresets.registerBlockAndItem("palm_wood",
            RotatedPillarBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_WOOD)
                    .mapColor(MapColor.PODZOL));
    public static final Block STRIPPED_PALM_WOOD = SkyCore.RegistryPresets.registerBlockAndItem("stripped_palm_wood",
            RotatedPillarBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.STRIPPED_JUNGLE_WOOD)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_PLANKS = SkyCore.RegistryPresets.registerBlockAndItem("palm_planks",
            Block::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_STAIRS = SkyCore.RegistryPresets.registerBlockAndItem("palm_stairs",
            properties -> new StairBlock(MCD_Blocks.PALM_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_STAIRS)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_SLAB = SkyCore.RegistryPresets.registerBlockAndItem("palm_slab",
            SlabBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_SLAB)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_FENCE = SkyCore.RegistryPresets.registerBlockAndItem("palm_fence",
            FenceBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_FENCE)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_FENCE_GATE = SkyCore.RegistryPresets.registerBlockAndItem("palm_fence_gate",
            properties -> new FenceGateBlock(WoodType.JUNGLE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_FENCE_GATE)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_DOOR = SkyCore.RegistryPresets.registerBlockAndItem("palm_door",
            properties -> new DoorBlock(BlockSetType.JUNGLE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_DOOR)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_TRAPDOOR = SkyCore.RegistryPresets.registerBlockAndItem("palm_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.JUNGLE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_TRAPDOOR)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_PRESSURE_PLATE = SkyCore.RegistryPresets.registerBlockAndItem("palm_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.JUNGLE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_PRESSURE_PLATE)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_BUTTON = SkyCore.RegistryPresets.registerBlockAndItem("palm_button",
            properties -> new ButtonBlock(BlockSetType.JUNGLE, 30, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_BUTTON));
    private static final WoodType PALM_WOOD_TYPE = TerraformSignBlockHelper.registerDefaultWoodType(DungeonsReborn.identifierOfDungeonsReborn("palm"));
    public static final Block PALM_SIGN = TerraformSignBlockHelper.registerSignBlock(DungeonsReborn.identifierOfDungeonsReborn("palm_sign"),
            properties -> new StandingSignBlock(MCD_Blocks.PALM_WOOD_TYPE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_SIGN)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock(DungeonsReborn.identifierOfDungeonsReborn("palm_wall_sign"),
            properties -> new WallSignBlock(MCD_Blocks.PALM_WOOD_TYPE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_WALL_SIGN)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .overrideLootTable(MCD_Blocks.PALM_SIGN.getLootTable()));
    public static final Block PALM_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(DungeonsReborn.identifierOfDungeonsReborn("palm_hanging_sign"),
            properties -> new CeilingHangingSignBlock(MCD_Blocks.PALM_WOOD_TYPE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_HANGING_SIGN)
                    .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block PALM_WALL_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(DungeonsReborn.identifierOfDungeonsReborn("palm_wall_hanging_sign"),
            properties -> new WallHangingSignBlock(MCD_Blocks.PALM_WOOD_TYPE, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_WALL_HANGING_SIGN)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .overrideLootTable(MCD_Blocks.PALM_HANGING_SIGN.getLootTable()));
    public static final Block PALM_LEAVES = SkyCore.RegistryPresets.registerBlock("palm_leaves",
            PalmLeavesBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_LEAVES)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.SPORE_BLOSSOM)
                    .mapColor(MapColor.GRASS));
    public static final Block PALM_SAPLING = SkyCore.RegistryPresets.registerBlock("palm_sapling",
            properties -> new SaplingBlock(new TreeGrower(
                    "palm",
                    Optional.empty(),
                    Optional.of(MCD_ConfiguredFeatures.PALM),
                    Optional.empty()
            ), properties) {
                @Override @NullMarked
                protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
                    return level.getBlockState(pos).is(BlockTags.SAND) || super.mayPlaceOn(state, level, pos);
                }
                @Override @NullMarked
                public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
                    return super.isBonemealSuccess(level, random, pos, state) && IntStream.rangeClosed(1, 4).allMatch(i -> level.getBlockState(pos.above(i)).isAir());
                }
            }, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.JUNGLE_SAPLING)
                    .sound(SoundType.SPORE_BLOSSOM)
                    .mapColor(MapColor.GRASS));
    public static final Block POTTED_PALM_SAPLING = SkyCore.RegistryPresets.registerBlock("potted_palm_sapling",
            properties -> new FlowerPotBlock(MCD_Blocks.PALM_SAPLING, properties), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.POTTED_JUNGLE_SAPLING));
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}