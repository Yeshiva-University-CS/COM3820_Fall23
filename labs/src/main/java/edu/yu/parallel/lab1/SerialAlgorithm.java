package edu.yu.parallel.lab1;

import java.util.function.Function;

public class SerialAlgorithm implements ComputeAlgorithm {

    
    @Override
    public long computeTotals(long start, long end, Function<Long, Long> kernel) {
        long totals = 0;
        for (long i = start; i <= end; i++) {
            totals += kernel.apply(i);
        }
        return totals;
    }
    
}
