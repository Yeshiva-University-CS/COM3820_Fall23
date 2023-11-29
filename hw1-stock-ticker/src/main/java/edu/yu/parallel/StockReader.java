package edu.yu.parallel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class StockReader {
    private final String fileName;
    private final Function<String, List<String>> csvLineParser = line -> Arrays.asList(line.split(","));
    private final Function<List<String>, Stock> inputToRecord = input -> {

        var symbol = input.get(0);
        var name = input.get(1);
        var close = Math.round(Double.valueOf(input.get(2)) * 100D) / 100D;

        return new Stock(symbol, name, close);
    };

    public StockReader(String stockFile) {
        this.fileName = "/" + stockFile;
    }

    public List<Stock> getStockList() {
        return fromFile().skip(1).map(csvLineParser).map(inputToRecord).toList();
    }
    
    public List<Stock> getStockList(int limit) {
        return fromFile().skip(1).map(csvLineParser).map(inputToRecord).limit(limit).toList();
    }

    private Stream<String> fromFile() {
        InputStream csvFileStream = getClass().getResourceAsStream(this.fileName);
        return new BufferedReader(new InputStreamReader(csvFileStream)).lines();
    }
}