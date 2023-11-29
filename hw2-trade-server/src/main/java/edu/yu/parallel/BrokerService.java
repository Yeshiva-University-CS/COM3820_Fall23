package edu.yu.parallel;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

/**
 * The BrokerService interface defines the methods that must be implemented by a
 * class that provides the functionality of a broker service.
 * 
 * The BrokerService is responsible for handling requests from clients and
 * returning the appropriate responses.
 * 
 * HTTP response codes are as follows:
 *   - Successful requests should return a 200 response code.
 *   - Requests with an incorrect HTTP Method should return a 405 response code
 *   - Requests resulting in the the following exceptions should return a 400 response code:
 *      - IllegalArgumentException
 *      - InsufficientSharesException
 *      - InsufficientFundsException
 *   - Requests resulting in any other exception should return a 500 response code.
 *   
 * 
 */
public interface BrokerService {

    /**
     * Handle a request to get the current stock prices
     * GET /prices
     * Response: a JSON array of JSON objects, each containing the symbol and price of a stock
     * 
     * @param exchange the HttpExchange object
     * @throws IOException
     */
    void handleGetPrices(HttpExchange exchange) throws IOException;

    /**
     * Handle a request to register a client
     * POST /register
     * Query Parameters: clientId, funds
     * Response: JSON representation of the client's Portfolio
     * 
     * @param exchange the HttpExchange object
     * @throws IOException
     */
    void handleRegisterClient(HttpExchange exchange) throws IOException;

    /**
     * Handle a request to get a client's portfolio
     * GET /portfolio
     * Query Parameters: clientId
     * Response: JSON representation of the client's Portfolio
     * 
     * @param exchange the HttpExchange object
     * @throws IOException
     */
    void handleGetPortfolio(HttpExchange exchange) throws IOException;

    /**
     * Handle a request to get the market value of a client's portfolio
     * GET /marketvalue
     * Query Parameters: clientId
     * Response: JSON object containing the total market value of the client's portfolio
     * {"marketvalue": 123.45"}
     * 
     * @param exchange the HttpExchange object
     * @throws IOException
     */
    void handleGetMarketValue(HttpExchange exchange) throws IOException;

    /**
     * Handle a request to buy shares of a stock for a client
     * POST /buy
     * Query Parameters: clientId, symbol, shares
     * Response: JSON representation of the client's Portfolio
     * 
     * @param exchange the HttpExchange object
     * @throws IOException
     */
    void handleBuyRequest(HttpExchange exchange) throws IOException;

    /**
     * Handle a request to sell shares of a stock for a client
     * POST /sell
     * Query Parameters: clientId, symbol, shares
     * Response: JSON representation of the client's Portfolio
     * 
     * @param exchange the HttpExchange object
     * @throws IOException
     */
    void handleSellRequest(HttpExchange exchange) throws IOException;

}