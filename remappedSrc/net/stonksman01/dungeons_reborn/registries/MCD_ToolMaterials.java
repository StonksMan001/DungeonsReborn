package net.qbaesz13.dungeons_reborn.registries;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;

public class MCD_ToolMaterials {
    public static final ToolMaterial STEEL_MACE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1000, 9.0F, 4.0F, 15, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial BROADSWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1000, 9.0F, 4.0F, 15, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial CLAYMORE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1000, 9.0F, 4.0F, 15, ItemTags.IRON_TOOL_MATERIALS);

    public static void register() {
        DungeonsReborn.LOGGER.info("[DungeonsReborn] Registering ToolMaterials");
    }
}
