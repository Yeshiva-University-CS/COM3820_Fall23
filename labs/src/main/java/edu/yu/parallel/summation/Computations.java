package edu.yu.parallel.summation;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.function.Function;

public class Computations {

    public static Function<Long, Long> identity = (Long x) -> x;

    public static Function<Long, Long> cpuIntensive = (Long x) -> {
        long y = 0;
        for (int i = 0; i < 100000000; i++) {
            y = y + 1;
        }
        for (int i = 0; i < 100000000; i++) {
            y = y - 1;
        }
        return y + x;
    };

    public static Function<Long, Long> ioIntensive = (Long x) -> {
        for (int i = 0; i < 20; i++) {
            simulateFileIO(1);
        }
        return x;
    };

    public static void simulateFileIO(int MB) {
        try (WritableByteChannel nullChannel = Channels.newChannel(OutputStream.nullOutputStream())) {
            // Data to "write"
            byte[] data = new byte[(1024 * 1024) * MB]; // 1 MB
            ByteBuffer buffer = ByteBuffer.wrap(data);

            // Write to null channel
            while (buffer.hasRemaining()) {
                nullChannel.write(buffer);
            }
        } catch (Exception e) {
        }
    }
}
