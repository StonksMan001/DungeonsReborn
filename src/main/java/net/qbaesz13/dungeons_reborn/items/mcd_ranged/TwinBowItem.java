package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
        if (level instanceof ServerLevel serverLevel && user instanceof Player playerEntity) {
            ItemStack itemStack1 = playerEntity.getProjectile(itemStack);
            if (!itemStack1.isEmpty()) {
                int i = this.getUseDuration(itemStack, user) - remainingTime;
                float f = getPowerForTime(i);
                if (!(f < 0.1)) {
                    List<ItemStack> list = draw(itemStack, itemStack1, playerEntity);
                    if (!list.isEmpty()) {
                        boolean bowHasInfinity = EnchantmentHelper.getItemEnchantmentLevel(DungeonsHelpers.getEnchantmentRegistryEntry(level, Enchantments.INFINITY), itemStack) > 0;
                        boolean critical = f == 1.0F;
                        float speed = f * 3.0F;
                        float divergence = 1.0F;
                        int index = 0; // redundant
                        AABB searchBox = new AABB(playerEntity.blockPosition()).inflate(20);
                        boolean targetPlayers = Boolean.TRUE.equals(itemStack.get(MCD_DataComponents.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE));
                        List<LivingEntity> targetEntities = serverLevel.getEntitiesOfClass(
                                LivingEntity.class,
                                searchBox,
                                entity -> DungeonsHelpers.isEntityEnemy(entity, playerEntity, targetPlayers)
                        );
                        boolean bonus_shot = false;
                        if (!targetEntities.isEmpty() && !playerEntity.isShiftKeyDown()) {
                            bonus_shot = true;
                            LivingEntity targetEntity = targetEntities.getFirst();
                            // bonus projectile
                            AbstractArrow abstractArrow0 = (AbstractArrow) super.createProjectile(serverLevel, playerEntity, itemStack, itemStack1, critical);
                            this.shootProjectile(playerEntity, abstractArrow0, index, speed, divergence, playerEntity.getYRot(), null);
                            abstractArrow0.pickup = AbstractArrow.Pickup.DISALLOWED;
                            abstractArrow0.shoot(targetEntity.getX() - playerEntity.getX(), targetEntity.getEyeY() - playerEntity.getEyeY(), targetEntity.getZ() - playerEntity.getZ(), 1.5F, 0);
                            level.addFreshEntity(abstractArrow0);
                        }
                        // main projectile
                        AbstractArrow abstractArrow1 = (AbstractArrow) super.createProjectile(serverLevel, playerEntity, itemStack, itemStack1, critical);
                        this.shootProjectile(playerEntity, abstractArrow1, index, speed, divergence, 0.0f, null);
                        if (bonus_shot || (bowHasInfinity && abstractArrow1 instanceof Arrow)) {
                            abstractArrow1.pickup = AbstractArrow.Pickup.DISALLOWED;
                        } else if (playerEntity.isCreative()) {
                            abstractArrow1.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        } else {
                            abstractArrow1.pickup = AbstractArrow.Pickup.ALLOWED;
                        }
                        level.addFreshEntity(abstractArrow1);
                        itemStack.hurtAndBreak(this.getDurabilityUse(itemStack1), playerEntity, playerEntity.getUsedItemHand().asEquipmentSlot());
                    }
                    level.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), MCD_Sounds.TWIN_BOW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                    return true;
                }
            }
        }
        return false;
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