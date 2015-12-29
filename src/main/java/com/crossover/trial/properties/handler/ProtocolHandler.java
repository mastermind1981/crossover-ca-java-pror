package com.crossover.trial.properties.handler;

/**
 * Handles different protocols for URLConnection
 * Created by thiago-rs on 12/28/15.
 */

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * A {@link URLStreamHandler} that handles resources on the classpath.
 */
public class ProtocolHandler extends URLStreamHandler {
    /**
     * The classloader to find resources from.
     */
    private final ClassLoader classLoader;

    public ProtocolHandler() {
        this.classLoader = getClass().getClassLoader();
    }

    public ProtocolHandler(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        final URL resourceUrl = classLoader.getResource(u.getPath());
        return resourceUrl.openConnection();
    }
}