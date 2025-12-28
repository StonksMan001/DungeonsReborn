package net.stonksman01.dungeons_reborn.registries;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.stonksman01.dungeons_reborn.DungeonsReborn;

import java.util.List;

public class MCD_LootTableModifiers {
    private static void init() {
        LootTableEvents.MODIFY.register(((registryKey, builder, lootTableSource, wrapperLookup) -> {
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.AUTO_CROSSBOW, 1, 0.05f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.BROADSWORD, 1, 0.05f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.CLAYMORE, 1, 0.5f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, 1, 0.2f);
            modifyChestLootTable(registryKey, builder, LootTables.JUNGLE_TEMPLE_CHEST, MCD_Items.TWIN_BOW, 1, 0.1f);
            modifyChestLootTable(registryKey, builder, LootTables.SIMPLE_DUNGEON_CHEST, MCD_Items.ROUGH_DIAMOND_SWORD, 1, 0.025f);
            modifyChestLootTable(registryKey, builder, LootTables.SIMPLE_DUNGEON_CHEST, MCD_Items.ROUGH_DIAMOND_PICKAXE, 1, 0.025f);
            modifyChestLootTable(registryKey, builder, LootTables.PILLAGER_OUTPOST_CHEST, MCD_Items.STEEL_MACE, 1, 0.5f);
            modifyChestLootTable(registryKey, builder, LootTables.PILLAGER_OUTPOST_CHEST, MCD_Items.ARTIFACT_IRON_HIDE_AMULET, 1, 1.0f);
            modifyChestLootTable(registryKey, builder, LootTables.WOODLAND_MANSION_CHEST, MCD_Items.ARTIFACT_IRON_HIDE_AMULET, 1, 0.1f);
            modifyChestLootTables(registryKey, builder, List.of(LootTables.WOODLAND_MANSION_CHEST, LootTables.SIMPLE_DUNGEON_CHEST), MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, 1, 0.1f);
            //TODO: Sun's Grace, Heartstealer, Heavy Crossbow
        }));
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
    public static void modifyChestLootTables(RegistryKey<LootTable> registryKey, LootTable.Builder builder, List<RegistryKey<LootTable>> chestLootTables, ItemConvertible insertedItem, int rolls, float chance) {
        if (chestLootTables.contains(registryKey)) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(rolls))
                    .conditionally(RandomChanceLootCondition.builder(chance))
                    .with(ItemEntry.builder(insertedItem));
            builder.pool(poolBuilder.build());
        }
    }
    public static void register() {
        init();
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering LootTableModifiers");
    }
}