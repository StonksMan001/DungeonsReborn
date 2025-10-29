package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import net.stonksman01.dungeons_reborn.registries.MCD_Blocks;

import java.util.concurrent.CompletableFuture;

public class MCD_LootTableProvider extends FabricBlockLootTableProvider {
    public MCD_LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }
    @Override
    public void generate() {
        addDrop(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        addDrop(MCD_Blocks.DRIED_MOSS_BLOCK);
        addDrop(MCD_Blocks.DRIED_MOSS_CARPET);
        addDrop(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
        addDrop(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        addDrop(MCD_Blocks.MIDNIGHT_SPROUTS);
        addDrop(MCD_Blocks.MOSSIER_COBBLESTONE);
        addDrop(MCD_Blocks.MOSSIER_OAK_PLANKS, block -> dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(Items.OAK_PLANKS))));
        addDrop(MCD_Blocks.MOSSIER_SPRUCE_PLANKS, block -> dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(Items.SPRUCE_PLANKS))));
        addDrop(MCD_Blocks.POP_FLOWER);
    }
}
