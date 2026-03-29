package net.qbaesz13.dungeons_reborn.items.mcd_meele;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.qbaesz13.dungeons_reborn.components.McdRarity;
import net.qbaesz13.dungeons_reborn.items.mcd_meele.templates.ClaymoreBaseItem;
import net.qbaesz13.dungeons_reborn.registries.MCD_GameRules;
import net.qbaesz13.dungeons_reborn.util.DungeonsHelpers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;

import java.util.function.Consumer;

public class HeartstealerItem extends ClaymoreBaseItem {
    public HeartstealerItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, baseAttackDamage, attackSpeed, properties);
    }
    @Override @NullMarked
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(builder);
        DungeonsHelpers.Tooltip.appendDescription(builder, Component.translatable("tooltip.dungeons_reborn.heartstealer"));
        DungeonsHelpers.Tooltip.appendMcdRarity(builder, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.powerful_pushback"), true);
        DungeonsHelpers.Tooltip.appendAbility(builder, Component.translatable("ability.dungeons_reborn.leeching"), false);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
    @Override @NullMarked
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        if (attacker.level() instanceof ServerLevel serverLevel && target.isDeadOrDying() && (target instanceof Mob || target instanceof Player)) {
            float healAmount = target.getMaxHealth() * ((float)serverLevel.getGameRules().get(MCD_GameRules.LEECHING_HP_STEAL_PERCENTAGE) / 100);
            if (healAmount > 0) attacker.heal(healAmount);
            if (healAmount < 0) attacker.hurtServer(serverLevel, serverLevel.damageSources().magic(), -healAmount);
            serverLevel.sendParticles(DustParticleOptions.REDSTONE,
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    20, 0.5F, 1.0F, 0.5F, 1);
        }
        super.postHurtEnemy(itemStack, target, attacker);
    }
    @Override @NullUnmarked
    public Tuple<@NonNull Double, Double> getAttackDamagePair(McdRarity mcdRarity) {
        return new Tuple<>(10d, null);
    }
}
