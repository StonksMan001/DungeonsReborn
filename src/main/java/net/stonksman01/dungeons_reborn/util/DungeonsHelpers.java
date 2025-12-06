package net.stonksman01.dungeons_reborn.util;

import net.minecraft.block.ComposterBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.registries.MCD_Enchantments;

import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

public interface DungeonsHelpers {
    interface Tooltip {
        static void appendDungeonsHeader(Consumer<Text> textConsumer) {
            textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        }
        static void appendMcdRarity(ItemStack stack, Consumer<Text> textConsumer) {
            McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
            if (mcdRarity != null) {
                switch (mcdRarity) {
                    case McdRarity.COMMON -> textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.common"));
                    case McdRarity.RARE -> textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.rare"));
                    case McdRarity.UNIQUE -> textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
                    default -> textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.common")
                            .append(Text.literal(" "))
                            .append(Text.translatable("tooltip.dungeons_reborn.rarity_info_extended.custom")));
                }
            } else {
                textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.unknown"));
            }
        }
    }
    static void makeUnrepairable(ItemStack stack) {
        if (Objects.nonNull(stack.get(DataComponentTypes.REPAIRABLE))) stack.set(DataComponentTypes.REPAIRABLE, null);
    }
    static void enchantStackWithPrimitiveness(ItemStack stack, RegistryWrapper.WrapperLookup provider) {
        addEnchantmentToStack(stack, provider, MCD_Enchantments.PRIMITIVENESS_CURSE, 1);
    }
    static void addEnchantmentToStack(ItemStack itemStack, RegistryWrapper.WrapperLookup wrapper, RegistryKey<Enchantment> enchantment, int level) {
        if (wrapper == null) return;
        enchantInWorld(itemStack, enchantment, level, wrapper.getOptional(RegistryKeys.ENCHANTMENT).orElse(null));
    }
    private static void enchantInWorld(ItemStack stack, RegistryKey<Enchantment> enchantment, int level, RegistryWrapper.Impl<Enchantment> lookup ) {
        if (lookup == null) return;
        lookup.getOptional(enchantment).map(e -> {
            stack.addEnchantment(e, level);
            return true;
        });
    }
    static boolean isEntityEnemy(LivingEntity target, PlayerEntity playerEntity, boolean targetPlayers) {
        if (!targetPlayers && target instanceof PlayerEntity) return false;
        boolean isHostile = target instanceof Monster
                || target instanceof HostileEntity
                || (target instanceof PlayerEntity otherPlayer
                && !otherPlayer.isCreative()
                && !otherPlayer.isSpectator());
        if (!isHostile) return false;
        return !areAllies(target, playerEntity);
    }
    static boolean areAllies(LivingEntity livingEntity, LivingEntity livingEntity2) {
        Team team1 = livingEntity.getScoreboardTeam();
        Team team2 = livingEntity2.getScoreboardTeam();
        if (livingEntity == livingEntity2) return true;
        return team1 == team2 && team1 != null;
    }

    static RegistryEntry<Enchantment> getEnchantmentRegistryEntry(World world, RegistryKey<Enchantment> enchantment) {
        return world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(enchantment).orElseThrow();
    }
    static void setRareOrCommonVariant(ItemStack stack) {
        if (Objects.isNull(stack.get(MCD_DataComponentTypes.MCD_RARITY))) {
            Random random = new Random();
            if (random.nextInt(5) == 1) stack.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.RARE);
            else stack.set(MCD_DataComponentTypes.MCD_RARITY, McdRarity.COMMON);
        }
    }
    static float getCompostingValue(ItemConvertible item) {
        return ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.getFloat(item.asItem());
    }
    static void modifyAttackKnockback(ItemStack stack, double knockback) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, knockback, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }

    static void modifyAttackDamage(ItemStack stack, double attackDamage) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
}