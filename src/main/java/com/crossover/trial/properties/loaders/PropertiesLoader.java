package com.crossover.trial.properties.loaders;

import com.crossover.trial.properties.AppProperties;
import com.crossover.trial.properties.Messages;
import com.crossover.trial.properties.TrialAppProperties;
import com.crossover.trial.properties.handler.ProtocolHandler;
import com.crossover.trial.properties.parsers.JsonPropertiesParser;
import com.crossover.trial.properties.parsers.PropertiesFileParser;
import com.crossover.trial.properties.parsers.PropertiesParser;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
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

    private ProtocolHandler protocolHandler =
            new ProtocolHandler(ClassLoader.getSystemClassLoader());

    protected String uri;

    protected PropertiesParser parser;

    public PropertiesLoader(String uri) {
        this.uri = uri;

        if(uri != null && uri.endsWith(".json")){
            this.parser = new JsonPropertiesParser();
        }else if(uri != null && uri.endsWith(".properties")){
            this.parser = new PropertiesFileParser();
        }
    }

    /**
     * Load properties from given URI
     * @return AppProperties
     * @throws Exception whenever can't load from URI
     */
    public AppProperties loadProps() throws UnsupportedOperationException{

        try{

            final URL url = buildUrl();
            final Map<String,Object> props =
                    parser.parseInput(url.openStream());

            return new TrialAppProperties(props);

        }catch (NullPointerException npe) {
            new UnsupportedOperationException(Messages.CANT_LOAD_PARSER+": "+uri,npe);
        } catch (MalformedURLException mfe) {
            new UnsupportedOperationException(Messages.CANT_LOAD_PROPERTIES+": "+uri,mfe);
        } catch (IOException ioe) {
            new UnsupportedOperationException(ioe);
        }

        throw new UnsupportedOperationException(Messages.CANT_LOAD_PROPERTIES+": "+uri);

    }

    public URL buildUrl() throws MalformedURLException {

        try {

            if (uri.startsWith("classpath")) {
                return new URL(null, uri, protocolHandler);
            } else return new URL(uri);
        }catch (NullPointerException npe){
            log.error(npe);
            throw new MalformedURLException();
        }

    }

}
