package net.qbaesz13.dungeons_reborn.registries;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCoreRegistryHelper;

import java.lang.invoke.MethodHandles;

public class MCD_TerraformBoatTypes {
    public static final RegistryKey<TerraformBoatType> PALM_BOAT = TerraformBoatTypeRegistry.createKey(DungeonsReborn.identifierOfDungeonsReborn("palm_boat"));

    private static void init() {
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, PALM_BOAT, new TerraformBoatType.Builder()
                .item(MCD_Items.PALM_BOAT)
                .chestItem(MCD_Items.PALM_CHEST_BOAT)
                .planks(MCD_Blocks.PALM_PLANKS.asItem())
                .build());
    }
    public static void register() {
        init();
        SkyCoreRegistryHelper.register(MethodHandles.lookup().lookupClass(), DungeonsReborn.LOGGER);
    }
}
