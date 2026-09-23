package net.qbaesz13.dungeons_reborn.registries;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

import java.lang.invoke.MethodHandles;

public class MCD_CreativeModeTabs {
    public static CreativeModeTab DUNGEONS_REBORN = SkyCore.RegistryPresets.registerCreativeModeTab("dungeons_reborn",
            FabricCreativeModeTab.builder().title(Component.literal("Dungeons Reborn")).icon(() -> new ItemStack(MCD_Items.ANCIENT_GOLD_INGOT)).displayItems((displayParameters, output) -> {
                addCommonAndRareClaymoreVariant(output, MCD_Items.CLAYMORE);
                addClaymoreVariant(output, MCD_Items.HEARTSTEALER);
                addClaymoreVariant(output, MCD_Items.BROADSWORD);
                output.accept(MCD_Items.ROUGH_DIAMOND_SWORD);
                output.accept(MCD_Items.ROUGH_DIAMOND_PICKAXE);
                addCommonAndRareCutlassVariant(output, MCD_Items.CUTLASS);
                output.accept(MCD_Items.LADLE);
                addCommonAndRareVariant(output, MCD_Items.STEEL_MACE);
                output.accept(MCD_Items.SUNS_GRACE);
                addCommonAndRareVariant(output, MCD_Items.HEAVY_CROSSBOW);
                output.accept(MCD_Items.AUTO_CROSSBOW);
                output.accept(MCD_Items.TWIN_BOW);
                output.accept(MCD_Items.SHORTBOW);
                output.accept(MCD_Items.LONGBOW);
                addCommonAndRareVariant(output, MCD_Items.ARTIFACT_IRON_HIDE_AMULET);
                addCommonAndRareVariant(output, MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
                output.accept(MCD_Blocks.HIGHLAND_MOSS_BLOCK);
                output.accept(MCD_Blocks.HIGHLAND_MOSS_CARPET);
                output.accept(MCD_Blocks.MEDIUM_HIGHLAND_GRASS);
                output.accept(MCD_Blocks.SHORT_HIGHLAND_GRASS);
                output.accept(MCD_Items.SOUR_BERRIES);
                output.accept(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE);
                output.accept(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_STAIRS);
                output.accept(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_SLAB);
                output.accept(MCD_Blocks.MIDNIGHT_MOSSY_COBBLESTONE_WALL);
                output.accept(MCD_Blocks.MIDNIGHT_MOSS_BLOCK);
                output.accept(MCD_Blocks.MIDNIGHT_MOSS_CARPET);
                output.accept(MCD_Blocks.POP_FLOWER);
                output.accept(MCD_Blocks.MIDNIGHT_SPROUTS);

                output.accept(MCD_Blocks.PALM_TRUNK);
                output.accept(MCD_Blocks.PALM_BEAM);
                output.accept(MCD_Blocks.PALM_WOOD);
                output.accept(MCD_Blocks.STRIPPED_PALM_TRUNK);
                output.accept(MCD_Blocks.STRIPPED_PALM_BEAM);
                output.accept(MCD_Blocks.STRIPPED_PALM_WOOD);
                output.accept(MCD_Blocks.PALM_PLANKS);
                output.accept(MCD_Blocks.PALM_STAIRS);
                output.accept(MCD_Blocks.PALM_SLAB);
                output.accept(MCD_Blocks.PALM_FENCE);
                output.accept(MCD_Blocks.PALM_FENCE_GATE);
                output.accept(MCD_Blocks.PALM_DOOR);
                output.accept(MCD_Blocks.PALM_TRAPDOOR);
                output.accept(MCD_Blocks.PALM_PRESSURE_PLATE);
                output.accept(MCD_Blocks.PALM_BUTTON);
                output.accept(MCD_Items.PALM_SIGN);
                output.accept(MCD_Items.PALM_HANGING_SIGN);
                output.accept(MCD_Items.PALM_BOAT);
                output.accept(MCD_Items.PALM_CHEST_BOAT);
                output.accept(MCD_Blocks.PALM_LEAVES);
                output.accept(MCD_Items.PALM_SAPLING);

                output.accept(MCD_Blocks.MOSSY_OAK_PLANKS);
                output.accept(MCD_Blocks.MOSSY_SPRUCE_PLANKS);
                output.accept(MCD_Items.ANCIENT_GOLD_INGOT);
                output.accept(MCD_Blocks.ANCIENT_GOLD_BLOCK);
                output.accept(MCD_Items.RAW_ANCIENT_GOLD);
                output.accept(MCD_Blocks.RAW_ANCIENT_GOLD_BLOCK);
            }).build());

    private static void addCommonAndRareClaymoreVariant(CreativeModeTab.Output output, Item item) {
        ItemStack commonItem = new ItemStack(item);
        commonItem.set(MCD_DataComponents.MCD_RARITY, McdRarity.COMMON);
        DungeonsHelpers.modifyAttackKnockback(commonItem, 0.0);
        output.accept(commonItem);
        ItemStack rareItem = new ItemStack(item);
        rareItem.set(MCD_DataComponents.MCD_RARITY, McdRarity.RARE);
        DungeonsHelpers.modifyAttackKnockback(rareItem, 0.0);
        output.accept(rareItem);
    }
    private static void addCommonAndRareCutlassVariant(CreativeModeTab.Output output, Item item) {
        ItemStack commonItem = new ItemStack(item);
        commonItem.set(MCD_DataComponents.MCD_RARITY, McdRarity.COMMON);
        DungeonsHelpers.modifyAttackDamage(commonItem, 6.0);
        output.accept(commonItem);
        ItemStack rareItem = new ItemStack(item);
        rareItem.set(MCD_DataComponents.MCD_RARITY, McdRarity.RARE);
        DungeonsHelpers.modifyAttackDamage(rareItem, 7.0);
        output.accept(rareItem);
    }
    private static void addClaymoreVariant(CreativeModeTab.Output output, Item item) {
        ItemStack claymoreItem = new ItemStack(item);
        DungeonsHelpers.modifyAttackKnockback(claymoreItem, 0.0);
        output.accept(claymoreItem);
    }
    private static void addCommonAndRareVariant(CreativeModeTab.Output output, Item item) {
        ItemStack commonItem = new ItemStack(item);
        commonItem.set(MCD_DataComponents.MCD_RARITY, McdRarity.COMMON);
        output.accept(commonItem);
        ItemStack rareItem = new ItemStack(item);
        rareItem.set(MCD_DataComponents.MCD_RARITY, McdRarity.RARE);
        output.accept(rareItem);
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
