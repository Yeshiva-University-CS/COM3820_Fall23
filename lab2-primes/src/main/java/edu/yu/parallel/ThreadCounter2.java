package edu.yu.parallel;

import java.util.function.Supplier;

import edu.yu.parallel.utils.CustomLogger;

public class ThreadCounter2 {
    private static CustomLogger logger = CustomLogger.getLogger(ThreadCounter2.class);

    public static void main(String[] args) {
        if (args.length != 2) {
            logger.log("Usage: java edu.yu.parallel.ThreadCounter2 <limit> <numThreads>");
            System.exit(1);
        }

        long limit = Long.parseLong(args[0]);
        int numberOfThreads = Integer.parseInt(args[1]);

        logger.log("Begin execution with %d numbers and %d threads", limit, numberOfThreads);
        logger.log("Using calculator: %s", ThreadCounter2.class.getName());

        int count = logger.logDuration("Computed primes", () -> {
            try {
                return countPrimes(limit, numberOfThreads);
            } catch (InterruptedException e) {
                logger.log("Interrupted");;
                return 0;
            }
        });
        
        logger.log("Count = %d", count);
    }

    public static int countPrimes(long limit, int numberOfThreads) throws InterruptedException {
        throw new UnsupportedOperationException("Implement me!");

        // int numberOfPrimes = 0;

        // for (PrimeFinderThread thread : threadList) {
        //     thread.join();
        //     numberOfPrimes += thread.getNumberOfPrimes();
        //     logger.log("From %d to %d counted %d primes [in %.3f ms]",
        //             thread.getFrom(), thread.getTo(), thread.getNumberOfPrimes(),
        //             thread.getDuration() / 1e6);
        // };

        // return numberOfPrimes;
    }

    static class PrimeFinderThread extends Thread {
        private final Supplier<Long> candidateGenerator;
        private long duration;
        private int numberOfPrimes;

        public PrimeFinderThread(Supplier<Long> candidateGenerator) {
            this.candidateGenerator = candidateGenerator;
        }

        public int getNumberOfPrimes() {
            return numberOfPrimes;
        }

        public long getDuration() {
            return duration;
        }

        public void run() {
            throw new UnsupportedOperationException("Implement me!");
        }
    }

}
