package edu.yu.parallel.lab1;

import java.util.function.Function;

import edu.yu.parallel.CustomLogger;

public class ComputeSum {
        private static CustomLogger logger = CustomLogger.getLogger(ComputeSum.class);

        private Function<Long, Long> kernel;
        private Long start = 0L;
        private Long end = 0L;
        private ComputeAlgorithm algorithm;

        private ComputeSum() {
        }

        public static ComputeSum create() {
                return new ComputeSum();
        }

        public ComputeSum withKernel(Function<Long, Long> kernel) {
                this.kernel = kernel;
                return this;
        }

        public ComputeSum startingFrom(long start) {
                this.start = start;
                return this;
        }

        public ComputeSum endingAt(long end) {
                this.end = end;
                return this;
        }

        public ComputeSum withAlgorithm(ComputeAlgorithm algorithm) {
                this.algorithm = algorithm;
                return this;
        }


        public long executeAndLog() {
                assert kernel != null;
                assert algorithm != null;

                logger.log("Begin execution of %d computations", (end - start));
                logger.log("Using algorithm: %s", algorithm.description());

                return logger.logDuration("Computed sum", () -> {
                        return algorithm.computeTotals(start, end, kernel);
                });
        }

        public long execute() {
                return algorithm.computeTotals(start, end, kernel);
        }

        public static void main(String[] args) {
                // read in command line arguments:
                // "-count=n" 
                // "-computation=cpu|io|identity"
                // "-algorithm=serial|threads|executor" 

                final long count = ParamUtils.getCountParameter(args);

                final Function<Long, Long> computation = ParamUtils.getComputationParameter(args);

                final ComputeAlgorithm algorithm = ParamUtils.getAlgorithmParameter(args);

                long total = new ComputeSum()
                    .withAlgorithm(algorithm)
                    .withKernel(computation)
                    .startingFrom(0L)
                    .endingAt(count)
                    .executeAndLog();

                logger.log("Total = %d", total);
        }       
}
