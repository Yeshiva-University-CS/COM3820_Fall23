package edu.yu.parallel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application starting");
        logger.info("Hello, world!");
        logger.info("Application exiting");
    }

}
