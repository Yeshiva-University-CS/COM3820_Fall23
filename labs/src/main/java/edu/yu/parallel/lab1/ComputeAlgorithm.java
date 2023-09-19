package edu.yu.parallel.lab1;

import java.util.function.Function;

public interface ComputeAlgorithm {

    default String description() {
        return this.getClass().getSimpleName();
    }

    long computeTotals(long start, long end, Function<Long, Long> kernel);
    
}
