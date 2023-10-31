package edu.yu.parallel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class TradeServer {
    private final int port;
    private final int brokerCount;
    private final HttpServer server;

    public TradeServer(int port, int brokerCount, BrokerService brokerService) throws IOException {
        this.port = port;
        this.brokerCount = brokerCount;

        // Create and configure the HttpServer
        this.server =  HttpServer.create(new InetSocketAddress(port), 0);
        this.server.setExecutor(Executors.newFixedThreadPool(brokerCount));

        // set up the routes to the broker service
        this.server.createContext("/prices", brokerService::handleGetPrices);
        this.server.createContext("/register", brokerService::handleRegisterClient);
        this.server.createContext("/portfolio", brokerService::handleGetPortfolio);
        this.server.createContext("/marketvalue", brokerService::handleGetMarketValue);
        this.server.createContext("/buy", brokerService::handleBuyRequest);
        this.server.createContext("/sell", brokerService::handleSellRequest);        
    }
    
    /**
     * Starts the HttpServer
     */
    public void start() {
        this.server.start();
    }

    /**
     * Stops the HttpServer
     *
     * @param delay the maximum time in seconds to wait until exchanges have finished
     * @throws IllegalArgumentException if delay is less than zero
     */
    public void stop(int delay) {
        this.server.stop(delay);
    }

    /**
     * Get the port the server is running on
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Get the number of broker threads
     * @return the number of broker threads
     */
    public int getBrokerCount() {
        return brokerCount;
    }
}
