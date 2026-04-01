package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

import java.lang.invoke.MethodHandles;

public class MCD_ItemGroups {
    public static ItemGroup DUNGEONS_REBORN = SkyCore.BuiltinRegistries.registerItemGroup("dungeons_reborn",
            FabricItemGroup.builder().displayName(Text.literal("Dungeons Reborn")).icon(() -> new ItemStack(MCD_Items.ANCIENT_GOLD_INGOT)).entries((displayContext, entries) -> {
                addCommonAndRareClaymoreVariant(entries, MCD_Items.CLAYMORE);
                addClaymoreVariant(entries, MCD_Items.HEARTSTEALER);
                addClaymoreVariant(entries, MCD_Items.BROADSWORD);
                entries.add(MCD_Items.ROUGH_DIAMOND_SWORD);
                entries.add(MCD_Items.ROUGH_DIAMOND_PICKAXE);
                addCommonAndRareVariant(entries, MCD_Items.STEEL_MACE);
                entries.add(MCD_Items.SUNS_GRACE);
                addCommonAndRareVariant(entries, MCD_Items.HEAVY_CROSSBOW);
                entries.add(MCD_Items.AUTO_CROSSBOW);
                entries.add(MCD_Items.TWIN_BOW);
                addCommonAndRareVariant(entries, MCD_Items.ARTIFACT_IRON_HIDE_AMULET);
                addCommonAndRareVariant(entries, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
                entries.add(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
                entries.add(MCD_Blocks.HIGHLAND_MOSS_CARPET);
                entries.add(MCD_Items.SOUR_BERRIES);
                entries.add(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
                entries.add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
                entries.add(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
                entries.add(MCD_Blocks.MIDNIGHT_SPROUTS);
                entries.add(MCD_Blocks.POP_FLOWER);
                entries.add(MCD_Blocks.MOSSIER_OAK_PLANKS);
                entries.add(MCD_Blocks.MOSSIER_SPRUCE_PLANKS);
                entries.add(MCD_Items.ANCIENT_GOLD_INGOT);
                entries.add(MCD_Blocks.ANCIENT_GOLD_BLOCK);
            }).build());

    private static void addCommonAndRareClaymoreVariant(ItemGroup.Entries entries, Item item) {
        ItemStack commonItem = new ItemStack(item);
        commonItem.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.COMMON);
        DungeonsHelpers.modifyAttackKnockback(commonItem, 0.0);
        entries.add(commonItem);
        ItemStack rareItem = new ItemStack(item);
        rareItem.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.RARE);
        DungeonsHelpers.modifyAttackKnockback(rareItem, 0.0);
        entries.add(rareItem);
    }
    private static void addClaymoreVariant(ItemGroup.Entries entries, Item item) {
        ItemStack claymoreItem = new ItemStack(item);
        DungeonsHelpers.modifyAttackKnockback(claymoreItem, 0.0);
        entries.add(claymoreItem);
    }
    private static void addCommonAndRareVariant(ItemGroup.Entries entries, Item item) {
        ItemStack commonItem = new ItemStack(item);
        commonItem.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.COMMON);
        entries.add(commonItem);
        ItemStack rareItem = new ItemStack(item);
        rareItem.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.RARE);
        entries.add(rareItem);
    }

    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
