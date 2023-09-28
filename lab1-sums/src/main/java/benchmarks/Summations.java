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

import edu.yu.parallel.lab1.ComputeKernels;
import edu.yu.parallel.lab1.ComputeSum;
import edu.yu.parallel.lab1.SerialAlgorithm;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class Summations {

    @Param({ "1000" })
    private long count;

    @Setup
    public void setup() {
        System.out.println("count = " + count);
    }

    @Benchmark
    public long testSerial() throws Exception {
        return ComputeSum.create()
                .withAlgorithm(new SerialAlgorithm())
                .withKernel(ComputeKernels.identity)
                .startingFrom(0L)
                .endingAt(count)
                .execute();
    }

    @Benchmark
    public long testSerialCPU() throws Exception {
        return ComputeSum.create()
                .withAlgorithm(new SerialAlgorithm())
                .withKernel(ComputeKernels.cpuIntensive)
                .startingFrom(0L)
                .endingAt(count)
                .execute();
    }

    @Benchmark
    public long testSerialIO() throws Exception {
        return ComputeSum.create()
                .withAlgorithm(new SerialAlgorithm())
                .withKernel(ComputeKernels.ioIntensive)
                .startingFrom(0L)
                .endingAt(count)
                .execute();
    }
}
