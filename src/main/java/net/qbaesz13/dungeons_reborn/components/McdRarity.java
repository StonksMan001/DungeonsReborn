package net.qbaesz13.dungeons_reborn.components;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum McdRarity implements StringIdentifiable {
    COMMON("common"),
    RARE("rare"),
    UNIQUE("unique");

    public static final Codec<McdRarity> CODEC = StringIdentifiable.createBasicCodec(McdRarity::values);
    private final String name;
    McdRarity(final String name) {
        this.name = name;
    }
    @Override
    public String asString() {
        return this.name;
    }
}
