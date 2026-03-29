package net.qbaesz13.dungeons_reborn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.qbaesz13.dungeons_reborn.mixin_utils.ThreadLocalContainer;
import net.qbaesz13.dungeons_reborn.registries.MCD_DataComponents;
import net.qbaesz13.dungeons_reborn.util.ChainAttackWeapon;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.text.DecimalFormat;
import java.util.Objects;

@Mixin(ItemAttributeModifiers.Display.Default.class)
public abstract class ItemAttributeModifiers$Display$DefaultMixin {

    @WrapOperation(method = "apply", at = @At(value = "INVOKE", target = "Ljava/text/DecimalFormat;format(D)Ljava/lang/String;", ordinal = 0))
    public String modifyTooltip(DecimalFormat instance, double e, Operation<String> original, @Local(ordinal = 0, argsOnly = true) Holder<Attribute> attribute) {
        ItemStack thisStack = ThreadLocalContainer.STACK.get();
        if (Objects.nonNull(thisStack) && thisStack.getItem() instanceof ChainAttackWeapon chainAttackWeapon) {
            String attributeString = attribute.value().getDescriptionId();
            switch (attributeString) {
                case "attribute.name.attack_damage" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackDamagePair(thisStack.get(MCD_DataComponents.MCD_RARITY)));
                    if (Objects.nonNull(string)) return string;
                }
                case "attribute.name.attack_speed" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackSpeedPair(thisStack.get(MCD_DataComponents.MCD_RARITY)));
                    if (Objects.nonNull(string)) return string;
                }
                case "attribute.name.attack_knockback" -> {
                    String string = getChainAttackWeaponParameters(original, instance, chainAttackWeapon.getAttackKnockbackPair(thisStack.get(MCD_DataComponents.MCD_RARITY)));
                    if (Objects.nonNull(string)) return string;
                }
                default -> {}
            }
        }
        return original.call(instance, e);
    }
    @Unique
    private String getChainAttackWeaponParameters(Operation<String> original, DecimalFormat instance, @Nullable Tuple<@NonNull Double, @Nullable Double> pair) {
        if (Objects.isNull(pair)) return null;
        String base = original.call(instance, pair.getA());
        String critical = Objects.nonNull(pair.getB()) ? original.call(instance, pair.getB()) : null;
        String diff = Objects.nonNull(pair.getB()) ? original.call(instance, pair.getB() - pair.getA()) : null;
        if (Objects.nonNull(diff) && diff.charAt(0) != '-') diff = "+" + diff;
        return Objects.nonNull(critical) ? String.format("[%s/%s/%s] (%s)", base, base, critical, diff) : base;
    }
}