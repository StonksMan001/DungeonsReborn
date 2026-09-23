package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_LootTableModifiers {
    private static void init() {
        LootTableEvents.MODIFY.register(((registryKey, builder, lootTableSource, wrapperLookup) -> {
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.AUTO_CROSSBOW, 0.1f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.BROADSWORD, 0.1f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.CLAYMORE, 0.5f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.HEARTSTEALER, 0.1f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, 1.0f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.SUNS_GRACE, 0.03f);
            modifyChestLootTable(registryKey, builder, LootTables.JUNGLE_TEMPLE_CHEST, MCD_Items.TWIN_BOW, 0.2f);
            modifyChestLootTable(registryKey, builder, LootTables.DESERT_PYRAMID_CHEST, MCD_Items.SUNS_GRACE, 0.05f);
            modifyChestLootTable(registryKey, builder, LootTables.SIMPLE_DUNGEON_CHEST, MCD_Items.ROUGH_DIAMOND_SWORD, 0.05f);
            modifyChestLootTable(registryKey, builder, LootTables.SIMPLE_DUNGEON_CHEST, MCD_Items.ROUGH_DIAMOND_PICKAXE, 0.05f);
            modifyChestLootTable(registryKey, builder, LootTables.SIMPLE_DUNGEON_CHEST, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, 0.1f);
            modifyChestLootTable(registryKey, builder, LootTables.ABANDONED_MINESHAFT_CHEST, MCD_Items.ROUGH_DIAMOND_SWORD, 0.025f);
            modifyChestLootTable(registryKey, builder, LootTables.ABANDONED_MINESHAFT_CHEST, MCD_Items.ROUGH_DIAMOND_PICKAXE, 0.025f);
            modifyChestLootTable(registryKey, builder, LootTables.ABANDONED_MINESHAFT_CHEST, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, 0.075f);
            modifyChestLootTable(registryKey, builder, LootTables.PILLAGER_OUTPOST_CHEST, MCD_Items.STEEL_MACE, 0.75f);
            modifyChestLootTable(registryKey, builder, LootTables.PILLAGER_OUTPOST_CHEST, MCD_Items.HEAVY_CROSSBOW, 0.75f);
            modifyChestLootTable(registryKey, builder, LootTables.PILLAGER_OUTPOST_CHEST, MCD_Items.ARTIFACT_IRON_HIDE_AMULET, 1.0f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.ARTIFACT_IRON_HIDE_AMULET, 0.5f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, 0.1f);
            modifyChestLootTable(registryKey, builder, LootTables.NETHER_BRIDGE_CHEST, MCD_Items.CUTLASS, 0.75f);
            modifyChestLootTable(registryKey, builder, LootTables.DESERT_PYRAMID_CHEST, MCD_Items.CUTLASS, 0.5f);
            modifyChestLootTable(registryKey, builder, LootTables.SHIPWRECK_SUPPLY_CHEST, MCD_Items.CUTLASS, 0.5f);
        }));
    }
    public static void modifyChestLootTable(RegistryKey<LootTable> resourceKey, LootTable.Builder builder, RegistryKey<LootTable> chestLootTable, ItemConvertible insertedItem, float chance) {
        modifyChestLootTable(resourceKey, builder, chestLootTable, insertedItem, 1, chance);
    }
    public static void modifyChestLootTable(RegistryKey<LootTable> registryKey, LootTable.Builder builder, RegistryKey<LootTable> chestLootTable, ItemConvertible insertedItem, int rolls, float chance) {
        if (chestLootTable.equals(registryKey)) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(rolls))
                    .conditionally(RandomChanceLootCondition.builder(chance))
                    .with(ItemEntry.builder(insertedItem));
            builder.pool(poolBuilder.build());
        }
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}