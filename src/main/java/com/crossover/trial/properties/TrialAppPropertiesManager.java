package com.crossover.trial.properties;

import com.crossover.trial.properties.loaders.PropertiesLoader;
import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A simple main method to load and print properties. You should feel free to change this class
 * or to create additional class. You may add addtional methods, but must implement the 
 * AppPropertiesManager API contract.
 * 
 * Note: a default constructor is required
 *
 * @author code test administrator
 */
public class TrialAppPropertiesManager implements AppPropertiesManager {

    public static final Logger log = Logger.getLogger(TrialAppPropertiesManager.class);



    @Override
    public AppProperties loadProps(List<String> propUris) {

        final List<AppProperties> props = new ArrayList<>();

        propUris.forEach(x -> {

            try{

                final PropertiesLoader propsLoader = new PropertiesLoader(x);
                final AppProperties ap = propsLoader.loadProps()
                    .orElseThrow(() -> new NoSuchElementException(
                    String.format("%s: %s",Messages.CANT_LOAD_PROPERTIES, x)));

                props.add(ap);

            }catch (NoSuchElementException nsee){
                log.error(nsee);
            }

        });

        return props.stream()
             .filter(a -> a.isValid() && !a.getKnownProperties().isEmpty())
             .reduce((appProps1,appProps2) -> appProps1.concat(appProps2))
             .orElse(new EmptyAppProperties());
    }

    @Override
    public void printProperties(AppProperties props, PrintStream sync) {
        props.getKnownProperties().forEach(k -> {
            final String key = String.valueOf(k);
            final Object value = props.get(key);
            sync.println(String.format("%s, %s, %s",key,value.getClass(),value));
        });
    }
}
