package net.qbaesz13.dungeons_reborn.registries;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn.components.McdRarity;

public class MCD_DataComponentTypes {
    public static final ComponentType<Boolean> TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE = SkyCore.BuiltinRegistries.registerComponentType("twin_bow_attack_player_entities_toggle", booleanBuilder -> {
        return booleanBuilder.codec(Codec.BOOL);
    });
    public static final ComponentType<Boolean> TEAMMATE_ONLY_TOGGLE = SkyCore.BuiltinRegistries.registerComponentType("teammate_only_toggle", booleanBuilder -> {
        return booleanBuilder.codec(Codec.BOOL);
    });
    public static final ComponentType<McdRarity> MCD_RARITY = SkyCore.BuiltinRegistries.registerComponentType("mcd_rarity", (mcdRarityBuilder) -> {
        return mcdRarityBuilder.codec(McdRarity.CODEC);
    });
    public static final ComponentType<Long> ACCELERATE_LAST_SHOT_TIME = SkyCore.BuiltinRegistries.registerComponentType("accelerate_last_shot_time", longBuilder -> {
        return longBuilder.codec(Codec.LONG);
    });
    public static final ComponentType<Float> ACCELERATE_RELOAD_BONUS = SkyCore.BuiltinRegistries.registerComponentType("accelerate_reload_bonus", floatBuilder -> {
        return floatBuilder.codec(Codec.FLOAT);
    });
    public static final ComponentType<Integer> ATTACK_CHAIN_STEP = SkyCore.BuiltinRegistries.registerComponentType("attack_chain_step", integerBuilder -> {
        return integerBuilder.codec(Codec.INT);
    });
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering DataComponentTypes");
    }
}
