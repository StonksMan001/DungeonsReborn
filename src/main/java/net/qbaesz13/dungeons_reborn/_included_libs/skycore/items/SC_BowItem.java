package net.qbaesz13.dungeons_reborn._included_libs.skycore.items;

import net.minecraft.item.BowItem;

public class SC_BowItem extends BowItem {
    public float pullTime = 20.0F;
    public SC_BowItem(Settings settings) {
        super(settings);
    }
    /**
     * Calculates pull progress of custom bows based on {@link BowItem#getPullProgress}
     */
    public float getCustomPullProgress(int useTicks) {
        float f = useTicks / this.pullTime;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
}
