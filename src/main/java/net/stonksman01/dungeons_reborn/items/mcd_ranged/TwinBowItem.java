package net.stonksman01.dungeons_reborn.items.mcd_ranged;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.stonksman01.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.McdItem;
import net.stonksman01.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.stonksman01.dungeons_reborn.registries.MCD_Sounds;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;

import java.util.List;

public class TwinBowItem extends SC_BowItem {
    public TwinBowItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE != null) {
            stack.set(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE, Boolean.FALSE.equals(stack.get(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE)));
            return true;
        } else {
            return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
        }
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE) == null) {
            stack.set(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE, false);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient && user instanceof PlayerEntity playerEntity) {
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (!itemStack.isEmpty()) {
                int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!(f < 0.1)) {
                    List<ItemStack> list = load(stack, itemStack, playerEntity);
                    if (world instanceof ServerWorld serverWorld && !list.isEmpty()) {
                        boolean bowHasInfinity = EnchantmentHelper.getLevel(DungeonsHelpers.getEnchantmentRegistryEntry(world, Enchantments.INFINITY), stack) > 0;
                        boolean critical = f == 1.0F;
                        float speed = f * 3.0F;
                        float divergence = 1.0F;
                        int index = 0; // redundant
                        Box searchBox = new Box(playerEntity.getBlockPos()).expand(20);
                        boolean targetPlayers = Boolean.TRUE.equals(stack.get(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE));
                        List<LivingEntity> targetEntities = serverWorld.getEntitiesByClass(
                                LivingEntity.class,
                                searchBox,
                                entity -> DungeonsHelpers.isEntityEnemy(entity, playerEntity, targetPlayers)
                        );
                        boolean bonus_shot = false;
                        if (!targetEntities.isEmpty() && !playerEntity.isSneaking()) {
                            bonus_shot = true;
                            LivingEntity targetEntity = targetEntities.getFirst();
                            // bonus projectile
                            PersistentProjectileEntity persistentProjectileEntity1 = (PersistentProjectileEntity) super.createArrowEntity(serverWorld, playerEntity, stack, itemStack, critical);
                            this.shoot(playerEntity, persistentProjectileEntity1, index, speed, divergence, playerEntity.getYaw(), null);
                            persistentProjectileEntity1.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
                            persistentProjectileEntity1.setVelocity(targetEntity.getX() - playerEntity.getX(), targetEntity.getEyeY() - playerEntity.getEyeY(), targetEntity.getZ() - playerEntity.getZ(), 1.5F, 0);
                            world.spawnEntity(persistentProjectileEntity1);
                        }
                        // main projectile
                        PersistentProjectileEntity persistentProjectileEntity0 = (PersistentProjectileEntity) super.createArrowEntity(serverWorld, playerEntity, stack, itemStack, critical);
                        this.shoot(playerEntity, persistentProjectileEntity0, index, speed, divergence, 0.0f, null);
                        if (bonus_shot || (bowHasInfinity && persistentProjectileEntity0 instanceof ArrowEntity)) {
                            persistentProjectileEntity0.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
                        } else if (playerEntity.isCreative()) {
                            persistentProjectileEntity0.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        } else {
                            persistentProjectileEntity0.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                        }
                        world.spawnEntity(persistentProjectileEntity0);
                        stack.damage(this.getWeaponStackDamage(itemStack), playerEntity, LivingEntity.getSlotForHand(playerEntity.getActiveHand()));
                    }
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), MCD_Sounds.TWIN_BOW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.twin_bow"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendToggle(tooltip, Text.translatable("toggle.dungeons_reborn.target_players"),
                stack, MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.bonus_shot"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}

