package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.client.data.Model;
import net.minecraft.client.data.TextureKey;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;

import java.util.Optional;

public class MCD_Models {
    public static final Model HANDHELD_CLAYMORE = item("handheld_claymore", TextureKey.LAYER0);

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(SkyCore.identifierOfDungeonsReborn("item/presets/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(SkyCore.identifierOfDungeonsReborn("block/presets/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Models");
    }
}
