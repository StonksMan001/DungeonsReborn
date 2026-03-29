package net.qbaesz13.dungeons_reborn.components;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum McdRarity implements StringRepresentable {
    COMMON("common"),
    RARE("rare"),
    UNIQUE("unique");

    public static final Codec<McdRarity> CODEC = StringRepresentable.fromValues(McdRarity::values);
    private final String name;
    McdRarity(final String name) {
        this.name = name;
    }
    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }
}
