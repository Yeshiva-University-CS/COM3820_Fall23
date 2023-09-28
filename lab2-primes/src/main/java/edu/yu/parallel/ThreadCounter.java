package edu.yu.parallel;

import java.util.ArrayList;

import edu.yu.parallel.utils.CustomLogger;

public class ThreadCounter {
    private static CustomLogger logger = CustomLogger.getLogger(ThreadCounter.class);

    public static void main(String[] args) {
        if (args.length != 2) {
            logger.log("Usage: java edu.yu.parallel.ThreadCounter <limit> <numThreads>");
            System.exit(1);
        }

        long limit = Long.parseLong(args[0]);
        int numberOfThreads = Integer.parseInt(args[1]);

        logger.log("Begin execution with %d numbers and %d threads", limit, numberOfThreads);
        logger.log("Using calculator: %s", ThreadCounter.class.getName());

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
        var threadList = new ArrayList<PrimeFinderThread>(numberOfThreads);

        long chunkSize = limit / numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            long from = i * chunkSize;
            long to = (i == numberOfThreads - 1) ? limit : (i + 1) * chunkSize - 1;

            // TODO: create and start threads
        }

        int numberOfPrimes = 0;

        for (PrimeFinderThread thread : threadList) {
            thread.join();
            numberOfPrimes += thread.getNumberOfPrimes();
            logger.log("From %d to %d counted %d primes [in %.3f ms]",
                    thread.getFrom(), thread.getTo(), thread.getNumberOfPrimes(),
                    thread.getDuration() / 1e6);
        };

        return numberOfPrimes;
    }

    static class PrimeFinderThread extends Thread {
        private long from;
        private long to;
        private long duration;
        private int numberOfPrimes;

        public PrimeFinderThread(long from, long to) {
            this.from = from;
            this.to = to;
        }

        public int getNumberOfPrimes() {
            return numberOfPrimes;
        }

        public long getDuration() {
            return duration;
        }

        public long getFrom() {
            return from;
        }

        public long getTo() {
            return to;
        }

        public void run() {
            throw new UnsupportedOperationException("Implement me!");
        }
    }

}
