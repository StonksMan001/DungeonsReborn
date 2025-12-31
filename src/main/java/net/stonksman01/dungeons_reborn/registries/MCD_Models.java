package net.stonksman01.dungeons_reborn.registries;

import net.minecraft.client.data.Model;
import net.minecraft.client.data.TextureKey;
import net.stonksman01.dungeons_reborn.DungeonsReborn;

import java.util.Optional;

public class MCD_Models {
    public static final Model HANDHELD_CLAYMORE = item("handheld_claymore", TextureKey.LAYER0);

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("item/presets/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("block/presets/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Models");
    }
}