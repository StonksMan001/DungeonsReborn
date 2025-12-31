package net.stonksman01.dungeons_reborn.mixin;

import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CrossbowItem.class)
public interface CrossbowItemAccessor {
    @Accessor
    public boolean getCharged();
    @Accessor
    public boolean getLoaded();
    @Accessor("charged")
    public void setCharged(boolean charged);
    @Accessor("loaded")
    public void setLoaded(boolean loaded);
    @Invoker("getSpeed")
    public static float getSpeed(ChargedProjectilesComponent stack) {
        throw new AssertionError();
    }
}
