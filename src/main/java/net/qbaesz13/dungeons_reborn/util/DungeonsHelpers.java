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
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.registries.MCD_Enchantments;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public interface DungeonsHelpers {
    static void executeForPlayersWithinDistance(World world, BlockPos blockPos, int distance, Function<PlayerEntity, Void> function) {
        Box box = new Box(blockPos).expand(distance);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
        if (!list.isEmpty()) {
            for (PlayerEntity playerEntity : list) {
                if (blockPos.isWithinDistance(playerEntity.getEntityPos(), distance)) function.apply(playerEntity);
            }
        }
    }

    interface Tooltip {
        static void appendDungeonsHeader(Consumer<Text> textConsumer) {
            textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        }
        static void appendMcdRarity(Consumer<Text> textConsumer, ItemStack stack) {
            appendMcdRarity(textConsumer, stack.get(MCD_DataComponentTypes.MCD_RARITY));
        }
        static void appendMcdRarity(Consumer<Text> textConsumer, McdRarity mcdRarity) {
            MutableText text = Text.translatable("tooltip.dungeons_reborn.rarity").append(Text.literal(": "));
            if (mcdRarity != null) {
                switch (mcdRarity) {
                    case McdRarity.COMMON -> textConsumer.accept(text.append(Text.translatable("mcdRarity.dungeons_reborn.common")));
                    case McdRarity.RARE -> textConsumer.accept(text.append(Text.translatable("mcdRarity.dungeons_reborn.rare")));
                    case McdRarity.UNIQUE -> textConsumer.accept(text.append(Text.translatable("mcdRarity.dungeons_reborn.unique")));
                    default -> textConsumer.accept(text.append(Text.translatable("mcdRarity.extended.dungeons_reborn.custom")));
                }
            } else {
                textConsumer.accept(text.append(Text.translatable("mcdRarity.dungeons_reborn.unknown")));
            }
        }
        static void appendBuiltInEnchantment(Consumer<Text> textConsumer, MutableText enchantmentText) {
            appendAbility(textConsumer, Text.translatable("ability.dungeons_reborn.built_in").append(Text.literal(" ").append(enchantmentText)), true);
        }
        static void appendAbility(Consumer<Text> textConsumer, MutableText abilityText, boolean italic) {
            Style style = Style.EMPTY.withFormatting(Formatting.GREEN).withItalic(italic);
            textConsumer.accept(abilityText.setStyle(style));
        }
        static void appendDescription(Consumer<Text> textConsumer, MutableText descriptionText) {
            if (Objects.isNull(descriptionText.getString())) return;
            for (String string : descriptionText.getString().split("\n")) {
                textConsumer.accept(Text.literal(string).setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
            }
        }
        static void appendToggle(Consumer<Text> textConsumer, MutableText toggleText, ItemStack stack, ComponentType<Boolean> toggleComponent) {
            textConsumer.accept(toggleText.append(Text.literal(": ")
                            .append(Boolean.TRUE.equals(stack.get(toggleComponent)) ?
                                    Text.translatable("options.on").formatted(Formatting.GREEN):
                                    Text.translatable("options.off").formatted(Formatting.RED))));
        }
    }
    static void makeUnrepairable(ItemStack itemStack) {
        if (Objects.nonNull(itemStack.get(DataComponentTypes.REPAIRABLE))) itemStack.set(DataComponentTypes.REPAIRABLE, null);
    }
    static void enchantStackWithPrimitiveness(ItemStack itemStack, RegistryWrapper.WrapperLookup wrapperLookup) {
        addEnchantmentToStack(itemStack, wrapperLookup, MCD_Enchantments.PRIMITIVENESS_CURSE, 1);
    }
    static void addEnchantmentToStack(ItemStack itemStack, RegistryWrapper.WrapperLookup wrapperLookup, RegistryKey<Enchantment> enchantment, int level) {
        if (wrapperLookup == null) return;
        enchantInWorld(itemStack, enchantment, level, wrapperLookup.getOptional(RegistryKeys.ENCHANTMENT).orElse(null));
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
    static void setAttackKnockbackModifierIfNotPresent(ItemStack itemStack) {
        var modifiersComponent = itemStack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
        var modifiers = modifiersComponent != null ? modifiersComponent.modifiers() : null;
        if (modifiers != null && modifiers.stream().noneMatch(entry -> entry.matches(EntityAttributes.ATTACK_KNOCKBACK, entry.modifier().id()))) {
            modifyAttackKnockback(itemStack, EntityAttributes.ATTACK_KNOCKBACK.value().getDefaultValue());
        }
    }
    static void modifyAttackKnockback(ItemStack stack, double knockback) {
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .with(
                        EntityAttributes.ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(DungeonsReborn.identifierOfDungeonsReborn("base_attack_knockback"), knockback, EntityAttributeModifier.Operation.ADD_VALUE),
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