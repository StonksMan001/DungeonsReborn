package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.SkyCore;

public class MCD_Enchantments {
    public static RegistryKey<Enchantment> PRIMITIVENESS_CURSE = SkyCore.BuiltinRegistries.ofEnchantmentRegistry("primitiveness_curse");
    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering Enchantments");
    }
}
