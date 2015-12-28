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

    default Object parsePropValue(Object value){

        final String val = value.toString();
        final Pattern p = Pattern.compile("^(-?)\\d+$");

        if(p.matcher(val).find()) {
            return Integer.parseInt(val);
        }else if(val.equalsIgnoreCase("true") ||
                val.equalsIgnoreCase("false")){
            return Boolean.parseBoolean(val);
        }

        return val;
    }

}
