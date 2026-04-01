package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HeartstealerItem extends ClaymoreBaseItem {
    public HeartstealerItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.heartstealer"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.powerful_pushback"), true);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.leeching"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getWorld() instanceof ServerWorld serverWorld && target.isDead() && (target instanceof MobEntity || target instanceof PlayerEntity)) {
            float healAmount = target.getMaxHealth() * ((float)MCD_GameRules.LEECHING_HP_STEAL_PERCENTAGE.getValue(serverWorld) / 100);
            if (healAmount > 0) attacker.heal(healAmount);
            if (healAmount < 0) attacker.damage(serverWorld.getDamageSources().magic(), -healAmount);
            serverWorld.spawnParticles(DustParticleEffect.DEFAULT,
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    20, 0.5F, 1.0F, 0.5F, 1);
        }
        super.postDamageEntity(stack, target, attacker);
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackDamagePair(@Nullable McdRarity mcdRarity) {
        return new Pair<>(10d, null);
    }
}
