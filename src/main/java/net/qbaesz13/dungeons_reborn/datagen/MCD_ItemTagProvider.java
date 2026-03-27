package net.qbaesz13.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_ItemTags;
import net.qbaesz13.dungeons_reborn.registries.MCD_Items;

import java.util.concurrent.CompletableFuture;

public class MCD_ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public MCD_ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.BOW_ENCHANTABLE).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_BOWS);
        valueLookupBuilder(ItemTags.CROSSBOW_ENCHANTABLE).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS);
        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_DURABLE)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_BOWS)
                .addOptionalTag(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS);
        valueLookupBuilder(ItemTags.CHEST_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_CHEST);
        valueLookupBuilder(ItemTags.FOOT_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_FOOT);
        valueLookupBuilder(ItemTags.HEAD_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_HEAD);
        valueLookupBuilder(ItemTags.LEG_ARMOR).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_ARMOR_LEG);
        valueLookupBuilder(ItemTags.AXES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_AXES);
        valueLookupBuilder(ItemTags.HOES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_HOES);
        valueLookupBuilder(ItemTags.PICKAXES).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_PICKAXES);
        valueLookupBuilder(ItemTags.SHOVELS).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_SHOVELS);
        valueLookupBuilder(ItemTags.SWORDS).addOptionalTag(MCD_ItemTags.HAS_DURABILITY_SWORDS);

        valueLookupBuilder(MCD_ItemTags.HAS_DURABILITY_PICKAXES)
                .add(MCD_Items.ROUGH_DIAMOND_PICKAXE);
        valueLookupBuilder(MCD_ItemTags.HAS_DURABILITY_BOWS)
                .add(MCD_Items.TWIN_BOW);
        valueLookupBuilder(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS)
                .add(MCD_Items.AUTO_CROSSBOW)
                .add(MCD_Items.HEAVY_CROSSBOW);
        valueLookupBuilder(MCD_ItemTags.HAS_DURABILITY_DURABLE)
                .add(MCD_Items.ARTIFACT_IRON_HIDE_AMULET)
                .add(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
        valueLookupBuilder(MCD_ItemTags.HAS_DURABILITY_SWORDS)
                .add(MCD_Items.ROUGH_DIAMOND_SWORD)
                .add(MCD_Items.STEEL_MACE)
                .add(MCD_Items.SUNS_GRACE)
                .add(MCD_Items.CLAYMORE)
                .add(MCD_Items.HEARTSTEALER)
                .add(MCD_Items.BROADSWORD);

        valueLookupBuilder(ItemTags.FOX_FOOD).add(MCD_Items.SOUR_BERRIES);
    }
}
