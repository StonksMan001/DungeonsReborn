package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class SkyCoreToolAPI {
    public static class ShovelItem extends Item {
        public ShovelItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
            super(settings.shovel(toolMaterial, baseAttackDamage, attackSpeed - 4f));
        }
    }
    public static class SwordItem extends Item {
        public SwordItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
            super(settings.sword(toolMaterial, baseAttackDamage, attackSpeed - 4f));
        }
    }
    public static class AxeItem extends Item {
        public AxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
            super(settings.axe(toolMaterial, baseAttackDamage, attackSpeed - 4f));
        }
    }
    public static class PickaxeItem extends Item {
        public PickaxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
            super(settings.pickaxe(toolMaterial, baseAttackDamage, attackSpeed - 4f));
        }
    }
    public static class HoeItem extends Item {
        public HoeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
            super(settings.hoe(toolMaterial, baseAttackDamage, attackSpeed - 4f));
        }
    }
}
