package edu.yu.parallel;

public class PrimeFinder {

    public static int countPrimes(long from, long to) {
        assert to >= from;
        assert from >= 0;

        int numberOfPrimes = 0;

        if (to < 2)
            return 0;
        else if (to == 2)
            return 1;
        else if (from <= 2)
            numberOfPrimes++;

        long begin = Math.max(from, 3);
        if (begin % 2 == 0)
            begin++;

        for (long i = begin; i <= to; i += 2) {
            if (isPrime(i)) {
                numberOfPrimes++;
            }
        }

        return numberOfPrimes;
    }

    public static boolean isPrime(long number) {
        assert number % 2 != 0;
        assert number > 2;

        long limit = (long) Math.sqrt(number);
        for (long i = 3; i <= limit; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
