package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_LootTableModifiers {
    private static void init() {
        LootTableEvents.MODIFY.register(((registryKey, builder, lootTableSource, wrapperLookup) -> {
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Items.AUTO_CROSSBOW, 1, 0.1f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Items.BROADSWORD, 1, 0.1f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Items.CLAYMORE, 1, 0.5f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Items.HEARTSTEALER, 1, 0.1f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Blocks.MIDNIGHT_MOSS_BLOCK, 1, 1.0f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Items.SUNS_GRACE, 1, 0.03f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.JUNGLE_TEMPLE, MCD_Items.TWIN_BOW, 1, 0.2f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.DESERT_PYRAMID, MCD_Items.SUNS_GRACE, 1, 0.05f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.SIMPLE_DUNGEON, MCD_Items.ROUGH_DIAMOND_SWORD, 1, 0.05f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.SIMPLE_DUNGEON, MCD_Items.ROUGH_DIAMOND_PICKAXE, 1, 0.05f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.SIMPLE_DUNGEON, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, 1, 0.1f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.ABANDONED_MINESHAFT, MCD_Items.ROUGH_DIAMOND_SWORD, 1, 0.025f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.ABANDONED_MINESHAFT, MCD_Items.ROUGH_DIAMOND_PICKAXE, 1, 0.025f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.ABANDONED_MINESHAFT, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, 1, 0.075f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.PILLAGER_OUTPOST, MCD_Items.STEEL_MACE, 1, 0.75f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.PILLAGER_OUTPOST, MCD_Items.HEAVY_CROSSBOW, 1, 0.75f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.PILLAGER_OUTPOST, MCD_Items.ARTIFACT_IRON_HIDE_AMULET, 1, 1.0f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Items.ARTIFACT_IRON_HIDE_AMULET, 1, 0.5f);
            modifyChestLootTable(registryKey, builder, BuiltInLootTables.WOODLAND_MANSION, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM, 1, 0.1f);
        }));
    }
    public static void modifyChestLootTable(ResourceKey<LootTable> resourceKey, LootTable.Builder builder, ResourceKey<LootTable> chestLootTable, ItemLike insertedItem, int rolls, float chance) {
        if (chestLootTable.equals(resourceKey)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(rolls))
                    .when(LootItemRandomChanceCondition.randomChance(chance))
                    .add(LootItem.lootTableItem(insertedItem));
            builder.pool(poolBuilder.build());
        }
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
