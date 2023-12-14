package edu.yu.parallel;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;


public class App {
    final static Logger logger = LogManager.getLogger(App.class);

    static {
        Configurator.setLevel("edu.yu.parallel", Level.INFO);
    }

    public static void main(String[] args) throws IOException {

        var symbolCache = new SymbolCache(new SymbolReader("nasdaq"));

        logger.info("{} symbols", symbolCache.stream().count());

    }
}
