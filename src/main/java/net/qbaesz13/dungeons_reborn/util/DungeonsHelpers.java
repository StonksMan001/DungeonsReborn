package net.qbaesz13.dungeons_reborn.util;

import net.minecraft.block.ComposterBlock;
import net.minecraft.component.ComponentType;
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
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.registries.MCD_Enchantments;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

public interface DungeonsHelpers {
    static void executeForPlayersWithinDistance(World world, BlockPos blockPos, int distance, Function<PlayerEntity, Void> function) {
        Box box = new Box(blockPos).expand(distance);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
        if (!list.isEmpty()) {
            for (PlayerEntity playerEntity : list) {
                if (blockPos.isWithinDistance(playerEntity.getPos(), distance)) function.apply(playerEntity);
            }
        }
    }

    interface Tooltip {
        static void appendDungeonsHeader(List<Text> tooltip) {
            tooltip.add(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        }
        static void appendMcdRarity(List<Text> tooltip, ItemStack stack) {
            appendMcdRarity(tooltip, stack.get(MCD_DataComponentTypes.MCD_RARITY));
        }
        static void appendMcdRarity(List<Text> tooltip, McdRarity mcdRarity) {
            MutableText text = Text.translatable("tooltip.dungeons_reborn.rarity").append(Text.literal(": "));
            if (mcdRarity != null) {
                switch (mcdRarity) {
                    case McdRarity.COMMON -> tooltip.add(text.append(Text.translatable("mcdRarity.dungeons_reborn.common")));
                    case McdRarity.RARE -> tooltip.add(text.append(Text.translatable("mcdRarity.dungeons_reborn.rare")));
                    case McdRarity.UNIQUE -> tooltip.add(text.append(Text.translatable("mcdRarity.dungeons_reborn.unique")));
                    default -> tooltip.add(text.append(Text.translatable("mcdRarity.extended.dungeons_reborn.custom")));
                }
            } else {
                tooltip.add(text.append(Text.translatable("mcdRarity.dungeons_reborn.unknown")));
            }
        }
        static void appendBuiltInEnchantment(List<Text> tooltip, MutableText enchantmentText) {
            appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.built_in").append(Text.literal(" ").append(enchantmentText)), true);
        }
        static void appendAbility(List<Text> tooltip, MutableText abilityText, boolean italic) {
            Style style = Style.EMPTY.withFormatting(Formatting.GREEN).withItalic(italic);
            tooltip.add(abilityText.setStyle(style));
        }
        static void appendDescription(List<Text> tooltip, MutableText descriptionText) {
            if (Objects.isNull(descriptionText.getString())) return;
            for (String string : descriptionText.getString().split("\n")) {
                tooltip.add(Text.literal(string).setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
            }
        }
        static void appendToggle(List<Text> tooltip, MutableText toggleText, ItemStack stack, ComponentType<Boolean> toggleComponent) {
            tooltip.add(toggleText.append(Text.literal(": ")
                            .append(Boolean.TRUE.equals(stack.get(toggleComponent)) ?
                                    Text.translatable("options.on").formatted(Formatting.GREEN):
                                    Text.translatable("options.off").formatted(Formatting.RED))));
        }
    }
    static void enchantStackWithPrimitiveness(ItemStack stack, RegistryWrapper.WrapperLookup provider) {
        addEnchantmentToStack(stack, provider, MCD_Enchantments.PRIMITIVENESS_CURSE, 1);
    }
    static void addEnchantmentToStack(ItemStack itemStack, RegistryWrapper.WrapperLookup wrapper, RegistryKey<Enchantment> enchantment, int level) {
        if (wrapper == null) return;
        enchantInWorld(itemStack, enchantment, level, wrapper.getOptionalWrapper(RegistryKeys.ENCHANTMENT).orElse(null));
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
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment).orElseThrow();
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
    static void setAttackKnockbackModifierIfNotPresent(ItemStack itemStack) {
        var modifiersComponent = itemStack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
        var modifiers = Objects.nonNull(modifiersComponent) ? modifiersComponent.modifiers() : null;
        if (Objects.nonNull(modifiers) && modifiers.stream().noneMatch(entry -> entry.matches(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, entry.modifier().id()))) {
            modifyAttackKnockback(itemStack, EntityAttributes.GENERIC_ATTACK_KNOCKBACK.value().getDefaultValue());
        }
    }
    static void modifyAttackKnockback(ItemStack stack, double knockback) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, knockback, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
    static void modifyAttackDamage(ItemStack stack, double attackDamage) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ));
    }
}