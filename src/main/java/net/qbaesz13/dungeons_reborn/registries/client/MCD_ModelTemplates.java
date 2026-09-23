package net.qbaesz13.dungeons_reborn.registries.client;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

public class MCD_ModelTemplates {
    public static final ModelTemplate HANDHELD_CLAYMORE = createItem("handheld_claymore", TextureSlot.LAYER0);
    public static final ModelTemplate TRUNK = create("trunk", TextureSlot.SIDE, TextureSlot.END);
    public static final ModelTemplate TRUNK_HORIZONTAL = create("trunk_horizontal", "_horizontal", TextureSlot.SIDE, TextureSlot.END);

    private static ModelTemplate createItem(final String id, final TextureSlot... slots) {
        return new ModelTemplate(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("item/presets/" + id)), Optional.empty(), slots);
    }
    private static ModelTemplate create(final String id, final TextureSlot... slots) {
        return new ModelTemplate(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("block/presets/" + id)), Optional.empty(), slots);
    }
    private static ModelTemplate create(final String id, final String suffix, final TextureSlot... slots) {
        return new ModelTemplate(Optional.of(DungeonsReborn.identifierOfDungeonsReborn("block/presets/" + id)), Optional.of(suffix), slots);
    }
    public static void register() {
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
