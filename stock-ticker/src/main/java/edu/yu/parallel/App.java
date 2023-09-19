package edu.yu.parallel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
        private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args)  {
        logger.info("Application started");

        // Create a list of 15 stocks using the StockReader class provided

        // Create and start a daemon thread that updates a random stock price every 1 second
        // Use the code suggestion below to generate a random price update
        // that will be within -5% and +5% of the current price
        // Log the thread start exactly as below in the samples
        // Log the update to the console exactly as below in the samples
        
        // Display stock prices in the PriceTickerFrame running in its own thread
        // Log the thread start and exit exactly as below in the samples
        // Refresh and update the display with the new prices and intraday change values
        // **at least** on 500 milliseconds interavals

        // Ensure that the application exits when the close button is clicked

        logger.info("Application finished");
    }

}

// Sample code to generate a random percentage between -5% and +5%
// Used to generate a price update
// double percentageChange = (random.nextDouble() - 0.5) * 0.1;
// double newPrice = currentPrice + (currentPrice * percentageChange);

// 13:04:16.556 [main] INFO  - Application starting
// 13:04:16.560 [ticker screen] INFO  - Started
// 13:04:16.561 [price updater] INFO  - Started
// 13:04:17.451 [price updater] INFO  - Price update for JNJ [168.38 => 167.96]
// 13:04:18.238 [price updater] INFO  - Price update for JPM [156.15 => 163.53]
// 13:04:18.776 [price updater] INFO  - Price update for JNJ [167.96 => 174.50]
// 13:04:18.920 [ticker screen] INFO  - Interrupted with an exception
// 13:04:18.921 [ticker screen] INFO  - Stopped (Interrupted = true)
// 13:04:18.921 [main] INFO  - Application exiting
// 13:04:19.593 [price updater] INFO  - Price update for KO [62.39 => 62.41]
// 13:04:20.294 [price updater] INFO  - Price update for JPM [163.53 => 160.93]

// NOTE: The "Interrupted with an exception" messasge should only be logged if the
// cancellation by the close button caused an InterruptedException


