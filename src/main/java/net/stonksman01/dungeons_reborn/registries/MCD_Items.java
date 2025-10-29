package net.stonksman01.dungeons_reborn.registries;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;
import net.stonksman01.dungeons_reborn.items.mcd_artifact.DeathCapMushroomItem;
import net.stonksman01.dungeons_reborn.items.mcd_artifact.IronSkinItem;
import net.stonksman01.dungeons_reborn.items.mcd_meele.RoughDiamondSwordItem;
import net.stonksman01.dungeons_reborn.items.mcd_meele.SteelMaceItem;
import net.stonksman01.dungeons_reborn.items.mcd_ranged.AutoCrossbowItem;
import net.stonksman01.dungeons_reborn.items.mcd_ranged.TwinBowItem;

public class MCD_Items {
    public static final Item TWIN_BOW = SkyCore.BuiltinRegistries.registerItem("twin_bow",
            new TwinBowItem(new Item.Settings().maxDamage(384).rarity(Rarity.EPIC)));
    public static final Item ROUGH_DIAMOND_SWORD = SkyCore.BuiltinRegistries.registerItem("rough_diamond_sword",
            new RoughDiamondSwordItem(ToolMaterials.DIAMOND, 3, 1.6f - 4f, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item STEEL_MACE = SkyCore.BuiltinRegistries.registerItem("steel_mace",
            new SteelMaceItem(MCD_ToolMaterials.STEEL_MACE, 3, 1.6f - 4f, new Item.Settings()));
    public static final Item AUTO_CROSSBOW = SkyCore.BuiltinRegistries.registerItem("auto_crossbow",
            new AutoCrossbowItem(new Item.Settings().maxDamage(465).rarity(Rarity.EPIC)));
    public static final Item ARTIFACT_IRON_HIDE_AMULET = SkyCore.BuiltinRegistries.registerItem("artifact_iron_hide_amulet",
            new IronSkinItem(new Item.Settings()));
    public static final Item ARTIFACT_DEATH_CAP_MUSHROOM = SkyCore.BuiltinRegistries.registerItem("artifact_death_cap_mushroom",
            new DeathCapMushroomItem(new Item.Settings()));
    public static final Item ANCIENT_GOLD_INGOT = SkyCore.BuiltinRegistries.registerItem("ancient_gold_ingot",
            new Item(new Item.Settings()));
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Items");
    }
}
