package net.stonksman01.dungeons_reborn.registries;

import net.minecraft.sound.SoundEvent;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.SkyCore;

public class MCD_Sounds {
    public static SoundEvent WANDERLUST = SkyCore.BuiltinRegistries.registerSoundEvent("wanderlust");
    public static SoundEvent SKOGSSTUGA = SkyCore.BuiltinRegistries.registerSoundEvent("skogsstuga");
    public static SoundEvent INTERTILE = SkyCore.BuiltinRegistries.registerSoundEvent("intertile");
    public static SoundEvent HALLAND = SkyCore.BuiltinRegistries.registerSoundEvent("halland");
    public static SoundEvent FINNBACKA = SkyCore.BuiltinRegistries.registerSoundEvent("finnbacka");
    public static SoundEvent DALARNA = SkyCore.BuiltinRegistries.registerSoundEvent("dalarna");
    public static SoundEvent CRYPT = SkyCore.BuiltinRegistries.registerSoundEvent("crypt");
    public static SoundEvent CREEPER_WOODS = SkyCore.BuiltinRegistries.registerSoundEvent("creeper_woods");
    public static SoundEvent CARAVAN = SkyCore.BuiltinRegistries.registerSoundEvent("caravan");
    public static SoundEvent BROKEN_HEARTH_OF_ENDER = SkyCore.BuiltinRegistries.registerSoundEvent("broken_hearth_of_ender");
    public static SoundEvent OBSIDIAN_CAVERN = SkyCore.BuiltinRegistries.registerSoundEvent("obsidian_cavern");
    public static SoundEvent TO_THE_BEGINNING = SkyCore.BuiltinRegistries.registerSoundEvent("to_the_beginning");
    public static SoundEvent IRON_HIDE_AMULET_USE = SkyCore.BuiltinRegistries.registerSoundEvent("artifact_iron_hide_amulet_used");
    public static SoundEvent DEATH_CAP_MUSHROOM_USE = SkyCore.BuiltinRegistries.registerSoundEvent("death_cap_mushroom_use");
    public static SoundEvent TWIN_BOW_SHOOT = SkyCore.BuiltinRegistries.registerSoundEvent("twin_bow_shoot");
    public static SoundEvent ANCIENT_GOLD_PICK_UP = SkyCore.BuiltinRegistries.registerSoundEvent("ancient_gold_pick_up");
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Sounds");
    }
}
