package edu.yu.parallel.benchmarks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
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

import edu.yu.parallel.impl.SerialTransformer;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class Benchmarks {

    @Param({ "" })
    private String image;

    @Param({ "2", "3", "4", "10" })
    private double scaleFactor;

    private BufferedImage originalImage;

    @Setup
    public void setup() throws ImageReadException, IOException {
        Path inputPath = Paths.get(image).toAbsolutePath();
        originalImage = Imaging.getBufferedImage(new File(inputPath.toUri()));
    }

    @Benchmark
    public BufferedImage testSerial() throws Exception {
        var transformer = new SerialTransformer(originalImage);
        return transformer.resizeAndAdjustBrightness(scaleFactor, 0.5);
    }
}
