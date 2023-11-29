package edu.yu.parallel.Impl;

import java.util.List;
import java.util.Map;

import edu.yu.parallel.Brokerage;
import edu.yu.parallel.Portfolio;
import edu.yu.parallel.Stock;
import edu.yu.parallel.StockPrices;

final public class BrokerageImpl implements Brokerage {

    /**
     * Constructor
     * 
     * @param stocks      list of Stock objects
     * @param stockPrices self-updating object with the current price of each stock
     */
    public BrokerageImpl(List<Stock> stocks, StockPrices stockPrices) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public Portfolio registerClient(String clientId, double initialBalance) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public Portfolio buyShares(String clientId, String symbol, int shares) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public Portfolio sellShares(String clientId, String symbol, int shares) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public Portfolio getClientPortfolio(String clientId) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public Map<String, Double> getStockPrices() {
        throw new UnsupportedOperationException("Implement this");
    }
}
