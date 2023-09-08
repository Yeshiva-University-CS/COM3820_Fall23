package benchmarks;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import edu.yu.parallel.summation.Computations;
import edu.yu.parallel.summation.SerialSum;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class Summations {
    @Param({ "10000"})
    private int limit;

    @Setup
    public void setup() {
        System.out.println("limit = " + limit);
    }

    @Benchmark
    public long testSerial() throws Exception {
        return SerialSum.computeTotals(limit, Computations.identity);
    }

    @Benchmark
    public long testSerialCPU() throws Exception {
        return SerialSum.computeTotals(limit, Computations.cpuIntensive);
    }

    @Benchmark
    public long testSerialIO() throws Exception {
        return SerialSum.computeTotals(limit, Computations.ioIntensive);
    }
}
