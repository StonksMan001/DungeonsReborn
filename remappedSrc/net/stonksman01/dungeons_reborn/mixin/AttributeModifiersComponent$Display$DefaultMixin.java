package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.ChainAttackWeapon;
import net.qbaesz13.dungeons_reborn.mixin_utils._ThreadLocalContainer;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponentTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.text.DecimalFormat;
import java.util.Objects;

@Mixin(AttributeModifiersComponent.Display.Default.class)
public abstract class AttributeModifiersComponent$Display$DefaultMixin {

    @WrapOperation(method = "addTooltip", at = @At(value = "INVOKE", target = "Ljava/text/DecimalFormat;format(D)Ljava/lang/String;", ordinal = 0))
    public String modifyTooltip(DecimalFormat instance, double e, Operation<String> original, @Local(ordinal = 0, argsOnly = true) RegistryEntry<EntityAttribute> attribute) {
        ItemStack thisStack = _ThreadLocalContainer.STACK.get();
        if (Objects.nonNull(thisStack) && thisStack.getItem() instanceof ChainAttackWeapon chainAttackWeapon) {
            String attributeString = attribute.value().getTranslationKey();
            switch (attributeString) {
                case "attribute.name.attack_damage" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackDamagePair(thisStack.get(MCD_DataComponentTypes.MCD_RARITY)));
                    if (Objects.nonNull(string)) return string;
                }
                case "attribute.name.attack_speed" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackSpeedPair(thisStack.get(MCD_DataComponentTypes.MCD_RARITY)));
                    if (Objects.nonNull(string)) return string;
                }
                case "attribute.name.attack_knockback" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackKnockbackPair(thisStack.get(MCD_DataComponentTypes.MCD_RARITY)));
                    if (Objects.nonNull(string)) return string;
                }
                default -> {}
            }
        }
        return original.call(instance, e);
    }
    @Unique
    private String getChainAttackWeaponParameters(Operation<String> original, DecimalFormat instance, @Nullable Pair<@NotNull Double, @Nullable Double> pair) {
        if (Objects.isNull(pair)) return null;
        String base = original.call(instance, pair.getLeft());
        String critical = Objects.nonNull(pair.getRight()) ? original.call(instance, pair.getRight()) : null;
        String diff = Objects.nonNull(pair.getRight()) ? original.call(instance, pair.getRight() - pair.getLeft()) : null;
        if (Objects.nonNull(diff) && diff.charAt(0) != '-') diff = "+" + diff;
        return Objects.nonNull(critical) ? String.format("[%s/%s/%s] (%s)", base, base, critical, diff) : base;
    }
}