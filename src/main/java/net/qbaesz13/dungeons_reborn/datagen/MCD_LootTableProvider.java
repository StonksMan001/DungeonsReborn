package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.data.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;

import java.util.concurrent.CompletableFuture;

public class MCD_LootTableProvider extends FabricBlockLootTableProvider {
    public MCD_LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }
    @Override
    public void generate() {
        addDrop(MCD_Blocks.ANCIENT_GOLD_BLOCK);
        addDrop(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
        addDrop(MCD_Blocks.HIGHLAND_MOSS_CARPET);
        addDrop(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
        addDrop(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
        addDrop(MCD_Blocks.MIDNIGHT_SPROUTS);
        addDrop(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
        addDrop(MCD_Blocks.MOSSIER_OAK_PLANKS, block -> dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(Items.OAK_PLANKS))));
        addDrop(MCD_Blocks.MOSSIER_SPRUCE_PLANKS, block -> dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(Items.SPRUCE_PLANKS))));
        addDrop(MCD_Blocks.POP_FLOWER);
        addBerryBushDrops(MCD_Blocks.SOUR_BERRY_BUSH, MCD_Items.SOUR_BERRIES);
    }
    /**
     * Based on {@link VanillaBlockLootTableGenerator#generate}
     */
    private void addBerryBushDrops(Block berryBush, Item drop) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        this.addDrop(
                Blocks.SWEET_BERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.builder()
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(berryBush).properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 3))
                                                )
                                                .with(ItemEntry.builder(drop))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                )
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(berryBush).properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 2))
                                                )
                                                .with(ItemEntry.builder(drop))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );
    }
}
