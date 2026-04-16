package net.cursedmodder.javatriggers.util.debug;

import net.cursedmodder.javatriggers.JavaTriggers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AudioLogger {
    private static final Logger myLogger = LogManager.getLogger(JavaTriggers.MODID);

    public static void info(String info) {
        myLogger.info(info);
    }

    public static void debug(String info) {
        myLogger.debug(info);
    }

    public static void error(String info) {
        myLogger.error(info);
    }

    public static void warn(String playerIsNull) {
        myLogger.warn(playerIsNull);
    }
}
