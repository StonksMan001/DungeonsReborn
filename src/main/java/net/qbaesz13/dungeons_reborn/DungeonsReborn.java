package net.qbaesz13.dungeons_reborn;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;
import net.qbaesz13.dungeons_reborn.registries.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsReborn implements ModInitializer {
	public static final String MOD_ID = "dungeons_reborn";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier identifierFromNamespaceDungeonsReborn(String id) {
		return Identifier.fromNamespaceAndPath(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		MCD_DataFixers.register();

		MCD_BlockEntityTypes.register();
		MCD_Blocks.register();
		MCD_Commands.register();
		MCD_CompostableItems.register();
		MCD_DataComponents.register();
		MCD_GameRules.register();
		MCD_Enchantments.register();
		MCD_ItemGroups.register();
		MCD_Items.register();
		MCD_ItemTags.register();
		MCD_LootTableModifiers.register();
		MCD_Sounds.register();
		MCD_ToolMaterials.register();
	}
}