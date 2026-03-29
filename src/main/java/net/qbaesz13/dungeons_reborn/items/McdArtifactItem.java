package net.qbaesz13.dungeons_reborn.items;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class McdArtifactItem extends McdItem {
    public McdArtifactItem(Properties properties) {
        super(properties.stacksTo(1).durability(10));
    }
    @Override
    public boolean canBeEnchantedWith(@NonNull ItemStack stack, @NonNull Holder<Enchantment> enchantment, @NonNull EnchantingContext context) {
        return false;
    }
    @Override
    public boolean isFoil(@NonNull ItemStack itemStack) {
        return false;
    }
    @Override
    public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
        DungeonsHelpers.enchantStackWithPrimitiveness(itemStack, level.registryAccess());
    }
}