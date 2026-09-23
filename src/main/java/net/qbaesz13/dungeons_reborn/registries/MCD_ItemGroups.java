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
                addCommonAndRareCutlassVariant(entries, MCD_Items.CUTLASS);
                entries.add(MCD_Items.LADLE);
                addCommonAndRareVariant(entries, MCD_Items.STEEL_MACE);
                entries.add(MCD_Items.SUNS_GRACE);
                addCommonAndRareVariant(entries, MCD_Items.HEAVY_CROSSBOW);
                entries.add(MCD_Items.AUTO_CROSSBOW);
                entries.add(MCD_Items.TWIN_BOW);
                entries.add(MCD_Items.SHORTBOW);
                entries.add(MCD_Items.LONGBOW);
                addCommonAndRareVariant(entries, MCD_Items.ARTIFACT_IRON_HIDE_AMULET);
                addCommonAndRareVariant(entries, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
                entries.add(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
                entries.add(MCD_Blocks.HIGHLAND_MOSS_CARPET);
                entries.add(MCD_Blocks.MEDIUM_HIGHLAND_GRASS);
                entries.add(MCD_Blocks.SHORT_HIGHLAND_GRASS);
                entries.add(MCD_Items.SOUR_BERRIES);
                entries.add(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
                entries.add(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_STAIRS);
                entries.add(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB);
                entries.add(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_WALL);
                entries.add(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
                entries.add(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
                entries.add(MCD_Blocks.POP_FLOWER);
                entries.add(MCD_Blocks.MIDNIGHT_SPROUTS);

                entries.add(MCD_Blocks.PALM_TRUNK);
                entries.add(MCD_Blocks.PALM_BEAM);
                entries.add(MCD_Blocks.PALM_WOOD);
                entries.add(MCD_Blocks.STRIPPED_PALM_TRUNK);
                entries.add(MCD_Blocks.STRIPPED_PALM_BEAM);
                entries.add(MCD_Blocks.STRIPPED_PALM_WOOD);
                entries.add(MCD_Blocks.PALM_PLANKS);
                entries.add(MCD_Blocks.PALM_STAIRS);
                entries.add(MCD_Blocks.PALM_SLAB);
                entries.add(MCD_Blocks.PALM_FENCE);
                entries.add(MCD_Blocks.PALM_FENCE_GATE);
                entries.add(MCD_Blocks.PALM_DOOR);
                entries.add(MCD_Blocks.PALM_TRAPDOOR);
                entries.add(MCD_Blocks.PALM_PRESSURE_PLATE);
                entries.add(MCD_Blocks.PALM_BUTTON);
                entries.add(MCD_Items.PALM_SIGN);
                entries.add(MCD_Items.PALM_HANGING_SIGN);
                entries.add(MCD_Items.PALM_BOAT);
                entries.add(MCD_Items.PALM_CHEST_BOAT);
                entries.add(MCD_Blocks.PALM_LEAVES);
                entries.add(MCD_Items.PALM_SAPLING);

                entries.add(MCD_Blocks.MOSSY_OAK_PLANKS);
                entries.add(MCD_Blocks.MOSSY_SPRUCE_PLANKS);
                entries.add(MCD_Items.ANCIENT_GOLD_INGOT);
                entries.add(MCD_Blocks.ANCIENT_GOLD_BLOCK);
                entries.add(MCD_Items.RAW_ANCIENT_GOLD);
                entries.add(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
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
    private static void addCommonAndRareCutlassVariant(ItemGroup.Entries entries, Item item) {
        ItemStack commonItem = new ItemStack(item);
        commonItem.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.COMMON);
        DungeonsHelpers.modifyAttackDamage(commonItem, 6.0);
        entries.add(commonItem);
        ItemStack rareItem = new ItemStack(item);
        rareItem.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.RARE);
        DungeonsHelpers.modifyAttackDamage(rareItem, 7.0);
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
