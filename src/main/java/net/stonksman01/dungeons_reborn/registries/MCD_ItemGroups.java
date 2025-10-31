package net.stonksman01.dungeons_reborn.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;
import net.stonksman01.dungeons_reborn.components.McdRarity;

public class MCD_ItemGroups {
    public static ItemGroup DUNGEONS_REBORN = SkyCore.BuiltinRegistries.registerItemGroup("dungeons_reborn",
            FabricItemGroup.builder().displayName(Text.literal("Dungeons Reborn")).icon(() -> new ItemStack(MCD_Items.ANCIENT_GOLD_INGOT)).entries((displayContext, entries) -> {
                ItemStack commonSteelMace = new ItemStack(MCD_Items.STEEL_MACE);
                commonSteelMace.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.COMMON);
                entries.add(commonSteelMace);
                ItemStack rareSteelMace = new ItemStack(MCD_Items.STEEL_MACE);
                rareSteelMace.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.RARE);
                entries.add(rareSteelMace);
                entries.add(MCD_Items.ROUGH_DIAMOND_SWORD);
                entries.add(MCD_Items.TWIN_BOW);
                entries.add(MCD_Items.AUTO_CROSSBOW);
                ItemStack commonAmulet = new ItemStack(MCD_Items.ARTIFACT_IRON_HIDE_AMULET);
                commonAmulet.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.COMMON);
                entries.add(commonAmulet);
                ItemStack rareAmulet = new ItemStack(MCD_Items.ARTIFACT_IRON_HIDE_AMULET);
                rareAmulet.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.RARE);
                entries.add(rareAmulet);
                ItemStack commonMushroom = new ItemStack(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
                commonMushroom.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.COMMON);
                entries.add(commonMushroom);
                ItemStack rareMushroom = new ItemStack(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
                rareMushroom.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.RARE);
                entries.add(rareMushroom);
                entries.add(MCD_Blocks.DRIED_MOSS_BLOCK);
                entries.add(MCD_Blocks.DRIED_MOSS_CARPET);
                entries.add(MCD_Blocks.MOSSIER_COBBLESTONE);
                entries.add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
                entries.add(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
                entries.add(MCD_Blocks.MIDNIGHT_SPROUTS);
                entries.add(MCD_Blocks.POP_FLOWER);
                entries.add(MCD_Blocks.MOSSIER_OAK_PLANKS);
                entries.add(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
                entries.add(MCD_Items.ANCIENT_GOLD_INGOT);
                entries.add(MCD_Blocks.ANCIENT_GOLD_BLOCK);
            }).build());
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering ItemGroups");
    }
}
