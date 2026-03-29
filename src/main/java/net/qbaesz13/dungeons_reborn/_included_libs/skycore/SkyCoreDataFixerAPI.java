package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import java.util.HashMap;

public class SkyCoreDataFixerAPI {
    public static final HashMap<String, String[]> BLOCKS_WITH_ALIAS = new HashMap<>();
    public static final HashMap<String, String[]> ITEM_WITH_ALIAS = new HashMap<>();
    public static void registerBlockNameFix(String parent, String... alias) {
        BLOCKS_WITH_ALIAS.put(parent, alias);
    }
    public static void registerItemNameFix(String parent, String... alias) {
        ITEM_WITH_ALIAS.put(parent, alias);
    }
    public static void registerBlockAndItemNameFix(String parent, String... alias) {
        registerBlockNameFix(parent, alias);
        registerItemNameFix(parent, alias);
    }
}