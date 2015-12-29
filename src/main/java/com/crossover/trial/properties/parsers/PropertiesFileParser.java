package com.crossover.trial.properties.parsers;

import com.crossover.trial.properties.Messages;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by thiago-rs on 12/28/15.
 */
public class PropertiesFileParser implements PropertiesParser{

    private final Logger log = Logger.getLogger(PropertiesFileParser.class);

    @Override
    public Map<String, Object> parseInput(InputStream is) throws IOException {

        final Map<String, Object> propsMap = new HashMap<>();
        final Properties props = new Properties();
        props.load(is);

        props.forEach((k,v) -> {

            final String key = String.valueOf(k);
            final String val = String.valueOf(v);
            final Optional<Object> value = PropTypeMapper.getConverterFunction(key).apply(val);
            propsMap.put(key, value.get());

        });

        propsMap.entrySet()
                .stream()
                .filter(p -> p.getValue().getClass().isAssignableFrom(Class.class))
//                .forEach(p -> log.info(String.format(Messages.PROPERTY_INVALID, p.getKey())));
                .forEach(p -> System.out.println(p));

        return propsMap;

    }
}
