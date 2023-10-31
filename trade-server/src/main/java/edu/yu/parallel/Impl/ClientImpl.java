package edu.yu.parallel.Impl;

import edu.yu.parallel.Client;
import edu.yu.parallel.Portfolio;

final class ClientImpl implements Client {

    /**
     * Constructor
     * 
     * @param clientId
     * @param funds
     */
    public ClientImpl(String clientId, double funds) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public String getClientId() {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public boolean tryBuyShares(String symbol, int shares, double pricePerShare) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public boolean trySellShares(String symbol, int shares, double pricePerShare) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public Portfolio getPortfolio() {
        throw new UnsupportedOperationException("Implement this");
    }

}