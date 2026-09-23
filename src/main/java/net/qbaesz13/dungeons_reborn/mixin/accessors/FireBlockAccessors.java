package net.qbaesz13.dungeons_reborn.mixin.accessors;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FireBlock.class)
public interface FireBlockAccessors {
    @Accessor
    public Object2IntMap<Block> getBurnChances();
    @Accessor
    public Object2IntMap<Block> getSpreadChances();
}
