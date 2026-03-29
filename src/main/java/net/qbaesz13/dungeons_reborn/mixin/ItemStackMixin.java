package net.qbaesz13.dungeons_reborn.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TooltipDisplay;
import net.qbaesz13.dungeons_reborn.mixin_utils.ThreadLocalContainer;
import net.qbaesz13.dungeons_reborn.util.ChainAttackWeapon;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "addAttributeTooltips", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V", shift = At.Shift.BEFORE))
    private void captureItemStack(Consumer<Component> builder, TooltipDisplay tooltipDisplay, @Nullable Player playerEntity, CallbackInfo ci) {
        ItemStack thisStack = (ItemStack)(Object)this;
        if (thisStack.getItem() instanceof ChainAttackWeapon) {
            ThreadLocalContainer.STACK.set(thisStack);
        }
    }
    @Inject(method = "addAttributeTooltips", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V", shift = At.Shift.AFTER))
    private void releaseItemStack(Consumer<Component> builder, TooltipDisplay tooltipDisplay, @Nullable Player playerEntity, CallbackInfo ci) {
        if (Objects.nonNull(ThreadLocalContainer.STACK.get())) ThreadLocalContainer.STACK.remove();
    }
}
