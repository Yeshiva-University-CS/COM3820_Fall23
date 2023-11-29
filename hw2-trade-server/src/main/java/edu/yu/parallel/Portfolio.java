package edu.yu.parallel;

import java.util.Map;

public interface Portfolio {

    /**
     * Get the client id
     * 
     * @return
     */
    String getClientId();

    /**
     * Get the client account's cash balance
     * 
     * @return
     */
    double getBalance();

    /**
     * Get the client's stock positions
     * 
     * @return
     */
    Map<String, Integer> getStockPositions();

}