package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreDataGenAPI;
import net.qbaesz13.dungeons_reborn.registries.MCD_BlockFamilies;
import net.qbaesz13.dungeons_reborn.registries.MCD_Blocks;
import net.qbaesz13.dungeons_reborn.registries.MCD_ItemTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MCD_ItemTagsProvider extends SkyCoreDataGenAPI.SC_ItemTagsProvider {
    public MCD_ItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }
    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        tag(ItemTags.BOW_ENCHANTABLE).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_BOWS);
        tag(ItemTags.CROSSBOW_ENCHANTABLE).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS);
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_DURABLE)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_BOWS)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS);
        tag(ItemTags.CHEST_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_CHEST);
        tag(ItemTags.FOOT_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_FOOT);
        tag(ItemTags.HEAD_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_HEAD);
        tag(ItemTags.LEG_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_LEG);
        tag(ItemTags.AXES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_AXES);
        tag(ItemTags.HOES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_HOES);
        tag(ItemTags.PICKAXES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_PICKAXES);
        tag(ItemTags.SHOVELS).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_SHOVELS);
        tag(ItemTags.SWORDS).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_SWORDS);

        tag(MCD_ItemTags.HAS_DURABILITY_PICKAXES)
                .add(SkyCore.getResourceKey(MCD_Items.ROUGH_DIAMOND_PICKAXE));
        tag(MCD_ItemTags.HAS_DURABILITY_BOWS)
                .add(SkyCore.getResourceKey(MCD_Items.TWIN_BOW))
                .add(SkyCore.getResourceKey(MCD_Items.SHORTBOW))
                .add(SkyCore.getResourceKey(MCD_Items.LONGBOW));
        tag(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS)
                .add(SkyCore.getResourceKey(MCD_Items.AUTO_CROSSBOW))
                .add(SkyCore.getResourceKey(MCD_Items.HEAVY_CROSSBOW));
        tag(MCD_ItemTags.HAS_DURABILITY_DURABLE)
                .add(SkyCore.getResourceKey(MCD_Items.ARTIFACT_IRON_HIDE_AMULET))
                .add(SkyCore.getResourceKey(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM));
        tag(MCD_ItemTags.HAS_DURABILITY_SWORDS)
                .add(SkyCore.getResourceKey(MCD_Items.ROUGH_DIAMOND_SWORD))
                .add(SkyCore.getResourceKey(MCD_Items.STEEL_MACE))
                .add(SkyCore.getResourceKey(MCD_Items.SUNS_GRACE))
                .add(SkyCore.getResourceKey(MCD_Items.CUTLASS))
                .add(SkyCore.getResourceKey(MCD_Items.CLAYMORE))
                .add(SkyCore.getResourceKey(MCD_Items.HEARTSTEALER))
                .add(SkyCore.getResourceKey(MCD_Items.BROADSWORD));

        createStoneSetTags(MCD_BlockFamilies.MIDNIGHT_MOSSY_COBBLESTONE);
        createWoodSetTags(
                MCD_ItemTags.PALM_LOGS,
                MCD_Blocks.PALM_WOOD,
                MCD_Blocks.STRIPPED_PALM_WOOD,
                MCD_Blocks.PALM_SAPLING,
                MCD_BlockFamilies.PALM
        );

        tag(ItemTags.FOX_FOOD).add(SkyCore.getResourceKey(MCD_Items.SOUR_BERRIES));
    }
}
