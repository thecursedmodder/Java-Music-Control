package net.cursedmodder.javatriggers.util.debug;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class ModLogger {
    private static final String LOG_FILE = "logs/java_triggers.log";  // relative to Minecraft directory
    //Separate logger from Minecraft's log file.
    public static void setupCustomLogger(String modid) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();

        // Create layout (same style as Minecraft logs)
        Layout<String> layout = PatternLayout.newBuilder()
                .withPattern("[%d{HH:mm:ss}] [%t/%level] [%logger]: %msg%n")
                .build();

        // Create RollingFileAppender (rotates when file gets large)
        Appender appender = RollingFileAppender.newBuilder()
                .setName(modid + "-File")
                .withFileName(LOG_FILE)
                .withFilePattern(LOG_FILE + ".%i.gz")  // for rollover
                .setLayout(layout)
                .withPolicy(SizeBasedTriggeringPolicy.createPolicy("10MB"))  // rotate at 10MB
                .withStrategy(DefaultRolloverStrategy.newBuilder().withMax("5").build())  // keep 5 old files
                .build();

        appender.start();
        config.addAppender(appender);

        // Create or get LoggerConfig for your mod
        LoggerConfig loggerConfig = config.getLoggerConfig(modid);
        if (loggerConfig == null || loggerConfig == config.getRootLogger()) {
            loggerConfig = new LoggerConfig(modid, Level.DEBUG, false);  // false = non-additive (don't send to root)
        }

        loggerConfig.addAppender(appender, Level.DEBUG, null);  // log DEBUG+ to this file
        config.addLogger(modid, loggerConfig);

        ctx.updateLoggers();

        LogManager.getLogger(modid).info("Custom logger initialized - writing to {}", LOG_FILE);
    }
}