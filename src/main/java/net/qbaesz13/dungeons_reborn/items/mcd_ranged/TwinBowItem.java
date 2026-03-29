package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.registries.MCD_Sounds;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class TwinBowItem extends SC_BowItem {
    public TwinBowItem(Properties properties) {
        super(properties);
    }

    @Override @NullMarked
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem) {
        if (clickAction == ClickAction.SECONDARY && MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE != null) {
            self.set(MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE, Boolean.FALSE.equals(self.get(MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE)));
            return true;
        } else {
            return super.overrideOtherStackedOnMe(self, other, slot, clickAction, player, carriedItem);
        }
    }
    @Override @NullMarked
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (itemStack.get(MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE) == null) {
            itemStack.set(MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE, false);
        }
        super.inventoryTick(itemStack, level, owner, slot);
    }
    @Override @NullMarked
    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity user, int remainingTime) {
        if (!(user instanceof Player playerEntity)) {
            return false;
        } else {
            ItemStack projectile = playerEntity.getProjectile(itemStack);
            if (projectile.isEmpty()) {
                return false;
            } else {
                int timeHeld = this.getUseDuration(itemStack, user) - remainingTime; // i
                float pow = getPowerForTime(timeHeld); // f
                if (pow < 0.1) {
                    return false;
                } else {
                    List<ItemStack> firedProjectiles = draw(itemStack, projectile, playerEntity);
                    if (level instanceof ServerLevel serverLevel && !firedProjectiles.isEmpty()) {
                        ItemStack itemStack1 = playerEntity.getProjectile(itemStack);
                        boolean bowHasInfinity = EnchantmentHelper.getItemEnchantmentLevel(DungeonsHelpers.getEnchantmentRegistryEntry(level, Enchantments.INFINITY), itemStack) > 0;
                        AABB searchBox = new AABB(playerEntity.blockPosition()).inflate(20);
                        boolean targetPlayers = Boolean.TRUE.equals(itemStack.get(MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE));
                        List<LivingEntity> targetEntities = serverLevel.getEntitiesOfClass(
                                LivingEntity.class,
                                searchBox,
                                entity -> DungeonsHelpers.isEntityEnemy(entity, playerEntity, targetPlayers)
                        );
                        boolean bonusShot = false;
                        if (!targetEntities.isEmpty() && !playerEntity.isShiftKeyDown()) {
                            bonusShot = true;
                            LivingEntity targetEntity = targetEntities.getFirst();
                            // bonus projectile
                            AbstractArrow abstractArrow0 = (AbstractArrow) super.createProjectile(serverLevel, playerEntity, itemStack, itemStack1, pow == 1.0F);
                            this.shootProjectile(playerEntity, abstractArrow0, 0, pow * 3.0F, 1.0F, playerEntity.getYRot(), null);
                            abstractArrow0.pickup = AbstractArrow.Pickup.DISALLOWED;
                            abstractArrow0.shoot(targetEntity.getX() - playerEntity.getX(), targetEntity.getEyeY() - playerEntity.getEyeY(), targetEntity.getZ() - playerEntity.getZ(), 1.5F, 0);
                            level.addFreshEntity(abstractArrow0);
                        }
                        // main projectile
                        AbstractArrow abstractArrow1 = (AbstractArrow) super.createProjectile(serverLevel, playerEntity, itemStack, itemStack1, pow == 1.0F);
                        this.shootProjectile(playerEntity, abstractArrow1, 0, pow * 3.0F, 1.0F, 0.0f, null);
                        if (bonusShot || (bowHasInfinity && abstractArrow1 instanceof Arrow)) {
                            abstractArrow1.pickup = AbstractArrow.Pickup.DISALLOWED;
                        } else if (playerEntity.isCreative()) {
                            abstractArrow1.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        } else {
                            abstractArrow1.pickup = AbstractArrow.Pickup.ALLOWED;
                        }
                        level.addFreshEntity(abstractArrow1);
                        itemStack.hurtAndBreak(this.getDurabilityUse(itemStack1), playerEntity, playerEntity.getUsedItemHand().asEquipmentSlot());
                    }
                    level.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            MCD_Sounds.TWIN_BOW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pow * 0.5F
                    );
                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                    return true;
                }
            }
        }
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.twin_bow"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendToggle(builder, Component.translatable("toggle.dungeons_reborn.target_players"),
                itemStack, MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.bonus_shot"), false);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }
}