package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;

import java.util.concurrent.CompletableFuture;

public class MCD_LootTableProvider extends SkyCoreDataGenAPI.SC_BlockLootSubProvider {
    public MCD_LootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }
    @Override
    public void generate() {
        HolderGetter<Enchantment> enchantmentHolderGetter = registries.lookupOrThrow(Registries.ENCHANTMENT);

        dropSelf(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        dropSelf(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);

        dropSelf(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
        dropSelf(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        add(MCD_Blocks.MIDNIGHT_SPROUTS, this::createShearsOnlyDrop);
        dropSelf(MCD_Blocks.POP_FLOWER);

        dropSelf(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
        dropSelf(MCD_Blocks.HIGHLAND_MOSS_CARPET);
        add(MCD_Blocks.MEDIUM_HIGHLAND_GRASS, this::createGrassDrops);
        add(MCD_Blocks.SHORT_HIGHLAND_GRASS, this::createShearsOnlyDrop);
        addBerryBushDrops(MCD_Blocks.SOUR_BERRY_BUSH, MCD_Items.SOUR_BERRIES);

        add(MCD_Blocks.MOSSY_OAK_PLANKS, block -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.OAK_PLANKS))));
        add(MCD_Blocks.MOSSY_SPRUCE_PLANKS, block -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.SPRUCE_PLANKS))));

        dropSelf(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        dropSelf(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_STAIRS);
        add(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB, createSlabItemTable(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB));
        dropSelf(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_WALL);

        dropSelf(MCD_Blocks.PALM_TRUNK);
        dropSelf(MCD_Blocks.PALM_BEAM);
        dropSelf(MCD_Blocks.PALM_WOOD);
        dropSelf(MCD_Blocks.STRIPPED_PALM_TRUNK);
        dropSelf(MCD_Blocks.STRIPPED_PALM_BEAM);
        dropSelf(MCD_Blocks.STRIPPED_PALM_WOOD);
        dropSelf(MCD_Blocks.PALM_PLANKS);
        dropSelf(MCD_Blocks.PALM_STAIRS);
        add(MCD_Blocks.PALM_SLAB, createSlabItemTable(MCD_Blocks.PALM_SLAB));
        dropSelf(MCD_Blocks.PALM_FENCE);
        dropSelf(MCD_Blocks.PALM_FENCE_GATE);
        add(MCD_Blocks.PALM_DOOR, createDoorTable(MCD_Blocks.PALM_DOOR));
        dropSelf(MCD_Blocks.PALM_TRAPDOOR);
        dropSelf(MCD_Blocks.PALM_PRESSURE_PLATE);
        dropSelf(MCD_Blocks.PALM_BUTTON);
        dropSelf(MCD_Blocks.PALM_SIGN);
        dropSelf(MCD_Blocks.PALM_HANGING_SIGN);

        add(MCD_Blocks.PALM_LEAVES, LootTable.lootTable()
                .pool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(hasShearsOrSilkTouch())
                        .add(LootItem.lootTableItem(MCD_Blocks.PALM_LEAVES)).build())
                .pool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0.0F, 4.0F))
                        .when(doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionCondition(
                                MCD_Blocks.PALM_LEAVES,
                                LootItem.lootTableItem(MCD_Blocks.PALM_SAPLING)))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                enchantmentHolderGetter.getOrThrow(Enchantments.FORTUNE),
                                1.0F)).build())
                .pool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(this.doesNotHaveShearsOrSilkTouch())
                                .add(this.applyExplosionDecay(
                                                MCD_Blocks.PALM_LEAVES, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentHolderGetter.getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))
                                ).build()
                ));

        dropSelf(MCD_Blocks.PALM_SAPLING);
        dropPottedContents(MCD_Blocks.POTTED_PALM_SAPLING);
    }
}
