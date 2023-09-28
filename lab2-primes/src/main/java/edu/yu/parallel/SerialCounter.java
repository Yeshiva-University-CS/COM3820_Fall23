package edu.yu.parallel;

import edu.yu.parallel.utils.CustomLogger;

public class SerialCounter {
    private static CustomLogger logger = CustomLogger.getLogger(SerialCounter.class);


    public static void main(String[] args) {
        if (args.length != 1) {
            logger.log("Usage: java edu.yu.parallel.SerialCounter <limit>");
            System.exit(1);
        }

        long limit = Long.parseLong(args[0]);

        logger.log("Begin execution with %d numbers", limit);
        logger.log("Using calculator: %s", SerialCounter.class.getName());

        int count = logger.logDuration("Computed primes", () -> {
            return countPrimes(limit);
        });
        logger.log("Count = %d", count);
    }


    public static int countPrimes(long limit) {
        return PrimeFinder.countPrimes(0, limit);
    }
}
