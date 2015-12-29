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
 * Parser to load .properties files into map
 * Created by thiago-rs on 12/28/15.
 */
public class PropertiesFileParser implements PropertiesParser {

    @Override
    public Map<String, Object> parseInput(InputStream is) throws IOException {

        final Properties props = new Properties();
        props.load(is);

        return parseTypes(props);

    }
}
