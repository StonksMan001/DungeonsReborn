package net.qbaesz13.dungeons_reborn.registries;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.DeathCapMushroomItem;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.IronSkinItem;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.*;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.*;

import java.lang.invoke.MethodHandles;

public class MCD_Items {
    public static final Item TWIN_BOW = SkyCore.BuiltinRegistries.registerItem("twin_bow",
            new TwinBowItem(new Item.Settings()
                    .maxDamage(384)
                    .rarity(Rarity.EPIC)));
    public static final Item SHORTBOW = SkyCore.BuiltinRegistries.registerItem("shortbow",
            new ShortbowItem(new Item.Settings()
                    .maxDamage(290)));
    public static final Item LONGBOW = SkyCore.BuiltinRegistries.registerItem("longbow",
            new LongbowItem(new Item.Settings()
                    .maxDamage(440)));
    public static final Item ROUGH_DIAMOND_SWORD = SkyCore.BuiltinRegistries.registerItem("rough_diamond_sword",
            new RoughDiamondSwordItem(ToolMaterials.DIAMOND, 3, 1.6f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item ROUGH_DIAMOND_PICKAXE = SkyCore.BuiltinRegistries.registerItem("rough_diamond_pickaxe",
            new RoughDiamondPickaxeItem(ToolMaterials.DIAMOND, 3, 1.2f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item STEEL_MACE = SkyCore.BuiltinRegistries.registerItem("steel_mace",
            new SteelMaceItem(MCD_ToolMaterials.DEFAULT, 2, 1.6f, new Item.Settings()));
    public static final Item SUNS_GRACE = SkyCore.BuiltinRegistries.registerItem("suns_grace",
            new SunsGraceItem(MCD_ToolMaterials.DEFAULT, 3, 1.6f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item CLAYMORE = SkyCore.BuiltinRegistries.registerItem("claymore",
            new ClaymoreItem(MCD_ToolMaterials.DEFAULT, 4, 1.0f, new Item.Settings()));
    public static final Item CUTLASS = SkyCore.BuiltinRegistries.registerItem("cutlass",
            new CutlassItem(MCD_ToolMaterials.DEFAULT, 2, 1.6f, new Item.Settings()));
    public static final Item HEARTSTEALER = SkyCore.BuiltinRegistries.registerItem("heartstealer",
            new HeartstealerItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item BROADSWORD = SkyCore.BuiltinRegistries.registerItem("broadsword",
            new BroadswordItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, new Item.Settings()
                    .rarity(Rarity.EPIC)));
    public static final Item LADLE = SkyCore.BuiltinRegistries.registerItem("ladle",
            new Item(new Item.Settings()
                    .maxCount(1)));
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
    public static final Item ANCIENT_GOLD_INGOT = SkyCore.BuiltinRegistries.registerItem("ancient_gold_ingot",
            new Item(new Item.Settings()));
    public static final Item RAW_ANCIENT_GOLD = SkyCore.BuiltinRegistries.registerItem("raw_ancient_gold",
            new Item(new Item.Settings()));
    public static final Item SOUR_BERRIES = SkyCore.BuiltinRegistries.registerItem("sour_berries",
            new AliasedBlockItem(MCD_Blocks.SOUR_BERRY_BUSH, new Item.Settings().food(FoodComponents.SWEET_BERRIES)));
    public static final Item PALM_SIGN = SkyCore.BuiltinRegistries.registerItem("palm_sign",
            new SignItem(new Item.Settings().maxCount(16), MCD_Blocks.PALM_SIGN, MCD_Blocks.PALM_WALL_SIGN));
    public static final Item PALM_HANGING_SIGN = SkyCore.BuiltinRegistries.registerItem("palm_hanging_sign",
            new HangingSignItem(MCD_Blocks.PALM_HANGING_SIGN, MCD_Blocks.PALM_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item PALM_BOAT = TerraformBoatItemHelper.registerBoatItem(DungeonsReborn.identifierOfDungeonsReborn("palm_boat"),
            MCD_TerraformBoatTypes.PALM_BOAT, false);
    public static final Item PALM_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(DungeonsReborn.identifierOfDungeonsReborn("palm_chest_boat"),
            MCD_TerraformBoatTypes.PALM_BOAT, true);
    public static final Item PALM_LEAVES = SkyCore.BuiltinRegistries.registerItem("palm_leaves",
            new AliasedBlockItem(MCD_Blocks.PALM_LEAVES, new Item.Settings()));
    public static final Item PALM_SAPLING = SkyCore.BuiltinRegistries.registerItem("palm_sapling",
            new AliasedBlockItem(MCD_Blocks.PALM_SAPLING, new Item.Settings()));
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
