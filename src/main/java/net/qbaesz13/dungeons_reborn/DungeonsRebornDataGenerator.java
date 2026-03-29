package net.qbaesz13.dungeons_reborn;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.qbaesz13.dungeons_reborn.datagen.*;
import org.jspecify.annotations.NonNull;

public class DungeonsRebornDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(MCD_BlockTagProvider::new);
		pack.addProvider(MCD_ItemTagProvider::new);
		pack.addProvider(MCD_LootTableProvider::new);
		pack.addProvider(MCD_ModelProvider::new);
		pack.addProvider(MCD_RecipeProvider::new);
		pack.addProvider(MCD_RegistryDataGenerator::new);
	}
}