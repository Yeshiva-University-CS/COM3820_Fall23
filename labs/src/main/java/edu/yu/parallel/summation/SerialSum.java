package edu.yu.parallel.summation;

import java.util.function.Function;

import edu.yu.parallel.CustomLogger;

public class SerialSum {
    private static final CustomLogger logger = CustomLogger.getLogger(SerialSum.class);

    public static long computeTotals(long limit, Function<Long, Long> computation) {
        long totals = 0;
        for (long i = 0; i < limit; i++) {
            totals += computation.apply(i);
        }
        return totals;
    }

    public static void main(String[] args) {
        // read in command line arguments "-count=n" and "-computation=cpu|io|identity"
        // if not specified, use default values of 1000000000 and "identity"

        final long count = ParamUtils.getCountParameter(args, 1000000);

        final Function<Long, Long> computation = ParamUtils.getComputationParameter(args, Computations.identity);

        logger.log("Begin serial sum of %d computations", count);

        long total = logger.logDuration("Compute sum", () -> {
            return computeTotals(count, computation);
        });

        logger.log("Total = %d", total);
    }

}
