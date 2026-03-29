package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.DeathCapMushroomItem;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.IronSkinItem;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.*;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.AutoCrossbowItem;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.HeavyCrossbowItem;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.TwinBowItem;

import java.lang.invoke.MethodHandles;

public class MCD_Items {
    public static final Item TWIN_BOW = SkyCore.RegistryPresets.registerItem("twin_bow",
            settings -> new TwinBowItem(settings
                    .durability(384)
                    .rarity(Rarity.EPIC)
                    .enchantable(1)));
    public static final Item ROUGH_DIAMOND_SWORD = SkyCore.RegistryPresets.registerItem("rough_diamond_sword",
            settings -> new RoughDiamondSwordItem(ToolMaterial.DIAMOND, 3, 1.6f, settings
                    .rarity(Rarity.EPIC)));
    public static final Item ROUGH_DIAMOND_PICKAXE = SkyCore.RegistryPresets.registerItem("rough_diamond_pickaxe",
            settings -> new RoughDiamondPickaxeItem(ToolMaterial.DIAMOND, 3, 1.2f, settings
                    .rarity(Rarity.EPIC)));
    public static final Item STEEL_MACE = SkyCore.RegistryPresets.registerItem("steel_mace",
            settings -> new SteelMaceItem(MCD_ToolMaterials.DEFAULT, 2, 1.6f, settings));
    public static final Item SUNS_GRACE = SkyCore.RegistryPresets.registerItem("suns_grace",
            settings -> new SunsGraceItem(MCD_ToolMaterials.DEFAULT, 3, 1.6f, settings
                    .rarity(Rarity.RARE)));
    public static final Item CLAYMORE = SkyCore.RegistryPresets.registerItem("claymore",
            settings -> new ClaymoreItem(MCD_ToolMaterials.DEFAULT, 4, 1.0f, settings));
    public static final Item HEARTSTEALER = SkyCore.RegistryPresets.registerItem("heartstealer",
            settings -> new HeartstealerItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, settings
                    .rarity(Rarity.RARE)));
    public static final Item BROADSWORD = SkyCore.RegistryPresets.registerItem("broadsword",
            settings -> new BroadswordItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, settings
                    .rarity(Rarity.EPIC)));
    public static final Item AUTO_CROSSBOW = SkyCore.RegistryPresets.registerItem("auto_crossbow",
            settings -> new AutoCrossbowItem(settings
                    .durability(465)
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                    .enchantable(1)));
    public static final Item HEAVY_CROSSBOW = SkyCore.RegistryPresets.registerItem("heavy_crossbow",
            settings -> new HeavyCrossbowItem(settings
                    .durability(465)
                    .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                    .enchantable(1)));
    public static final Item ARTIFACT_IRON_HIDE_AMULET = SkyCore.RegistryPresets.registerItem("artifact_iron_hide_amulet",
            IronSkinItem::new);
    public static final Item ARTIFACT_DEATH_CAP_MUSHROOM = SkyCore.RegistryPresets.registerItem("artifact_death_cap_mushroom",
            DeathCapMushroomItem::new);
    public static final Item SOUR_BERRIES = SkyCore.RegistryPresets.registerItemThatHasBlock("sour_berries",
            MCD_Blocks.SOUR_BERRY_BUSH, new Item.Properties()
                    .food(Foods.SWEET_BERRIES));

    public static final Item ANCIENT_GOLD_INGOT = SkyCore.RegistryPresets.registerItem("ancient_gold_ingot", Item::new);
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
