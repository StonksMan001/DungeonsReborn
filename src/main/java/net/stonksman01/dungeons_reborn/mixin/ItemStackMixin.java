package net.stonksman01.dungeons_reborn.mixin;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.stonksman01.dungeons_reborn.mixin_utils.ThreadLocalContainer;
import net.stonksman01.dungeons_reborn.util.ChainAttackWeapon;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "appendAttributeModifiersTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;applyAttributeModifier(Lnet/minecraft/component/type/AttributeModifierSlot;Lorg/apache/commons/lang3/function/TriConsumer;)V", shift = At.Shift.BEFORE))
    private void captureItemStack(Consumer<Text> textConsumer, TooltipDisplayComponent displayComponent, @Nullable PlayerEntity player, CallbackInfo ci) {
        ItemStack thisStack = (ItemStack)(Object)this;
        if (thisStack.getItem() instanceof ChainAttackWeapon) {
            ThreadLocalContainer.STACK.set(thisStack);
        }
    }
    @Inject(method = "appendAttributeModifiersTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;applyAttributeModifier(Lnet/minecraft/component/type/AttributeModifierSlot;Lorg/apache/commons/lang3/function/TriConsumer;)V", shift = At.Shift.AFTER))
    private void releaseItemStack(Consumer<Text> textConsumer, TooltipDisplayComponent displayComponent, @Nullable PlayerEntity player, CallbackInfo ci) {
        if (Objects.nonNull(ThreadLocalContainer.STACK.get())) ThreadLocalContainer.STACK.remove();
    }
}
