package net.stonksman01.dungeons_reborn;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Items;
import net.stonksman01.dungeons_reborn.registries.*;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsReborn implements ModInitializer {
	public static final String MOD_ID = "dungeons_reborn";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MCD_DataFixers.register();

		MCD_BlockEntities.register();
		MCD_Blocks.register();
		MCD_CompostableItems.register();
		MCD_DataComponentTypes.register();
		MCD_Enchantments.register();
		MCD_GameRules.register();
		MCD_ItemGroups.register();
		MCD_Items.register();
		MCD_ItemTags.register();
		MCD_LootTableModifiers.register();
		MCD_Models.register();
		MCD_Sounds.register();
	}
}