package net.stonksman01.dungeons_reborn.items;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

public class McdArtifactItem extends McdItem {
    public McdArtifactItem(net.minecraft.item.Item.Settings settings) {
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
