package com.crossover.trial.properties.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by thiago-rs on 12/28/15.
 */
public class PropertiesFileParser implements PropertiesParser{

    @Override
    public Map<String, Object> parseInput(InputStream is) throws IOException {

        final Map<String, Object> propsMap = new HashMap<>();
        final Properties props = new Properties();
        props.load(is);

        props.forEach((k,v) -> {

            propsMap.put(String.valueOf(k), parsePropValue(v));

        });

        return propsMap;

    }
}
