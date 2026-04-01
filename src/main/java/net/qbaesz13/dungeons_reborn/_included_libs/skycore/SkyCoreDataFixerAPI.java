package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
public class SkyCoreDataFixerAPI {
    public static final HashMap<String, String[]> ITEM_WITH_ALIAS = new HashMap<>();
    public static final HashMap<String, String[]> BLOCKS_WITH_ALIAS = new HashMap<>();
    public static final HashMap<String, String[]> DATA_COMPONENT_TYPES_WITH_ALIAS = new HashMap<>();
    public static final HashMap<String, String[]> BLOCK_ENTITY_TYPES_WITH_ALIAS = new HashMap<>();
    public static void registerItemNameFix(String parent, String... alias) {
        ITEM_WITH_ALIAS.put(parent, ArrayUtils.addAll(ITEM_WITH_ALIAS.get(parent), alias));
    }
    public static void registerBlockNameFix(String parent, String... alias) {
        BLOCKS_WITH_ALIAS.put(parent, ArrayUtils.addAll(BLOCKS_WITH_ALIAS.get(parent), alias));
    }
    public static void registerDataComponentTypeNameFix(String parent, String... alias) {
        DATA_COMPONENT_TYPES_WITH_ALIAS.put(parent, ArrayUtils.addAll(DATA_COMPONENT_TYPES_WITH_ALIAS.get(parent), alias));
    }
    public static void registerBlockEntityTypeNameFix(String parent, String... alias) {
        BLOCK_ENTITY_TYPES_WITH_ALIAS.put(parent, ArrayUtils.addAll(BLOCK_ENTITY_TYPES_WITH_ALIAS.get(parent), alias));
    }
    public static void registerBlockAndItemNameFix(String parent, String... alias) {
        registerBlockNameFix(parent, alias);
        registerItemNameFix(parent, alias);
    }
}
