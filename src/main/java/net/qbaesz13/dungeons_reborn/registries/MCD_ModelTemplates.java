package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

public class MCD_ModelTemplates {
    public static final ModelTemplate HANDHELD_CLAYMORE = item("handheld_claymore", TextureSlot.LAYER0);

    private static ModelTemplate item(String parent, TextureSlot... requiredTextureSlots) {
        return new ModelTemplate(Optional.of(DungeonsReborn.identifierFromNamespaceDungeonsReborn("item/presets/" + parent)), Optional.empty(), requiredTextureSlots);
    }
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureSlots) {
        return new ModelTemplate(Optional.of(DungeonsReborn.identifierFromNamespaceDungeonsReborn("block/presets/" + parent)), Optional.empty(), requiredTextureSlots);
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
