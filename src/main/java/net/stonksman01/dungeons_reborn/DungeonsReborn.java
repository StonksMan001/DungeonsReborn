package net.stonksman01.dungeons_reborn;

import net.fabricmc.api.ModInitializer;

import net.stonksman01.dungeons_reborn.registries.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsReborn implements ModInitializer {
	public static final String MOD_ID = "dungeons_reborn";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MCD_BlockEntities.register();
		MCD_Blocks.register();
		MCD_DataComponentTypes.register();
		MCD_GameRules.register();
		MCD_ItemGroups.register();
		MCD_Items.register();
		MCD_ItemTags.register();
		MCD_Sounds.register();
	}
}