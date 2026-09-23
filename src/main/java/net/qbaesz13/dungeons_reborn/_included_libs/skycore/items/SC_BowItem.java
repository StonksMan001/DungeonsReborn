package net.qbaesz13.dungeons_reborn._included_libs.skycore.items;

import net.minecraft.world.item.BowItem;

public class SC_BowItem extends BowItem {
    public float pullTime = 20.0F;
    public SC_BowItem(Properties properties) {
        super(properties);
    }
    /**
     * Calculates pull progress of custom bows based on {@link BowItem#getPowerForTime}
     */
    public float getCustomPowerForTime(final int timeHeld) {
        float pow = timeHeld / this.pullTime;
        pow = (pow * pow + pow * 2.0F) / 3.0F;
        if (pow > 1.0F) {
            pow = 1.0F;
        }

        return pow;
    }
}
