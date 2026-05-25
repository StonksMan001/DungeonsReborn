package net.qbaesz13.dungeons_reborn.registries;

import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataFixerAPI;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_DataFixers {
    /**
     * Section#1 marks DataFixers ensuring compatibility between Dungeons Reborn and its predecessor (SkyBlock Mod: MCD Module)
     */
    private static void init() {
        /* <Section#1> */
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:midnight_mossy_cobblestone", "skyblock:mcd__mossier_cobblestone");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:mossy_oak_planks", "skyblock:mcd__mossier_oak_planks");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:mossy_spruce_planks", "skyblock:mcd__mossier_spruce_planks");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:highland_moss_block", "skyblock:mcd__dried_moss_block");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:highland_moss_carpet", "skyblock:mcd__dried_moss_carpet");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:midnight_moss_block", "skyblock:mcd__midnight_moss_block");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:midnight_moss_carpet", "skyblock:mcd__midnight_moss_carpet");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:midnight_sprouts", "skyblock:mcd__midnight_sprouts");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:pop_flower", "skyblock:mcd__pop_flower");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:ancient_gold_block", "skyblock:mcd__ancient_gold_block");
        SkyCoreDataFixerAPI.registerItemNameFix("dungeons_reborn:twin_bow", "skyblock:mcd__twin_bow");
        SkyCoreDataFixerAPI.registerItemNameFix("dungeons_reborn:rough_diamond_sword", "skyblock:mcd__rough_diamond_sword");
        SkyCoreDataFixerAPI.registerItemNameFix("dungeons_reborn:steel_mace", "skyblock:mcd__steel_mace");
        SkyCoreDataFixerAPI.registerItemNameFix("dungeons_reborn:auto_crossbow", "skyblock:mcd__auto_crossbow");
        SkyCoreDataFixerAPI.registerItemNameFix("dungeons_reborn:artifact_iron_hide_amulet", "skyblock:mcd__artifact_iron_hide_amulet");
        SkyCoreDataFixerAPI.registerItemNameFix("dungeons_reborn:artifact_death_cap_mushroom", "skyblock:mcd__artifact_death_cap_mushroom");
        SkyCoreDataFixerAPI.registerItemNameFix("dungeons_reborn:ancient_gold_ingot", "skyblock:mcd__ancient_gold_ingot");
        /* </Section#1> */

        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:highland_moss_block", "dungeons_reborn:dried_moss_block");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:highland_moss_carpet", "dungeons_reborn:dried_moss_carpet");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:midnight_mossy_cobblestone", "dungeons_reborn:mossier_cobblestone");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:mossy_oak_planks", "dungeons_reborn:mossier_oak_planks");
        SkyCoreDataFixerAPI.registerBlockAndItemNameFix("dungeons_reborn:mossy_spruce_planks", "dungeons_reborn:mossier_spruce_planks");
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
