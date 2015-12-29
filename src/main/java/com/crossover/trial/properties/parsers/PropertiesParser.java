package com.crossover.trial.properties.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by thiago-rs on 12/28/15.
 */
public interface PropertiesParser {

    Map<String,Object> parseInput(InputStream is) throws IOException;

}
