package net.qbaesz13.dungeons_reborn.items.mcd_ranged;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
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
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.qbaesz13.dungeons_reborn.items.McdItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import net.qbaesz13.dungeons_reborn.registries.MCD_Sounds;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class TwinBowItem extends SC_BowItem {
    public TwinBowItem(net.minecraft.item.Item.Settings settings) {
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
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (stack.get(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE) == null) {
            stack.set(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE, false);
        }
        super.inventoryTick(stack, world, entity, slot);
    }
    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world instanceof ServerWorld && user instanceof PlayerEntity playerEntity) {
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (!itemStack.isEmpty()) {
                int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!((double)f < 0.1)) {
                    List<ItemStack> list = load(stack, itemStack, playerEntity);
                    if (world instanceof ServerWorld) {
                        ServerWorld serverWorld = ((ServerWorld) world).toServerWorld();
                        if (!list.isEmpty()) {
                            boolean bowHasInfinity = false;
                            if (EnchantmentHelper.getLevel(DungeonsHelpers.getEnchantmentRegistryEntry(world, Enchantments.INFINITY), stack) > 0) {
                                bowHasInfinity = true;
                            }
                            boolean critical = f == 1.0F;
                            float speed = f * 3.0F;
                            float divergence = 1.0F;
                            int index = 0; //redundant
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
                                LivingEntity closestHostile = targetEntities.getFirst();
                                // bonus projectile
                                PersistentProjectileEntity persistentProjectileEntity1 = (PersistentProjectileEntity) super.createArrowEntity(serverWorld, playerEntity, stack, itemStack, critical);
                                this.shoot(playerEntity, persistentProjectileEntity1, index, speed, divergence, playerEntity.getYaw(), null);
                                persistentProjectileEntity1.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
                                persistentProjectileEntity1.setVelocity(closestHostile.getX() - playerEntity.getX(), closestHostile.getEyeY() - playerEntity.getEyeY(), closestHostile.getZ() - playerEntity.getZ(), 1.5F, 0);
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
                            stack.damage(this.getWeaponStackDamage(itemStack), playerEntity, playerEntity.getActiveHand().getEquipmentSlot());
                        }
                    }
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), MCD_Sounds.TWIN_BOW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.minecraft_dungeons_header").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.twin_bow.tooltip1").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.twin_bow.tooltip2").setStyle(Style.EMPTY.withItalic(true).withFormatting(Formatting.GRAY)));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.rarity.unique"));
        textConsumer.accept(Text.translatable("tooltip.dungeons_reborn.bonus_shot.target_players")
                .append(Text.literal(": ")
                        .append(Boolean.TRUE.equals(stack.get(MCD_DataComponentTypes.TWIN_BOW_TARGET_PLAYER_ENTITIES_TOGGLE)) ?
                                Text.translatable("options.on").formatted(Formatting.GREEN):
                                Text.translatable("options.off").formatted(Formatting.RED))));
        textConsumer.accept(Text.translatable("enchantment.dungeons_reborn.bonus_shot").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN)));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return McdItem.getMcdItemBarColor();
    }

}

