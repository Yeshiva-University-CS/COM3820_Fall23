package edu.yu.parallel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StockPrices {

    private static volatile StockPrices instance;

    final Map<String, Double> prices;

    ScheduledExecutorService scheduledExecutorService;

    /**
     * Private constructor for the singleton instance of this class
     * 
     * @param stocksWithInitialPrices
     */
    private StockPrices(List<Stock> stocksWithInitialPrices) {
        this.prices = new ConcurrentHashMap<>();
        for (var stock : stocksWithInitialPrices) {
            this.prices.put(stock.getSymbol(), stock.getPrice());
        }
    }

    /**
     * Get the singleton instance of this class
     * 
     * @param stocksWithInitialPrices
     * @return StockPrices instance
     */
    public static StockPrices getInstance(List<Stock> stocksWithInitialPrices) {
        if (instance == null) {
            synchronized (StockPrices.class) {
                if (instance == null) {
                    instance = new StockPrices(stocksWithInitialPrices);
                }
            }
        }
        return instance;
    }

    /**
     * Start updating the prices of the stocks
     */
    public void startUpdates() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });

        var random = new Random();
        List<String> symbols = new ArrayList<>(prices.keySet());

        Runnable task = () -> {
            // Randomly select a stock to update
            var symbol = symbols.get(random.nextInt(symbols.size()));
            double currentPrice = prices.get(symbol);

            // Change the price by a random percentage between -2% to +2%
            double percentageChange = (random.nextDouble() - 0.2) * 0.1;
            double newPrice = currentPrice + (currentPrice * percentageChange);
            newPrice = Math.round(newPrice * 100D) / 100D;

            // Update the stock's price
            prices.put(symbol, newPrice);
        };

        // Run the task every 500 milliseconds
        scheduledExecutorService.scheduleAtFixedRate(task, 500, 500, TimeUnit.MILLISECONDS);
    }

    /**
     * Stop updating the prices of the stocks
     */
    public void stopUpdates() {
        scheduledExecutorService.shutdown();
    }

    /**
     * Get the current price of a stock
     * 
     * @param symbol the stock's symbol
     * @return the stock's price
     * @throws IllegalArgumentException if the symbol is invalid
     */
    public double getStockPrice(String symbol) {
        if (!prices.containsKey(symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }
        return prices.get(symbol);
    }

    /**
     * Get the current prices of all stocks
     * 
     * @return a map of stock symbols to prices
     */
    public Map<String, Double> getStockPrices() {
        return Collections.unmodifiableMap(new HashMap<>(prices));
    }
}