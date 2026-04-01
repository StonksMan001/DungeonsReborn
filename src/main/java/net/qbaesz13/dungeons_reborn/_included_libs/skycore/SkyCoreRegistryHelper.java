package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import org.slf4j.Logger;

public class SkyCoreRegistryHelper {
    public static void register(Class<?> clazz, Logger logger) {
        logger.info("Registering {}", clazz.getSimpleName());
    }
}
