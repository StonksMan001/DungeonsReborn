package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_ItemTags {
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_CHEST = SkyCore.RegistryPresets.createItemTag("has_durability/armor/chest");
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_FOOT = SkyCore.RegistryPresets.createItemTag("has_durability/armor/foot");
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_HEAD = SkyCore.RegistryPresets.createItemTag("has_durability/armor/head");
    public static final TagKey<Item> HAS_DURABILITY_ARMOR_LEG = SkyCore.RegistryPresets.createItemTag("has_durability/armor/leg");

    public static final TagKey<Item> HAS_DURABILITY_BOWS = SkyCore.RegistryPresets.createItemTag("has_durability/bows");
    public static final TagKey<Item> HAS_DURABILITY_CROSSBOWS = SkyCore.RegistryPresets.createItemTag("has_durability/crossbows");
    public static final TagKey<Item> HAS_DURABILITY_DURABLE = SkyCore.RegistryPresets.createItemTag("has_durability/durable");

    public static final TagKey<Item> HAS_DURABILITY_AXES = SkyCore.RegistryPresets.createItemTag("has_durability/axes");
    public static final TagKey<Item> HAS_DURABILITY_HOES = SkyCore.RegistryPresets.createItemTag("has_durability/hoes");
    public static final TagKey<Item> HAS_DURABILITY_PICKAXES = SkyCore.RegistryPresets.createItemTag("has_durability/pickaxes");
    public static final TagKey<Item> HAS_DURABILITY_SHOVELS = SkyCore.RegistryPresets.createItemTag("has_durability/shovels");
    public static final TagKey<Item> HAS_DURABILITY_SWORDS = SkyCore.RegistryPresets.createItemTag("has_durability/swords");
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
