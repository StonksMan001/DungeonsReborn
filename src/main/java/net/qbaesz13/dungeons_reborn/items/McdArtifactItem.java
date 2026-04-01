package net.qbaesz13.dungeons_reborn.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;

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
        DungeonsHelpers.enchantStackWithPrimitiveness(stack, world.getRegistryManager());
    }
}
