package net.stonksman01.dungeons_reborn.items.mcd_meele.templates;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn._included_libs.skycore.SkyCoreToolAPI;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.util.AttackCooldownDependent;
import net.stonksman01.dungeons_reborn.util.ChainAttackWeapon;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class ClaymoreBaseItem extends SkyCoreToolAPI.SwordItem implements ChainAttackWeapon, AttackCooldownDependent {
    protected float baseAttackDamage;
    public ClaymoreBaseItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
        this.baseAttackDamage = baseAttackDamage;
    }
    @Override
    public void postChargedAttack(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        int next_chain_step;
        if (current_chain_step == 1) attacker.getWorld().playSoundFromEntity(null, attacker, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (current_chain_step == 3) DungeonsHelpers.modifyAttackKnockback(stack, 3.0);
        else DungeonsHelpers.modifyAttackKnockback(stack, 0.0);
        if (!(current_chain_step >= 3)) {
            next_chain_step = current_chain_step + 1;
        } else next_chain_step = 1;
        stack.set(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, next_chain_step);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (Objects.isNull(stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS))) DungeonsHelpers.modifyAttackKnockback(stack, 0.0);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        int current_chain_step = stack.getOrDefault(MCD_DataComponentTypes.ATTACK_CHAIN_STEP, 1);
        if (current_chain_step == 1) return McdItem.getMcdChargedItemBarColor();
        else return McdItem.getMcdItemBarColor();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }
    @Override
    public @Nullable Pair<Double, Double> getAttackKnockbackPair(@Nullable McdRarity mcdRarity) {
        return new Pair<>(4d, 7d);
    }
}
