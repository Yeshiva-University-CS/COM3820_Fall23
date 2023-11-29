package edu.yu.parallel.Impl;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;

import edu.yu.parallel.BrokerService;
import edu.yu.parallel.Brokerage;
import edu.yu.parallel.Portfolio;

final public class BrokerServiceImpl implements BrokerService {

    /**
     * Constructor
     * 
     * @param brokerage
     */
    public BrokerServiceImpl(Brokerage brokerage) {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public void handleGetPrices(HttpExchange exchange) throws IOException {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public void handleRegisterClient(HttpExchange exchange) throws IOException {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public void handleGetPortfolio(HttpExchange exchange) throws IOException {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public void handleGetMarketValue(HttpExchange exchange) throws IOException {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public void handleBuyRequest(HttpExchange exchange) throws IOException {
        throw new UnsupportedOperationException("Implement this");
    }

    @Override
    public void handleSellRequest(HttpExchange exchange) throws IOException {
        throw new UnsupportedOperationException("Implement this");
    }

    // JSON Utility Methods

    private JSONObject exceptionToJSON(Exception e) {
        JSONObject mainJson = new JSONObject();
        mainJson.put("type", e.getClass().getSimpleName());
        mainJson.put("message", e.getMessage());

        JSONArray stackTraceArray = new JSONArray();
        for (StackTraceElement element : e.getStackTrace()) {
            stackTraceArray.put(element.toString());
        }

        mainJson.put("stacktrace", stackTraceArray);

        return mainJson;
    }

    private JSONObject portfolioToJSON(Portfolio portfolio) {
        // Create the main JSONObject and populate it
        JSONObject mainJson = new JSONObject();
        mainJson.put("clientId", portfolio.getClientId());
        mainJson.put("accountBalance", portfolio.getBalance());
        mainJson.put("stockPositions", new JSONObject(portfolio.getStockPositions()));

        return mainJson;
    }

    private JSONObject messageToJSON(String message) {
        return new JSONObject().put("message", message);
    }

}
