package edu.yu.parallel;

import java.util.Iterator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);
    
    static {
        Configurator.setLevel("edu.yu.parallel", Level.INFO);
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
        logger.info("Begin serial sum of {} computations", count);
        logger.info(Application.computeSumSerial(count));
        logger.info("Completed in " + String.format("%.3f", (System.nanoTime() - start)/1000000.0) + " ms");
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
