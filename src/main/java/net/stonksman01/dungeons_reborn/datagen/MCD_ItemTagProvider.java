package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.stonksman01.dungeons_reborn.registries.MCD_ItemTags;
import net.stonksman01.dungeons_reborn.registries.MCD_Items;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MCD_ItemTagProvider extends FabricTagProvider.ItemTagProvider {
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

        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_BOWS)
                .add(MCD_Items.TWIN_BOW);
        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_CROSSBOWS)
                .add(MCD_Items.AUTO_CROSSBOW);
        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_DURABLE)
                .add(MCD_Items.ARTIFACT_IRON_HIDE_AMULET)
                .add(MCD_Items.ARTIFACT_DEATH_CAP_MUSHROOM);
        getOrCreateTagBuilder(MCD_ItemTags.HAS_DURABILITY_SWORDS)
                .add(MCD_Items.ROUGH_DIAMOND_SWORD)
                .add(MCD_Items.STEEL_MACE);
    }
}
