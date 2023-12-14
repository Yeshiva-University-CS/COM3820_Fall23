package edu.yu.parallel;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SymbolCache {
    private final Map<String, SymbolData> symbolList;

    public SymbolCache(SymbolReader reader) {
        this.symbolList = new HashMap<>(); // implement this with streams
    }


    public Stream<SymbolData> stream() {
        return symbolList.values().stream();
    }

    public SymbolData getSymbolData(String symbol) {
        return symbolList.get(symbol);
    }
}
