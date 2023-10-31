package edu.yu.parallel;

public interface Client {

    /**
     * Get the client id
     * 
     * @return
     */
    String getClientId();

    /**
     * Try to buy the given number of shares of the given stock at the given price
     * 
     * @param symbol        the symbol of the stock to buy
     * @param shares        the number of shares to buy
     * @param pricePerShare the price per share
     * @return true if the buy was successful, false otherwise
     * @throws IllegalArgumentException if the shares is not positive or the price
     *                                  is negative
     */
    boolean tryBuyShares(String symbol, int shares, double pricePerShare);

    /**
     * Try to sell the given number of shares of the given stock at the given price
     * 
     * @param symbol        the symbol of the stock to sell
     * @param shares        the number of shares to sell
     * @param pricePerShare the price per share
     * @return true if the sell was successful, false otherwise
     * @throws IllegalArgumentException if the shares is not positive or the price
     *                                  is negative
     */
    boolean trySellShares(String symbol, int shares, double pricePerShare);

    /**
     * Get a snapshot of the client's portfolio
     * 
     * @return the client's updated portfolio information
     */
    Portfolio getPortfolio();

}