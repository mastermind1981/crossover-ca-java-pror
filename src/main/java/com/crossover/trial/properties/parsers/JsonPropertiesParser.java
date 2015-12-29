package com.crossover.trial.properties.parsers;

import com.crossover.trial.properties.Messages;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * Parser to load Json files into Maps
 * Created by thiago-rs on 12/28/15.
 */
public class JsonPropertiesParser implements PropertiesParser {

    private final Logger log = Logger.getLogger(PropertiesFileParser.class);

    @Override
    public Map<String, Object> parseInput(InputStream is) throws IOException {
        final Gson gson = new GsonBuilder().create();
        final String json = CharStreams.toString(new InputStreamReader(is, "UTF-8"));
        return parseTypes(gson.fromJson(json, Map.class));
    }
}
