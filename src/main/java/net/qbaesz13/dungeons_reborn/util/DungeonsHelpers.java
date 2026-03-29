package net.qbaesz13.dungeons_reborn.util;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Team;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.registries.MCD_Enchantments;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public interface DungeonsHelpers {
    static void executeForPlayersWithinDistance(Level level, BlockPos blockPos, int distance, Function<Player, Void> function) {
        AABB box = new AABB(blockPos).inflate(distance);
        List<Player> list = level.getEntitiesOfClass(Player.class, box);
        if (!list.isEmpty()) {
            for (Player playerEntity : list) {
                if (blockPos.closerToCenterThan(playerEntity.position(), distance)) function.apply(playerEntity);
            }
        }
    }

    interface Tooltip {
        static void appendDungeonsHeader(Consumer<Component> builder) {
            builder.accept(Component.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).applyFormats(ChatFormatting.GRAY)));
        }
        static void appendMcdRarity(Consumer<Component> builder, ItemStack itemStack) {
            appendMcdRarity(builder, itemStack.get(MCD_DataComponents.MCD_RARITY));
        }
        static void appendMcdRarity(Consumer<Component> builder, McdRarity mcdRarity) {
            MutableComponent text = Component.translatable("tooltip.dungeons_reborn.rarity").append(Component.literal(": "));
            if (mcdRarity != null) {
                switch (mcdRarity) {
                    case McdRarity.COMMON -> builder.accept(text.append(Component.translatable("mcdRarity.dungeons_reborn.common")));
                    case McdRarity.RARE -> builder.accept(text.append(Component.translatable("mcdRarity.dungeons_reborn.rare")));
                    case McdRarity.UNIQUE -> builder.accept(text.append(Component.translatable("mcdRarity.dungeons_reborn.unique")));
                    default -> builder.accept(text.append(Component.translatable("mcdRarity.extended.dungeons_reborn.custom")));
                }
            } else {
                builder.accept(text.append(Component.translatable("mcdRarity.dungeons_reborn.unknown")));
            }
        }
        static void appendBuiltInEnchantment(Consumer<Component> builder, MutableComponent enchantmentText) {
            appendAbility(builder, Component.translatable("ability.dungeons_reborn.built_in").append(Component.literal(" ").append(enchantmentText)), true);
        }
        static void appendAbility(Consumer<Component> builder, MutableComponent abilityText, boolean italic) {
            Style style = Style.EMPTY.applyFormats(ChatFormatting.GREEN).withItalic(italic);
            builder.accept(abilityText.setStyle(style));
        }
        static void appendDescription(Consumer<Component> builder, MutableComponent descriptionText) {
            if (Objects.isNull(descriptionText.getString())) return;
            for (String string : descriptionText.getString().split("\n")) {
                builder.accept(Component.literal(string).setStyle(Style.EMPTY.withItalic(true).applyFormats(ChatFormatting.GRAY)));
            }
        }
        static void appendToggle(Consumer<Component> builder, MutableComponent toggleText, ItemStack itemStack, DataComponentType<Boolean> toggleComponent) {
            builder.accept(toggleText.append(Component.literal(": ")
                            .append(Boolean.TRUE.equals(itemStack.get(toggleComponent)) ?
                                    Component.translatable("options.on").withStyle(ChatFormatting.GREEN):
                                    Component.translatable("options.off").withStyle(ChatFormatting.RED))));
        }
    }
    static void makeUnrepairable(ItemStack itemStack) {
        if (Objects.nonNull(itemStack.get(DataComponents.REPAIRABLE))) itemStack.set(DataComponents.REPAIRABLE, null);
    }
    static void enchantStackWithPrimitiveness(ItemStack stack, HolderLookup.Provider provider) {
        addEnchantmentToStack(stack, provider, MCD_Enchantments.PRIMITIVENESS_CURSE, 1);
    }
    static void addEnchantmentToStack(ItemStack itemStack, HolderLookup.Provider provider, ResourceKey<Enchantment> enchantment, int level) {
        if (provider == null) return;
        enchantInWorld(itemStack, enchantment, level, provider.lookup(Registries.ENCHANTMENT).orElse(null));
    }
    private static void enchantInWorld(ItemStack stack, ResourceKey<Enchantment> enchantment, int level, HolderLookup.RegistryLookup<Enchantment> lookup ) {
        if (lookup == null) return;
        lookup.get(enchantment).map(e -> {
            stack.enchant(e, level);
            return true;
        });
    }
    static boolean isEntityEnemy(LivingEntity target, Player playerEntity, boolean targetPlayers) {
        if (!targetPlayers && target instanceof Player) return false;
        boolean isHostile = target instanceof Enemy
                || target instanceof Monster
                || (target instanceof Player otherPlayer
                && !otherPlayer.isCreative()
                && !otherPlayer.isSpectator());
        if (!isHostile) return false;
        return !areAllies(target, playerEntity);
    }
    static boolean areAllies(LivingEntity livingEntity, LivingEntity livingEntity2) {
        PlayerTeam team1 = livingEntity.getTeam();
        PlayerTeam team2 = livingEntity2.getTeam();
        if (livingEntity == livingEntity2) return true;
        return team1 == team2 && team1 != null;
    }

    static Holder<Enchantment> getEnchantmentRegistryEntry(Level world, ResourceKey<Enchantment> enchantment) {
        return world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(enchantment).orElseThrow();
    }
    static void setRareOrCommonVariant(ItemStack itemStack) {
        if (Objects.isNull(itemStack.get(MCD_DataComponents.MCD_RARITY))) {
            Random random = new Random();
            if (random.nextInt(5) == 1) itemStack.set(MCD_DataComponents.MCD_RARITY, McdRarity.RARE);
            else itemStack.set(MCD_DataComponents.MCD_RARITY, McdRarity.COMMON);
        }
    }
    static float getCompostingValue(ItemLike item) {
        return ComposterBlock.COMPOSTABLES.getFloat(item.asItem());
    }
    static void modifyAttackKnockback(ItemStack itemStack, double attackKnockback) {
        itemStack.set(DataComponents.ATTRIBUTE_MODIFIERS, itemStack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY)
                .withModifierAdded(
                        Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackKnockback, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                ));
    }

    static void modifyAttackDamage(ItemStack stack, double attackDamage) {
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY)
                .withModifierAdded(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                ));
    }
}