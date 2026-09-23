package net.qbaesz13.dungeons_reborn.registries.client;

import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

public class MCD_Models {
    public static final Model HANDHELD_CLAYMORE = item("handheld_claymore", TextureKey.LAYER0);
    public static final Model TRUNK = block("trunk", TextureKey.SIDE, TextureKey.END);
    public static final Model TRUNK_HORIZONTAL = block("trunk_horizontal", "_horizontal", TextureKey.SIDE, TextureKey.END);

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("item/presets/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("block/presets/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("block/presets/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}