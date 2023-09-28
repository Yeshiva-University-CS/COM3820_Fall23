package edu.yu.parallel;

import java.util.function.Supplier;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CustomLogger {
    private final Logger logger;

    public static CustomLogger getLogger(Class<?> clazz) {
        return new CustomLogger(Logger.getLogger(clazz.getName()));
    }

    private CustomLogger(Logger logger) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format(record.getMessage(), record.getParameters()) + "\n";
            }
        });

        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        this.logger = logger;
    }

    public void log(String message, Object... args) {
        this.logger.log(Level.INFO, message, args);
    }

    public void logDuration(String message, Runnable runnable) {
        log("BEGIN " + message);
        long start = System.nanoTime();
        runnable.run();
        long end = System.nanoTime();
        log("END " + message + " [in %.3f ms]", (end - start) / 1000000.0);
    }

    public <T> T logDuration(String message, Supplier<T> task) {
        log("BEGIN " + message);
        long start = System.nanoTime();
        T result = task.get();
        long end = System.nanoTime();
        log("END " + message + " [in %.3f ms]", (end - start) / 1000000.0);
        return result;
    }
}
