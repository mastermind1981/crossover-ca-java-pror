package com.crossover.trial.properties.loaders;

import com.crossover.trial.properties.AppProperties;
import com.crossover.trial.properties.Messages;
import com.crossover.trial.properties.handler.ProtocolHandler;
import com.crossover.trial.properties.parsers.JsonPropertiesParser;
import com.crossover.trial.properties.parsers.PropertiesFileParser;
import com.crossover.trial.properties.parsers.PropertiesParser;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

/**
 * Interface to define behaviour of components that loads properties in
 * different ways
 *
 * Created by thiago-rs on 12/28/15.
 */
public class PropertiesLoader {

    protected final Logger log = Logger.getLogger(PropertiesLoader.class);

    protected String uri;

    protected PropertiesParser parser;

    public PropertiesLoader(String uri) {
        this.uri = uri;

        if(uri.endsWith(".json")){
            this.parser = new JsonPropertiesParser();
        }else if(uri.endsWith(".properties")){
            this.parser = new PropertiesFileParser();
        }
    }

    /**
     * Load properties from given URI
     * @return AppProperties
     * @throws Exception whenever can't load from URI
     */
    public Optional<AppProperties> loadProps(){

        Optional<AppProperties> opt = Optional.empty();

        try{

            System.out.println(System.getProperty("java.protocol.handler.pkgs"));

            final URL url = new URL(null, uri,
                    new ProtocolHandler(ClassLoader.getSystemClassLoader()));

            final Map<String,Object> props =
                    parser.parseInput(url.openStream());

//            opt = Optinal.of  ;

        }catch (NullPointerException npe) {
            log.error(Messages.CANT_LOAD_PARSER+": "+uri,npe);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return opt;

    }

}