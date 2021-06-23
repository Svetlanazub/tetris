package com.epam.prejap.tetris.logger;

/**
 * A facade for actual logger implementation.
 * In case the logging library is changed, this class should be changed as well.
 * <p>
 * Available levels of logging:
 * <li>ERROR</li>
 * <li>WARN</li>
 * <li>INFO</li>
 * <li>DEBUG</li>
 * <li>TRACE</li>
 *
 * @author Andrii Velychko
 */
public class Logger {
    public static void error(final Object message) {
        org.tinylog.Logger.error(message);
    }

    public static void error(final String message, final Object... arguments) {
        org.tinylog.Logger.error(message, arguments);
    }

    public static void warn(final Object message) {
        org.tinylog.Logger.warn(message);
    }

    public static void warn(final String message, final Object... arguments) {
        org.tinylog.Logger.warn(message, arguments);
    }

    public static void info(final Object message) {
        org.tinylog.Logger.info(message);
    }

    public static void info(final String message, final Object... arguments) {
        org.tinylog.Logger.info(message, arguments);
    }

    public static void debug(final Object message) {
        org.tinylog.Logger.debug(message);
    }

    public static void debug(final String message, final Object... arguments) {
        org.tinylog.Logger.debug(message, arguments);
    }

    public static void trace(final Object message) {
        org.tinylog.Logger.trace(message);
    }

    public static void trace(final String message, final Object... arguments) {
        org.tinylog.Logger.trace(message, arguments);
    }
}
