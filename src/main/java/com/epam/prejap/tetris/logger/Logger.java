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
    private static String className; // may be used by other logging libraries

    private Logger() {
    }

    /**
     * Most of the logging libraries provide a method for getting a logger instance.
     * The parameter to that method used as a name for returned logger.
     * Usually a class name is used.
     *
     * @param name The name of the logger
     * @return instance of this class
     */
    public static Logger getLogger(String name) {
        className = name;
        return new Logger();
    }

    /**
     * A convenient way of giving the name to the logger after the class.
     *
     * @param clazz The name of the logger
     * @return instance of this class
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public void error(final Object message) {
        org.tinylog.Logger.error(message);
    }

    public void error(final String message, final Object... arguments) {
        org.tinylog.Logger.error(message, arguments);
    }

    public void warn(final Object message) {
        org.tinylog.Logger.warn(message);
    }

    public void warn(final String message, final Object... arguments) {
        org.tinylog.Logger.warn(message, arguments);
    }

    public void info(final Object message) {
        org.tinylog.Logger.info(message);
    }

    public void info(final String message, final Object... arguments) {
        org.tinylog.Logger.info(message, arguments);
    }

    public void debug(final Object message) {
        org.tinylog.Logger.debug(message);
    }

    public void debug(final String message, final Object... arguments) {
        org.tinylog.Logger.debug(message, arguments);
    }

    public void trace(final Object message) {
        org.tinylog.Logger.trace(message);
    }

    public void trace(final String message, final Object... arguments) {
        org.tinylog.Logger.trace(message, arguments);
    }
}
