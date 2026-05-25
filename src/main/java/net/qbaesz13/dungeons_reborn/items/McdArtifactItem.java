package net.qbaesz13.dungeons_reborn.items;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

public class McdArtifactItem extends McdItem {
    public McdArtifactItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(10));
    }
    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return false;
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.enchantStackWithPrimitiveness(stack, world.getRegistryManager());
    }
}
