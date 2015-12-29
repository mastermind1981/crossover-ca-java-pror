package com.crossover.trial.properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Provides example usage of the API and classes - although candidates can use this
 * Main method to test if their changes will be accepted by the autograder. If your
 * solution is incompatible with this main method, it will be incompatible with the
 * autograder and may cause your solution to be failed.
 *
 * @author code test administrator
 */
public class Main {
    /**
     * Main method useful for your testing, this method is not tested by the grader.
     *
     * @param args
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void main(String[] args) throws URISyntaxException, IOException {

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.ERROR);

        PatternLayout layout = new PatternLayout("%5p %d [%t] (%F:%L) - %m%n");
        RollingFileAppender rfa = new RollingFileAppender(layout, "output.log");
        rfa.setMaximumFileSize(1000000);
        rootLogger.addAppender(rfa);

        // invoke the property parser and print out properties alphabetically
        AppPropertiesManager m = new TrialAppPropertiesManager();
        AppProperties props = m.loadProps(Arrays.asList(args));
        m.printProperties(props, System.out);
    }
}
