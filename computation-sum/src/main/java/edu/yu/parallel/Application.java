package edu.yu.parallel;

import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format(record.getMessage(), record.getParameters()) + "\n";
            }
        });

        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
    }

    public static long computeSumSerial(long count) {
        LongGenerator generator = new LongGenerator(count);
        long sum = 0;
        while (generator.hasNext()) {
            sum += generator.next();
        }
        return sum;
    }

    public static void main(String[] args) {
        long count = (args.length > 0 && args[0] != null) ? Long.parseLong(args[0]) : 1000000000;

        long start = System.nanoTime();
        logger.log(Level.INFO, "Begin serial sum of %d computations", count);
        
        long sum = Application.computeSumSerial(count);
        long end = System.nanoTime();

        logger.log(Level.INFO, "%d", sum);
        logger.log(Level.INFO, "Completed in %.3f ms", (end - start) / 1000000.0);
    }

    private static class LongGenerator implements Iterator<Long> {
        private long current = 1;
        private long limit;

        public LongGenerator(long limit) {
            this.limit = limit;
        }

        @Override
        public boolean hasNext() {
            return current <= limit;
        }

        @Override
        public Long next() {
            return current++;
        }
    }
}
