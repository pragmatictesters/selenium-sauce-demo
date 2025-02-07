package com.pragmatic.sauce.util;

import org.apache.logging.log4j.Logger;

public class LogManager {

    private static Logger getLogger() {
        // Get the actual calling class (skip the Log class itself)
        String callingClass = new Throwable().getStackTrace()[2].getClassName();
        return org.apache.logging.log4j.LogManager.getLogger(callingClass);
    }

    public static void trace(String message) {
        getLogger().trace(message);
    }

    public static void debug(String message) {
        getLogger().debug(message);
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void info(String message, String parameter) {
        getLogger().info(message, parameter);
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }

    public static void warn(String message, String parameter) {
        getLogger().warn(message, parameter);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void fatal(String message) {
        getLogger().fatal(message);
    }
}
