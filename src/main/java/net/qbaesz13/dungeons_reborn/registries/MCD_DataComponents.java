package net.qbaesz13.dungeons_reborn.registries;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;
import net.qbaesz13.dungeons_reborn.components.McdRarity;

import java.lang.invoke.MethodHandles;

public class MCD_DataComponents {
    public static final DataComponentType<Boolean> TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE = SkyCore.RegistryPresets.registerComponentType("twin_bow_attack_player_entities_toggle", booleanBuilder -> {
        return booleanBuilder.persistent(Codec.BOOL);
    });
    public static final DataComponentType<Boolean> TEAMMATE_ONLY_TOGGLE = SkyCore.RegistryPresets.registerComponentType("teammate_only_toggle", booleanBuilder -> {
        return booleanBuilder.persistent(Codec.BOOL);
    });
    public static final DataComponentType<McdRarity> MCD_RARITY = SkyCore.RegistryPresets.registerComponentType("mcd_rarity", (mcdRarityBuilder) -> {
        return mcdRarityBuilder.persistent(McdRarity.CODEC);
    });
    public static final DataComponentType<Long> ACCELERATE_LAST_SHOT_TIME = SkyCore.RegistryPresets.registerComponentType("accelerate_last_shot_time", longBuilder -> {
        return longBuilder.persistent(Codec.LONG);
    });
    public static final DataComponentType<Float> ACCELERATE_RELOAD_BONUS = SkyCore.RegistryPresets.registerComponentType("accelerate_reload_bonus", floatBuilder -> {
        return floatBuilder.persistent(Codec.FLOAT);
    });
    public static final DataComponentType<Integer> ATTACK_CHAIN_STEP = SkyCore.RegistryPresets.registerComponentType("attack_chain_step", integerBuilder -> {
        return integerBuilder.persistent(Codec.INT);
    });
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
