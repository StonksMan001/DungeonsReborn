package net.stonksman01.dungeons_reborn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class MCD_RegistryDataGenerator extends FabricDynamicRegistryProvider {
    public MCD_RegistryDataGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
            entries.addAll(registries.getOrThrow(RegistryKeys.CONFIGURED_FEATURE));
            entries.addAll(registries.getOrThrow(RegistryKeys.PLACED_FEATURE));
    }
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
