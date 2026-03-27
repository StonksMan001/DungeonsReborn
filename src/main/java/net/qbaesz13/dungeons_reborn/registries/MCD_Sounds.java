package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.sound.SoundEvent;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_Sounds {
    public static SoundEvent IRON_HIDE_AMULET_USE = SkyCore.BuiltinRegistries.registerSoundEvent("artifact_iron_hide_amulet_used");
    public static SoundEvent DEATH_CAP_MUSHROOM_USE = SkyCore.BuiltinRegistries.registerSoundEvent("death_cap_mushroom_use");
    public static SoundEvent TWIN_BOW_SHOOT = SkyCore.BuiltinRegistries.registerSoundEvent("twin_bow_shoot");
    public static SoundEvent ANCIENT_GOLD_PICK_UP = SkyCore.BuiltinRegistries.registerSoundEvent("ancient_gold_pick_up");
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
