package net.stonksman01.dungeons_reborn.items.mcd_artifact;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdArtifactItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.registries.MCD_Sounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class IronSkinItem extends McdArtifactItem {
    public IronSkinItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE != null) {
            stack.set(MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE, Boolean.FALSE.equals(stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE)));
            return true;
        } else {
            return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
        }
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) { //TODO
        ItemStack stack = user.getStackInHand(hand);
        McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
        int distance = 0;
        int duration = 0;
        int amplifier = 2;
        switch (mcdRarity) {
            case COMMON -> {
                distance = 10;
                duration = 200;
            }
            case RARE -> {
                distance = 15;
                duration = 260;
            }
            case null, default -> {}
        }
        if (world instanceof ServerWorld && duration != 0) {
            if (Boolean.TRUE.equals(stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE))) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, duration, amplifier, false, true, true));
                world.playSoundFromEntity(null, user, MCD_Sounds.IRON_HIDE_AMULET_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
                stack.damage(1, user, hand.getEquipmentSlot());
            } else if (Boolean.FALSE.equals(stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE))) {
                world.playSoundFromEntity(null, user, MCD_Sounds.IRON_HIDE_AMULET_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
                stack.damage(1, user, hand.getEquipmentSlot());
                int j = distance;
                int k = user.getBlockPos().getX();
                int l = user.getBlockPos().getY();
                int m = user.getBlockPos().getZ();
                Box box = (new Box(k, l, m, k + 1, l + 1, m + 1)).expand(j).stretch(0.0, world.getHeight(), 0.0);
                List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
                if (!list.isEmpty()) {
                    for (PlayerEntity playerEntity : list) {
                        if (user.getBlockPos().isWithinDistance(playerEntity.getBlockPos(), j)) {
                            if (playerEntity.getScoreboardTeam() != user.getScoreboardTeam() ||
                                    (playerEntity.getScoreboardTeam() == null && user.getScoreboardTeam() == null)
                            ) {
                            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, duration, amplifier, false, true, true));
                            }
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet.tooltip3").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        DungeonsHelpers.appendMcdRarity(stack, textConsumer);
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet.personal")
                .append(Text.literal(": ")
                        .append(Boolean.TRUE.equals(stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE)) ?
                                Text.translatable("options.on").formatted(Formatting.GREEN):
                                Text.translatable("options.off").formatted(Formatting.RED))));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.artifact.iron_hide_amulet.tooltip4").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withItalic(true)));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (stack.get(MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE) == null) {
            stack.set(MCD_DataComponentTypes.IRON_HIDE_AMULET_PERSONAL_TOGGLE, false);
        }
        super.inventoryTick(stack, world, entity, slot);
        DungeonsHelpers.setRareOrCommonVariant(stack);
    }
}
