package net.stonksman01.dungeons_reborn.registries;

import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCore;
import net.stonksman01.dungeons_reborn.items.mcd_artifact.DeathCapMushroomItem;
import net.stonksman01.dungeons_reborn.items.mcd_artifact.IronSkinItem;
import net.stonksman01.dungeons_reborn.items.mcd_meele.*;
import net.stonksman01.dungeons_reborn.items.mcd_ranged.AutoCrossbowItem;
import net.stonksman01.dungeons_reborn.items.mcd_ranged.HeavyCrossbowItem;
import net.stonksman01.dungeons_reborn.items.mcd_ranged.TwinBowItem;

public class MCD_Items {
    public static final Item TWIN_BOW = SkyCore.BuiltinRegistries.registerItem("twin_bow",
            new TwinBowItem(new Item.Settings()
                    .maxDamage(384)
                    .rarity(Rarity.EPIC)));
    public static final Item ROUGH_DIAMOND_SWORD = SkyCore.BuiltinRegistries.registerItem("rough_diamond_sword",
            new RoughDiamondSwordItem(ToolMaterials.DIAMOND, 3, 1.6f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item ROUGH_DIAMOND_PICKAXE = SkyCore.BuiltinRegistries.registerItem("rough_diamond_pickaxe",
            new RoughDiamondPickaxeItem(ToolMaterials.DIAMOND, 1, 1.2f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item STEEL_MACE = SkyCore.BuiltinRegistries.registerItem("steel_mace",
            new SteelMaceItem(MCD_ToolMaterials.DEFAULT, 2, 1.6f, new Item.Settings()));
    public static final Item SUNS_GRACE = SkyCore.BuiltinRegistries.registerItem("suns_grace",
            new SunsGraceItem(MCD_ToolMaterials.DEFAULT, 3, 1.6f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item CLAYMORE = SkyCore.BuiltinRegistries.registerItem("claymore",
            new ClaymoreItem(MCD_ToolMaterials.DEFAULT, 4, 1.0f, new Item.Settings()));
    public static final Item HEARTSTEALER = SkyCore.BuiltinRegistries.registerItem("heartstealer",
            new HeartstealerItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item BROADSWORD = SkyCore.BuiltinRegistries.registerItem("broadsword",
            new BroadswordItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item AUTO_CROSSBOW = SkyCore.BuiltinRegistries.registerItem("auto_crossbow",
            new AutoCrossbowItem(new Item.Settings()
                    .maxDamage(465)
                    .rarity(Rarity.EPIC)));
    public static final Item HEAVY_CROSSBOW = SkyCore.BuiltinRegistries.registerItem("heavy_crossbow",
            new HeavyCrossbowItem(new Item.Settings()
                    .maxDamage(465)));
    public static final Item ARTIFACT_IRON_HIDE_AMULET = SkyCore.BuiltinRegistries.registerItem("artifact_iron_hide_amulet",
            new IronSkinItem(new Item.Settings()));
    public static final Item ARTIFACT_DEATH_CAP_MUSHROOM = SkyCore.BuiltinRegistries.registerItem("artifact_death_cap_mushroom",
            new DeathCapMushroomItem(new Item.Settings()));
    public static final Item SOUR_BERRIES = SkyCore.BuiltinRegistries.registerItem("sour_berries",
            new AliasedBlockItem(MCD_Blocks.SOUR_BERRY_BUSH, new Item.Settings().food(FoodComponents.SWEET_BERRIES)));
    public static final Item ANCIENT_GOLD_INGOT = SkyCore.BuiltinRegistries.registerItem("ancient_gold_ingot",
            new Item(new Item.Settings()));
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Items");
    }
}
