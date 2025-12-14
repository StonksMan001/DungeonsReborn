package net.stonksman01.dungeons_reborn.items.mcd_artifact;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdArtifactItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.registries.MCD_Sounds;

import java.util.List;

public class DeathCapMushroomItem extends McdArtifactItem {
    public DeathCapMushroomItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        McdRarity mcdRarity = stack.get(MCD_DataComponentTypes.MCD_RARITY);
        int duration = 0;
        int amplifier = 0;
        switch (mcdRarity) {
            case COMMON -> {
                duration = 200;
            }
            case RARE -> {
                duration = 300;
                amplifier = 1;
            }
            case null, default -> {}
        }
        if (!world.isClient && duration != 0) {
            user.getItemCooldownManager().set(this, 600);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, duration, amplifier, false, true, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, duration, amplifier, false, true, true));
            world.playSoundFromEntity(null, user, MCD_Sounds.DEATH_CAP_MUSHROOM_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            stack.damage(1, user, LivingEntity.getSlotForHand(hand));
        }
        return TypedActionResult.success(stack, true);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.artifact.death_cap_mushroom"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, stack);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.artifact.death_cap_mushroom"), true);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        DungeonsHelpers.setRareOrCommonVariant(stack);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
