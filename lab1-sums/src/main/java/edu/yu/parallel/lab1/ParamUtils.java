package edu.yu.parallel.lab1;

import java.util.function.Function;

public class ParamUtils {

    public static long getCountParameter(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-count=")) {
                return Long.parseLong(args[i].substring("-count=".length()));
            }
        }
        throw new IllegalArgumentException("No count parameter found");
    }

    public static Function<Long, Long> getComputationParameter(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-computation=")) {
                String computationName = args[i].substring("-computation=".length());
                if (computationName.equals("cpu")) {
                    return ComputeKernels.cpuIntensive;
                } else if (computationName.equals("io")) {
                    return ComputeKernels.ioIntensive;
                } else if (computationName.equals("identity")) {
                    return ComputeKernels.identity;
                } else {
                    System.err.println("Unknown computation: " + computationName);
                    System.exit(1);
                }
            }
        }
        throw new IllegalArgumentException("No computation parameter found");
    }

    public static ComputeAlgorithm getAlgorithmParameter(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-algorithm=")) {
                String algorithm = args[i].substring("-algorithm=".length());
                if (algorithm.equals("serial")) {
                    return new SerialAlgorithm();
                } else if (algorithm.equals("threads")) {
                    throw new UnsupportedOperationException("Not implemented yet");
                } else if (algorithm.equals("executor")) {
                     throw new UnsupportedOperationException("Not implemented yet");
                } else {
                    System.err.println("Unknown algorithm: " + algorithm);
                    System.exit(1);
                }
            }
        }
        throw new IllegalArgumentException("No algorithm parameter found");
    }

}
