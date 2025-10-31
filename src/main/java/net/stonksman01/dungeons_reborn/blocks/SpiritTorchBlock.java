package net.stonksman01.dungeons_reborn.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractTorchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SpiritTorchBlock extends AbstractTorchBlock {
    public static final MapCodec<SpiritTorchBlock> CODEC = createCodec(SpiritTorchBlock::new);
    public MapCodec<? extends SpiritTorchBlock> getCodec() {
        return CODEC;
    }
    public SpiritTorchBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double)pos.getX() + (double)0.5F + (random.nextDouble() - (double)0.5F) * 0.2;
        double e = (double)pos.getY() + 0.7 + (random.nextDouble() - (double)0.5F) * 0.2;
        double f = (double)pos.getZ() + (double)0.5F + (random.nextDouble() - (double)0.5F) * 0.2;
        world.addParticleClient(new DustParticleEffect(65343, 1.0f), d, e, f, 0.0F, 0.0F, 0.0F);
    }
}
