package edu.yu.parallel.summation;

import java.util.function.Function;

public class ParamUtils {

    public static long getCountParameter(String[] args, long defaultValue) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-count=")) {
                return Long.parseLong(args[i].substring("-count=".length()));
            }
        }
        return defaultValue;
    }

    public static Function<Long, Long> getComputationParameter(String[] args, Function<Long, Long> defaultValue) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-computation=")) {
                String computationName = args[i].substring("-computation=".length());
                if (computationName.equals("cpu")) {
                    return Computations.cpuIntensive;
                } else if (computationName.equals("io")) {
                    return Computations.ioIntensive;
                } else if (computationName.equals("identity")) {
                    return Computations.identity;
                } else {
                    System.err.println("Unknown computation: " + computationName);
                    System.exit(1);
                }
            }
        }
        return defaultValue;
    }

}
