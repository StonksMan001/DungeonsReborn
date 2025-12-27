package net.stonksman01.dungeons_reborn.items.mcd_meele;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.stonksman01.dungeons_reborn.components.McdRarity;
import net.stonksman01.dungeons_reborn.items.mcd_meele.templates.MaceBaseItem;
import net.stonksman01.dungeons_reborn.util.DungeonsHelpers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class SunsGraceItem extends MaceBaseItem {
    public SunsGraceItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, baseAttackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        DungeonsHelpers.Tooltip.appendDungeonsHeader(tooltip);
        DungeonsHelpers.Tooltip.appendDescription(tooltip, Text.translatable("tooltip.dungeons_reborn.suns_grace"));
        DungeonsHelpers.Tooltip.appendMcdRarity(tooltip, McdRarity.UNIQUE);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.powerful_combo"), true);
        DungeonsHelpers.Tooltip.appendAbility(tooltip, Text.translatable("ability.dungeons_reborn.radiance"), false);
        super.appendTooltip(stack, context, tooltip, type);
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        if ((target instanceof MobEntity || target instanceof PlayerEntity) && attacker.getWorld() instanceof ServerWorld serverWorld && random.nextInt(5) == 0) {
            BlockPos center = target.getBlockPos();
            DungeonsHelpers.executeForPlayersWithinDistance(serverWorld, center, 5, (playerEntity) -> {
                playerEntity.heal(3);
                serverWorld.spawnParticles(DustParticleEffect.DEFAULT,
                        playerEntity.getX(),
                        playerEntity.getY(),
                        playerEntity.getZ(),
                        20, 0.5F, 1.0F, 0.5F, 1);
                return null;
            });
            serverWorld.spawnParticles(new DustParticleEffect(Vec3d.unpackRgb(16757504).toVector3f(), 2.5f),
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    100, 2.5F, 1.0F, 2.5F, 1);

        }
        super.postDamageEntity(stack, target, attacker);
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackDamagePair(@Nullable McdRarity mcdRarity) {
        return new Pair<>(8d, 12d);
    }
    @Override
    public @Nullable Pair<@NotNull Double, @Nullable Double> getAttackSpeedPair(@Nullable McdRarity mcdRarity) {
        return new Pair<>(1.6d, 0.6d);
    }
}
