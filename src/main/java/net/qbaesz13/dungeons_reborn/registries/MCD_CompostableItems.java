package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

public class MCD_CompostableItems {
    private static void init() {
        CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.HIGHLAND_MOSS_BLOCK, DungeonsHelpers.getCompostingValue(Blocks.MOSS_BLOCK));
        CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.HIGHLAND_MOSS_CARPET, DungeonsHelpers.getCompostingValue(Blocks.MOSS_CARPET));
        CompostingChanceRegistry.INSTANCE.add(MCD_Items.SOUR_BERRIES, DungeonsHelpers.getCompostingValue(Items.SWEET_BERRIES));
        CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK, DungeonsHelpers.getCompostingValue(Blocks.MOSS_BLOCK));
        CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_MOSS_CARPET, DungeonsHelpers.getCompostingValue(Blocks.MOSS_CARPET));
        CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.MIDNIGHT_SPROUTS, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
        CompostingChanceRegistry.INSTANCE.add(MCD_Blocks.POP_FLOWER, DungeonsHelpers.getCompostingValue(Blocks.SHORT_GRASS));
    }
    public static void register() {
        init();
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering CompostableItems");
    }
}
