package edu.yu.parallel;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.yu.parallel.Impl.BrokerServiceImpl;
import edu.yu.parallel.Impl.BrokerageImpl;

public class App {
        private static final Logger logger = LogManager.getLogger(App.class);

        private static final int port = 8080;
        private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        logger.info("Application starting");

        // Read in the number of broker threads to run
        int brokerCount = 1;
        if (args.length > 0) {
            brokerCount = Integer.parseInt(args[0]);
        }
        logger.info("Using {} broker(s)", brokerCount);

        // Create a list of stocks
        var stocks = new StockReader("stocks.csv").getStockList(15);
        logger.info("Loaded {} stocks", stocks.size());

        // Instantiate the singleton StockPrices class and
        var stockPrices = StockPrices.getInstance(stocks);

        // Create a brokerage, brokerService, and tradeServer
        var brokerage = new BrokerageImpl(stocks, stockPrices);
        var brokerService = new BrokerServiceImpl(brokerage);
        var tradeServer = new TradeServer(port, brokerCount, brokerService);

        // start updating the prices of the stocks
        stockPrices.startUpdates();
        logger.info("Started updating of stock prices");

        tradeServer.start();
       
        logger.info("Server started on port {}. Press Ctrl+C to stop...", port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            tradeServer.stop(0);
            logger.info("Server stopped.");
            latch.countDown();
        }));

        try {
            latch.await(); // This will block the main thread until the latch reaches zero.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Application exiting");
    }
}
