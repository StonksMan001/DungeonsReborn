package net.qbaesz13.dungeons_reborn.registries;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.DeathCapMushroomItem;
import net.qbaesz13.dungeons_reborn.items.mcd_artifact.IronSkinItem;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.*;
import net.qbaesz13.dungeons_reborn.items.mcd_ranged.*;

import java.lang.invoke.MethodHandles;

public class MCD_Items {
    public static final Item TWIN_BOW = SkyCore.RegistryPresets.registerItem("twin_bow",
            properties -> new TwinBowItem(properties
                    .durability(384)
                    .rarity(Rarity.EPIC)
                    .enchantable(1)));
    public static final Item SHORTBOW = SkyCore.RegistryPresets.registerItem("shortbow",
            properties -> new ShortbowItem(properties
                    .durability(290)));
    public static final Item LONGBOW = SkyCore.RegistryPresets.registerItem("longbow",
            properties -> new LongbowItem(properties
                    .durability(440)));
    public static final Item ROUGH_DIAMOND_SWORD = SkyCore.RegistryPresets.registerItem("rough_diamond_sword",
            properties -> new RoughDiamondSwordItem(ToolMaterial.DIAMOND, 3, 1.6f, properties
                    .rarity(Rarity.EPIC)));
    public static final Item ROUGH_DIAMOND_PICKAXE = SkyCore.RegistryPresets.registerItem("rough_diamond_pickaxe",
            properties -> new RoughDiamondPickaxeItem(ToolMaterial.DIAMOND, 3, 1.2f, properties
                    .rarity(Rarity.EPIC)));
    public static final Item STEEL_MACE = SkyCore.RegistryPresets.registerItem("steel_mace",
            properties -> new SteelMaceItem(MCD_ToolMaterials.DEFAULT, 2, 1.6f, properties));
    public static final Item SUNS_GRACE = SkyCore.RegistryPresets.registerItem("suns_grace",
            properties -> new SunsGraceItem(MCD_ToolMaterials.DEFAULT, 3, 1.6f, properties
                    .rarity(Rarity.RARE)));
    public static final Item CLAYMORE = SkyCore.RegistryPresets.registerItem("claymore",
            properties -> new ClaymoreItem(MCD_ToolMaterials.DEFAULT, 4, 1.0f, properties));
    public static final Item CUTLASS = SkyCore.RegistryPresets.registerItem("cutlass",
            properties -> new CutlassItem(MCD_ToolMaterials.DEFAULT, 2, 1.6f, properties));
    public static final Item HEARTSTEALER = SkyCore.RegistryPresets.registerItem("heartstealer",
            properties -> new HeartstealerItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, properties
                    .rarity(Rarity.RARE)));
    public static final Item BROADSWORD = SkyCore.RegistryPresets.registerItem("broadsword",
            properties -> new BroadswordItem(MCD_ToolMaterials.DEFAULT, 5, 1.0f, properties
                    .rarity(Rarity.EPIC)));
    public static final Item LADLE = SkyCore.RegistryPresets.registerItem("ladle",
            properties -> new Item(properties
                    .stacksTo(1)));
    public static final Item AUTO_CROSSBOW = SkyCore.RegistryPresets.registerItem("auto_crossbow",
            properties -> new AutoCrossbowItem(properties
                    .durability(465)
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                    .enchantable(1)));
    public static final Item HEAVY_CROSSBOW = SkyCore.RegistryPresets.registerItem("heavy_crossbow",
            properties -> new HeavyCrossbowItem(properties
                    .durability(465)
                    .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                    .enchantable(1)));
    public static final Item ARTIFACT_IRON_HIDE_AMULET = SkyCore.RegistryPresets.registerItem("artifact_iron_hide_amulet",
            IronSkinItem::new);
    public static final Item ARTIFACT_DEATH_CAP_MUSHROOM = SkyCore.RegistryPresets.registerItem("artifact_death_cap_mushroom",
            DeathCapMushroomItem::new);
    public static final Item ANCIENT_GOLD_INGOT = SkyCore.RegistryPresets.registerItem("ancient_gold_ingot", Item::new);
    public static final Item RAW_ANCIENT_GOLD = SkyCore.RegistryPresets.registerItem("raw_ancient_gold", Item::new);
    public static final Item SOUR_BERRIES = SkyCore.RegistryPresets.registerItemThatHasBlock("sour_berries",
            MCD_Blocks.SOUR_BERRY_BUSH, new Item.Properties()
                    .food(Foods.SWEET_BERRIES));
    public static final Item PALM_SIGN = SkyCore.RegistryPresets.registerItem("palm_sign",
            properties -> new SignItem(MCD_Blocks.PALM_SIGN, MCD_Blocks.PALM_WALL_SIGN, properties
                    .stacksTo(16)
                    .useBlockDescriptionPrefix()));
    public static final Item PALM_HANGING_SIGN = SkyCore.RegistryPresets.registerItem("palm_hanging_sign",
            properties -> new HangingSignItem(MCD_Blocks.PALM_HANGING_SIGN, MCD_Blocks.PALM_WALL_HANGING_SIGN, properties
                    .stacksTo(16)
                    .useBlockDescriptionPrefix()));
    public static final Item PALM_BOAT = TerraformBoatItemHelper.registerBoatItem(DungeonsReborn.identifierOfDungeonsReborn("palm"), false);
    public static final Item PALM_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(DungeonsReborn.identifierOfDungeonsReborn("palm"), true);
    public static final Item PALM_LEAVES = SkyCore.RegistryPresets.registerItemThatHasBlock("palm_leaves",
            MCD_Blocks.PALM_LEAVES, new Item.Properties());
    public static final Item PALM_SAPLING = SkyCore.RegistryPresets.registerItemThatHasBlock("palm_sapling",
            MCD_Blocks.PALM_SAPLING, new Item.Properties());
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
