package net.stonksman01.dungeons_reborn;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.stonksman01.dungeons_reborn.datagen.*;

public class DungeonsRebornDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(MCD_BlockTagProvider::new);
		pack.addProvider(MCD_ItemTagProvider::new);
		pack.addProvider(MCD_LootTableProvider::new);
		pack.addProvider(MCD_ModelProvider::new);
		pack.addProvider(MCD_RecipeProvider::new);
	}
}
