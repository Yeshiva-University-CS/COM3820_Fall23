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

    public static int computeSumSerial(int count) {
        IntGenerator generator = new IntGenerator(count);
        int sum = 0;
        while (generator.hasNext()) {
            sum += generator.next();
        }
        return sum;
    }

    public static void main(String[] args) {
        int count = (args.length > 0 && args[0] != null) ? Integer.parseInt(args[0]) : 1000000000;

        long start = System.nanoTime();
        logger.info("Begin serial sum of {} computations", count);
        logger.info(Application.computeSumSerial(count));
        logger.info("Completed in " + String.format("%.3f", (System.nanoTime() - start)/1000000.0) + " ms");
    }

    private static class IntGenerator implements Iterator<Integer> {
        private int current = 1;
        private int limit;

        public IntGenerator(int limit) {
            this.limit = limit;
        }

        @Override
        public boolean hasNext() {
            return current <= limit;
        }

        @Override
        public Integer next() {
            return current++;
        }
    }
}
