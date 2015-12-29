package com.crossover.trial.properties.parsers;

import com.crossover.trial.properties.Messages;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Specifies how Parsers should behave
 * Created by thiago-rs on 12/28/15.
 */
public interface PropertiesParser {

    /**
     * Takes a InputStream from json or properties files
     * and returns a Map
     * @param is InputStream
     * @return Map<String, Object>
     * @throws IOException if can't read from InputStream
     */
    Map<String, Object> parseInput(InputStream is) throws IOException;

    /**
     * Parsers properties from String and validates each
     * property agains it's type
     * @param m Map
     * @return Map<String, Object>
     */
    default Map<String, Object> parseTypes(Map m) {

        final Logger log = Logger.getLogger(PropertiesParser.class);

        final Map<String, Object> propsMap = new HashMap<>();

        m.forEach((k, v) -> {

            final String key = String.valueOf(k);
            final String val = String.valueOf(v);
            final Optional<Object> value = PropTypeMapper.getConverterFunction(key).apply(val);
            propsMap.put(key, value.get());

        });

        propsMap.entrySet()
                .stream()
                .filter(p -> p.getValue().getClass().isAssignableFrom(Class.class))
                .forEach(p -> log.error(String.format(Messages.PROPERTY_INVALID, p.getKey())));

        return propsMap;

    }

}
