package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_ItemTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;

import java.util.concurrent.CompletableFuture;

public class MCD_ItemTagProvider extends SkyCoreDataGenAPI.SC_ItemTagProvider {
    public MCD_ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_BOWS);
        getOrCreateTagBuilder(ItemTags.CROSSBOW_ENCHANTABLE).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS);
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_DURABLE)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_BOWS)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS);
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_CHEST);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_FOOT);
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_HEAD);
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_LEG);
        getOrCreateTagBuilder(ItemTags.AXES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_AXES);
        getOrCreateTagBuilder(ItemTags.HOES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_HOES);
        getOrCreateTagBuilder(ItemTags.PICKAXES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_PICKAXES);
        getOrCreateTagBuilder(ItemTags.SHOVELS).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_SHOVELS);
        getOrCreateTagBuilder(ItemTags.SWORDS).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_SWORDS);

        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_PICKAXES)
                .add(MCD_Items.ROUGH_DIAMOND_PICKAXE);
        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_BOWS)
                .add(MCD_Items.TWIN_BOW)
                .add(MCD_Items.SHORTBOW)
                .add(MCD_Items.LONGBOW);
        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS)
                .add(MCD_Items.AUTO_CROSSBOW)
                .add(MCD_Items.HEAVY_CROSSBOW);
        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_DURABLE)
                .add(MCD_Items.ARTIFACT_IRON_HIDE_AMULET)
                .add(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_SWORDS)
                .add(MCD_Items.ROUGH_DIAMOND_SWORD)
                .add(MCD_Items.STEEL_MACE)
                .add(MCD_Items.SUNS_GRACE)
                .add(MCD_Items.CUTLASS)
                .add(MCD_Items.CLAYMORE)
                .add(MCD_Items.HEARTSTEALER)
                .add(MCD_Items.BROADSWORD);

        createStoneSetTags(MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE);
        createWoodSetTags(
                MCD_ItemTags.PALM_LOGS,
                MCD_Blocks.PALM_BEAM,
                MCD_Blocks.PALM_WOOD,
                MCD_Blocks.STRIPPED_PALM_BEAM,
                MCD_Blocks.STRIPPED_PALM_WOOD,
                MCD_Blocks.PALM_SAPLING,
                MCD_BlockFamilies.PALM
        );

        getOrCreateTagBuilder(ItemTags.FOX_FOOD).add(MCD_Items.SOUR_BERRIES);
    }
}
