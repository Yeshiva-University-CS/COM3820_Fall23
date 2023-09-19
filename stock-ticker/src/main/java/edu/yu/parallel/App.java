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
        // at 500 milliseconds interavals

        // Ensure that the application exits when the close button is clicked

        logger.info("Application finished");
    }

}

// Sample code to generate a random percentage between -5% and +5%
// Used to generate a price update
// double percentageChange = (random.nextDouble() - 0.5) * 0.1;
// double newPrice = currentPrice + (currentPrice * percentageChange);

// 14:15:08.872 [main   ] INFO  - Application starting
// 14:15:08.877 [ticker ] INFO  - Started
// 14:15:08.877 [updater] INFO  - Started
// 14:15:09.491 [updater] INFO  - Price update for AMGN [233.23 => 240.52]
// 14:15:10.434 [updater] INFO  - Price update for AXP [177.11 => 182.69]
// 14:15:11.037 [updater] INFO  - Price update for IBM [138.38 => 137.87]
// 14:15:11.947 [updater] INFO  - Price update for GS [350.86 => 349.24]
// 14:15:12.435 [ticker ] INFO  - Interrupted with an exception
// 14:15:12.435 [ticker ] INFO  - Stopped (Interrupted = true)
// 14:15:12.435 [main   ] INFO  - Application exiting
// 14:15:12.474 [updater] INFO  - Price update for AXP [182.69 => 181.58]
// 14:15:13.367 [updater] INFO  - Price update for HD [319.09 => 333.00]
// 14:15:13.894 [updater] INFO  - Price update for HON [207.96 => 208.32]

// NOTE: The "Interrupted with an exception" messasge should only be logged if the
// cancellation by the close button caused an InterruptedException. Whether or not it 
// does is dependent on execution timing.

// NOTE: Order of the threads starting (or their log messages) does not matter.

// NOTE: Log messages from the updater will be interleaved with the ticker and main thread logs,
// so order does not matter.

// Note: Log messages from the updater may or may not appear after the "Application exiting" log message. 
// This is also dependent on execution timing.


