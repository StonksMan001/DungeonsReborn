package net.stonksman01.dungeons_reborn.util;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;

import java.util.List;

public interface DungeonsHelpers {
    static boolean wrapRangedWeaponHardcodedCallsIfPresent(ItemStack instance, Item item, Operation<Boolean> original) {
        if (item instanceof BowItem) {
            return original.call(instance, item) || instance.getItem() instanceof BowItem;
        }
        if (item instanceof CrossbowItem) {
            return original.call(instance, item) || instance.getItem() instanceof CrossbowItem;
        }
        return original.call(instance, item);
    }
    static void appendMcdRarity(ItemStack stack, List<Text> tooltip) {
        McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
        if (mcdRarity != null) {
            switch (mcdRarity) {
                case McdRarity.COMMON -> tooltip.add(Text.translatable("tooltip.dungeons_reborn.rarity.common"));
                case McdRarity.RARE -> tooltip.add(Text.translatable("tooltip.dungeons_reborn.rarity.rare"));
                case McdRarity.UNIQUE -> tooltip.add(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
                default -> tooltip.add(Text.translatable("tooltip.dungeons_reborn.rarity.common")
                        .append(Text.literal(" "))
                        .append(Text.translatable("tooltip.dungeons_reborn.rarity_info_extended.custom")));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.dungeons_reborn.rarity.common")
                    .append(Text.literal(" "))
                    .append(Text.translatable("tooltip.dungeons_reborn.rarity_info_extended.custom")));
        }
    }
    static boolean addEnchantmentToStack(ItemStack itemStack, RegistryWrapper.WrapperLookup wrapper, RegistryKey<Enchantment> enchantment, int level) {
        if (wrapper == null) return false;
        return enchantInWorld(itemStack, enchantment, level, wrapper.getOptionalWrapper(RegistryKeys.ENCHANTMENT).orElse(null));
    }
    private static boolean enchantInWorld( ItemStack stack, RegistryKey<Enchantment> enchantment, int level, RegistryWrapper.Impl<Enchantment> lookup ) {
        if (lookup == null) return false;
        return lookup.getOptional(enchantment).map(e -> {
                    stack.addEnchantment(e, level);
                    return true;
        }).orElse(false);
    }
    static RegistryEntry<Enchantment> getEnchantmentRegistryEntry(World world, RegistryKey<Enchantment> enchantment) {
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment).orElseThrow();
    }
}
