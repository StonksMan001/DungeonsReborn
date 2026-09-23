package net.qbaesz13.dungeons_reborn;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.qbaesz13.dungeons_reborn.datagen.*;
import net.qbaesz13.dungeons_reborn.registries.world.MCD_ConfiguredFeatures;
import net.qbaesz13.dungeons_reborn.registries.world.MCD_PlacedFeatures;
import org.jspecify.annotations.NonNull;

public class DungeonsRebornDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(MCD_BlockTagsProvider::new);
		pack.addProvider(MCD_ItemTagsProvider::new);
		pack.addProvider(MCD_LootTableProvider::new);
		pack.addProvider(MCD_ModelProvider::new);
		pack.addProvider(MCD_RecipeProvider::new);
		pack.addProvider(MCD_RegistryDataGenerator::new);
	}
	@Override
	public void buildRegistry(@NonNull RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, MCD_ConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, MCD_PlacedFeatures::bootstrap);
	}
}