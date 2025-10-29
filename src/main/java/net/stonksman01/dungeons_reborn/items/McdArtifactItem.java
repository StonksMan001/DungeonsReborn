package net.stonksman01.dungeons_reborn.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class McdArtifactItem extends McdItem {
    public McdArtifactItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(10));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //enchantStackWithPrimitiveness(stack, world.getRegistryManager()); //TODO
    }
}
