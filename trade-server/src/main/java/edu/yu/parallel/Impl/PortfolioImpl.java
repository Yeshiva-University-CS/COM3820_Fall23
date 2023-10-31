package edu.yu.parallel.Impl;

import java.util.Map;

import edu.yu.parallel.Portfolio;

final public class PortfolioImpl implements Portfolio {

    /**
     * Contstructor
     * 
     * @param clientId
     * @param accountBalance
     * @param stockPositions
     */
    public PortfolioImpl(String clientId, double accountBalance, Map<String, Integer> stockPositions) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public String getClientId() {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public double getBalance() {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public Map<String, Integer> getStockPositions() {
        throw new UnsupportedOperationException("Implement this");
    }

}
