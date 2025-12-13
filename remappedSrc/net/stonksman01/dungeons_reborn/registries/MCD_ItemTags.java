package net.stonksman01.dungeons_reborn.registries;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCore;

public class MCD_ItemTags {
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_CHEST = SkyCore.BuiltinRegistries.createItemTag("has_durability/armor/chest");
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_FOOT = SkyCore.BuiltinRegistries.createItemTag("has_durability/armor/foot");
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_HEAD = SkyCore.BuiltinRegistries.createItemTag("has_durability/armor/head");
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_LEG = SkyCore.BuiltinRegistries.createItemTag("has_durability/armor/leg");

    public static final TagKey<Item> HAS_DURABILITY_BOWS = SkyCore.BuiltinRegistries.createItemTag("has_durability/bows");
    public static final TagKey<Item> HAS_DURABILITY_CROSSBOWS = SkyCore.BuiltinRegistries.createItemTag("has_durability/crossbows");
    public static final TagKey<Item> HAS_DURABILITY_DURABLE = SkyCore.BuiltinRegistries.createItemTag("has_durability/durable");

    public static final TagKey<Item> HAS_DURABILITY_AXES = SkyCore.BuiltinRegistries.createItemTag("has_durability/axes");
    public static final TagKey<Item> HAS_DURABILITY_HOES = SkyCore.BuiltinRegistries.createItemTag("has_durability/hoes");
    public static final TagKey<Item> HAS_DURABILITY_PICKAXES = SkyCore.BuiltinRegistries.createItemTag("has_durability/pickaxes");
    public static final TagKey<Item> HAS_DURABILITY_SHOVELS = SkyCore.BuiltinRegistries.createItemTag("has_durability/shovels");
    public static final TagKey<Item> HAS_DURABILITY_SWORDS = SkyCore.BuiltinRegistries.createItemTag("has_durability/swords");
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering ItemTags");
    }
}
