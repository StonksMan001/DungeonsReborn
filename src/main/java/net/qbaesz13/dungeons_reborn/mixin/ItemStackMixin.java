package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.component.ComponentHolder;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn.util.ChainAttackWeapon;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.text.DecimalFormat;
import java.util.Objects;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ComponentHolder {
    @Shadow public abstract Item getItem();

    @WrapOperation(method = "appendAttributeModifierTooltip", at = @At(value = "INVOKE", target = "Ljava/text/DecimalFormat;format(D)Ljava/lang/String;", ordinal = 0))
    public String modifyTooltip(DecimalFormat instance, double e, Operation<String> original, @Local(ordinal = 0, argsOnly = true) RegistryEntry<EntityAttribute> attribute) {
        if (this.getItem() instanceof ChainAttackWeapon chainAttackWeapon) {
            String attributeString = attribute.value().getTranslationKey();
            switch (attributeString) {
                case "attribute.name.generic.attack_damage" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackDamagePair(this.get(MCD_DataComponentTypes.MCD_RARITY)));
                    if (string != null) return string;
                }
                case "attribute.name.generic.attack_speed" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackSpeedPair(this.get(MCD_DataComponentTypes.MCD_RARITY)));
                    if (string != null) return string;
                }
                case "attribute.name.generic.attack_knockback" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackKnockbackPair(this.get(MCD_DataComponentTypes.MCD_RARITY)));
                    if (string != null) return string;
                }
                default -> {}
            }
        }
        return original.call(instance, e);
    }
    @Unique
    private String getChainAttackWeaponParameters(Operation<String> original, DecimalFormat instance, @Nullable Pair<@NotNull Double, @Nullable Double> pair) {
        if (pair == null) return null;
        String base = original.call(instance, pair.getLeft());
        String critical = pair.getRight() != null ? original.call(instance, pair.getRight()) : null;
        String diff = pair.getRight() != null ? original.call(instance, pair.getRight() - pair.getLeft()) : null;
        if (diff != null && diff.charAt(0) != '-') diff = "+" + diff;
        return critical != null ? String.format("[%s/%s/%s] (%s)", base, base, critical, diff) : base;
    }
}