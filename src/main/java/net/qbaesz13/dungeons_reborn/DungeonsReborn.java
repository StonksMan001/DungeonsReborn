package net.qbaesz13.dungeons_reborn;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;
import net.qbaesz13.dungeons_reborn.registries.*;
import net.qbaesz13.dungeons_reborn.registries.world.MCD_BiomeModifications;
import net.qbaesz13.dungeons_reborn.registries.world.MCD_ConfiguredFeatures;
import net.qbaesz13.dungeons_reborn.registries.world.MCD_PlacedFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsReborn implements ModInitializer {
	public static final String MOD_ID = "dungeons_reborn";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier identifierOfDungeonsReborn(String id) {
		return Identifier.fromNamespaceAndPath(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		MCD_DataFixers.register(); // Should register before everything

		MCD_BlockEntityTypes.register();
		MCD_Blocks.register();
		MCD_BlockFamilies.register();
		MCD_BlockTags.register();
		MCD_Commands.register();
		MCD_DataComponents.register();
		MCD_Enchantments.register();
		MCD_GameRules.register();
		MCD_Items.register();
		MCD_CreativeModeTabs.register();
		MCD_SpecialProperties.register(); // Should register after MCD_Items and MCD_Blocks
		MCD_ItemTags.register();
		MCD_LootTableModifiers.register();
		MCD_Sounds.register();

		MCD_ConfiguredFeatures.register();
		MCD_PlacedFeatures.register(); // Should register after MCD_ConfiguredFeatures
		MCD_BiomeModifications.register(); // Should register after MCD_PlacedFeatures
	}
}