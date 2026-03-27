package net.qbaesz13.dungeons_reborn;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.qbaesz13.dungeons_reborn.datagen.*;
import net.qbaesz13.dungeons_reborn.registries.MCD_ConfiguredFeatures;

public class DungeonsRebornDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(MCD_BlockTagProvider::new);
		pack.addProvider(MCD_ItemTagProvider::new);
		pack.addProvider(MCD_LootTableProvider::new);
		pack.addProvider(MCD_ModelProvider::new);
		pack.addProvider(MCD_RecipeProvider::new);
		pack.addProvider(MCD_RegistryDataGenerator::new);
	}
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, MCD_ConfiguredFeatures::bootstrap);
	}
}
