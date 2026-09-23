package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;

import java.util.concurrent.CompletableFuture;

public class MCD_LootTableProvider extends SkyCoreDataGenAPI.SC_BlockLootTableProvider {
    public MCD_LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }
    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        addDrop(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);

        addDrop(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
        addDrop(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        addDrop(MCD_Blocks.MIDNIGHT_SPROUTS, BlockLootTableGenerator::dropsWithShears);
        addDrop(MCD_Blocks.POP_FLOWER);

        addDrop(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
        addDrop(MCD_Blocks.HIGHLAND_MOSS_CARPET);
        addDrop(MCD_Blocks.MEDIUM_HIGHLAND_GRASS, this::shortPlantDrops);
        addDrop(MCD_Blocks.SHORT_HIGHLAND_GRASS, BlockLootTableGenerator::dropsWithShears);
        addBerryBushDrops(MCD_Blocks.SOUR_BERRY_BUSH, MCD_Items.SOUR_BERRIES);

        addDrop(MCD_Blocks.MOSSY_OAK_PLANKS, block -> dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(Items.OAK_PLANKS))));
        addDrop(MCD_Blocks.MOSSY_SPRUCE_PLANKS, block -> dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(Items.SPRUCE_PLANKS))));

        addDrop(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        addDrop(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_STAIRS);
        addDrop(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB, slabDrops(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB));
        addDrop(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_WALL);

        addDrop(MCD_Blocks.PALM_TRUNK);
        addDrop(MCD_Blocks.PALM_BEAM);
        addDrop(MCD_Blocks.PALM_WOOD);
        addDrop(MCD_Blocks.STRIPPED_PALM_TRUNK);
        addDrop(MCD_Blocks.STRIPPED_PALM_BEAM);
        addDrop(MCD_Blocks.STRIPPED_PALM_WOOD);
        addDrop(MCD_Blocks.PALM_PLANKS);
        addDrop(MCD_Blocks.PALM_STAIRS);
        addDrop(MCD_Blocks.PALM_SLAB, slabDrops(MCD_Blocks.PALM_SLAB));
        addDrop(MCD_Blocks.PALM_FENCE);
        addDrop(MCD_Blocks.PALM_FENCE_GATE);
        addDrop(MCD_Blocks.PALM_DOOR, doorDrops(MCD_Blocks.PALM_DOOR));
        addDrop(MCD_Blocks.PALM_TRAPDOOR);
        addDrop(MCD_Blocks.PALM_PRESSURE_PLATE);
        addDrop(MCD_Blocks.PALM_BUTTON);
        addDrop(MCD_Blocks.PALM_SIGN);
        addDrop(MCD_Blocks.PALM_HANGING_SIGN);

        addDrop(MCD_Blocks.PALM_LEAVES, LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(createWithShearsOrSilkTouchCondition())
                        .with(ItemEntry.builder(MCD_Blocks.PALM_LEAVES)))
                .pool(LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(0.0F, 4.0F))
                        .conditionally(createWithoutShearsOrSilkTouchCondition())
                        .with(this.addSurvivesExplosionCondition(
                                MCD_Blocks.PALM_LEAVES,
                                ItemEntry.builder(MCD_Blocks.PALM_SAPLING)))
                        .conditionally(TableBonusLootCondition.builder(
                                impl.getOrThrow(Enchantments.FORTUNE),
                                1.0F)))
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(this.createWithoutShearsOrSilkTouchCondition())
                                .with(this.applyExplosionDecay(
                                                MCD_Blocks.PALM_LEAVES, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))
                                )
                ));

        addDrop(MCD_Blocks.PALM_SAPLING);
        addPottedPlantDrops(MCD_Blocks.POTTED_PALM_SAPLING);
    }
}
