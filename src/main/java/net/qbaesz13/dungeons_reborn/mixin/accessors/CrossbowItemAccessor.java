package net.qbaesz13.dungeons_reborn.mixin.accessors;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CrossbowItem.class)
public interface CrossbowItemAccessor {
    @Accessor
    public boolean getStartSoundPlayed();
    @Accessor
    public boolean getMidLoadSoundPlayed();
    @Accessor("startSoundPlayed")
    public void setStartSoundPlayed(boolean startSoundPlayed);
    @Accessor("midLoadSoundPlayed")
    public void setMidLoadSoundPlayed(boolean midLoadSoundPlayed);
    @Invoker("getShootingPower")
    public static float getShootingPower(ChargedProjectiles projectiles) {
        throw new AssertionError();
    }
}
