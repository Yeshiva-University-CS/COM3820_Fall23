package edu.yu.parallel.summation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.UUID;
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
        fileIOActivity(30);
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
            e.printStackTrace();
        }
    }

    public static void fileIOActivity(int numberOfFiles) {
        File[] tempFiles = new File[numberOfFiles];

        // Create and write to temporary files
        for (int i = 0; i < numberOfFiles; i++) {
            String uniqueFileName = "tempFile_" + UUID.randomUUID().toString();
            try {
                tempFiles[i] = File.createTempFile(uniqueFileName, ".txt");

                try (FileWriter writer = new FileWriter(tempFiles[i])) {
                    // Write data to file
                    for (int j = 0; j < 1000; j++) {
                        writer.write("Test IO itensive " + uniqueFileName + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Clean up: Delete temporary files
        for (File file : tempFiles) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
