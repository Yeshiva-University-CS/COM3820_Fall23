package edu.yu.parallel;

import java.util.Map;

public interface Brokerage {

    public class InsufficientSharesException extends RuntimeException {
    }

    public class InsufficientFundsException extends RuntimeException {
    }

    /**
     * Register a client with the brokerage
     * 
     * @param clientId       the id of the client
     * @param initialBalance the initial cash balance of the client
     * @return the client's updated portfolio information
     * @throws IllegalArgumentException if the client is already registered
     */
    Portfolio registerClient(String clientId, double initialBalance);

    /**
     * Buy shares of a stock for a client
     * 
     * @param clientId the id of the client
     * @param symbol   the symbol of the stock
     * @param shares   the number of shares to buy
     * @return the client's updated portfolio information
     * @throws IllegalArgumentException    if the client is not registered, or the
     *                                     symbol is invalid, or the shares is not
     *                                     positive
     * @throws InsufficientSharesException if there are not enough shares available
     * @throws InsufficientFundsException  if the client does not have enough funds
     */
    Portfolio buyShares(String clientId, String symbol, int shares);

    /**
     * Sell shares of a stock for a client
     *
     * @param clientId the client id of the client
     * @param symbol   the symbol of the stock to sell
     * @return the client's updated portfolio information
     * @throws IllegalArgumentException    if the client is not registered, or the
     *                                     symbol is invalid, pr the shares is not
     *                                     positive
     * @throws InsufficientSharesException if the client does not have enough shares
     *                                     to sell
     */
    Portfolio sellShares(String clientId, String symbol, int shares);

    /**
     * Get the current portfolio of a client
     * 
     * @param clientId the id of the client
     * @return the client's updated portfolio information
     * @throws IllegalArgumentException if the client is not registered
     */
    Portfolio getClientPortfolio(String clientId);

    /**
     * Get the current prices of all stocks
     * 
     * @return a map of stock symbols to prices
     */
    Map<String, Double> getStockPrices();

}