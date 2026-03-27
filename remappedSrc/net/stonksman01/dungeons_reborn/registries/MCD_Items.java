package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Rarity;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.DeathCapMushroomItem;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.IronSkinItem;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.*;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.AutoCrossbowItem;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.TwinBowItem;

import java.util.List;

public class MCD_Items {
    public static final Item TWIN_BOW = SkyCore.BuiltinRegistries.registerItem("twin_bow",
            settings -> new TwinBowItem(settings
                    .maxDamage(384)
                    .rarity(Rarity.EPIC)
                    .enchantable(1)));
    public static final Item ROUGH_DIAMOND_SWORD = SkyCore.BuiltinRegistries.registerItem("rough_diamond_sword",
            settings -> new RoughDiamondSwordItem(ToolMaterial.DIAMOND, 3, 1.6f - 4f, settings
                    .rarity(Rarity.EPIC)));
    public static final Item ROUGH_DIAMOND_PICKAXE = SkyCore.BuiltinRegistries.registerItem("rough_diamond_pickaxe",
            settings -> new RoughDiamondPickaxeItem(ToolMaterial.DIAMOND, 1, 1.2f - 4f, settings
                    .rarity(Rarity.EPIC)));
    public static final Item STEEL_MACE = SkyCore.BuiltinRegistries.registerItem("steel_mace",
            settings -> new SteelMaceItem(MCD_ToolMaterials.STEEL_MACE, 2, 1.6f - 4f, settings));
    public static final Item CLAYMORE = SkyCore.BuiltinRegistries.registerItem("claymore",
            settings -> new ClaymoreItem(MCD_ToolMaterials.CLAYMORE, 4, 1.0f - 4f, settings));
    public static final Item BROADSWORD = SkyCore.BuiltinRegistries.registerItem("broadsword",
            settings -> new BroadswordItem(MCD_ToolMaterials.CLAYMORE, 4, 1.0f - 4f, settings
                    .rarity(Rarity.EPIC)));
    public static final Item AUTO_CROSSBOW = SkyCore.BuiltinRegistries.registerItem("auto_crossbow",
            settings -> new AutoCrossbowItem(settings
                    .maxDamage(465)
                    .rarity(Rarity.EPIC)
                    .component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT)
                    .enchantable(1)));
    public static final Item ARTIFACT_IRON_HIDE_AMULET = SkyCore.BuiltinRegistries.registerItem("artifact_iron_hide_amulet",
            settings -> new IronSkinItem(settings
                    .useCooldown(25.0f)));
    public static final Item ARTIFACT_DEATH_CAP_MUSHROOM = SkyCore.BuiltinRegistries.registerItem("artifact_death_cap_mushroom",
            settings -> new DeathCapMushroomItem(settings
                    .useCooldown(30.0f)));
    public static final Item SOUR_BERRIES = SkyCore.BuiltinRegistries.registerItemThatHasBlock("sour_berries",
                    MCD_Blocks.SOUR_BERRY_BUSH, new Item.Settings()
                    .food(FoodComponents.SWEET_BERRIES));

    public static final Item ANCIENT_GOLD_INGOT = SkyCore.BuiltinRegistries.registerItem("ancient_gold_ingot", Item::new);
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Items");
    }
}
