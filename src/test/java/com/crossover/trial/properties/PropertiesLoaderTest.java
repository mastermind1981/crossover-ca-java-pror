package com.crossover.trial.properties;

import com.crossover.trial.properties.loaders.PropertiesLoader;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by thiago-rs on 12/28/15.
 */
public class PropertiesLoaderTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsuportedProtocolLoader() {

        final String uri = "xxx:resources/jdbc.properties";
        final PropertiesLoader propsLoader = new PropertiesLoader(uri);
        propsLoader.loadProps();

        fail("Protocol should be unsupported");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testNullProtocolLoader() {

        new PropertiesLoader(null).loadProps();

        fail("Null URI should be unsupported");
    }


    @Test()
    public void testClasspathURIProperties() {

        final String uri = "classpath:jdbc.properties";

        final PropertiesLoader propsLoader = new PropertiesLoader(uri);
        final AppProperties ap = propsLoader.loadProps();

        assertTrue("Cant load classpath:jdbc.properties", ap.getKnownProperties().size() == 5);
        assertTrue("Unhandle property type", ap.getMissingProperties().size() == 0);

    }

    @Test()
    public void testFileSystemLoaderProperties() {

        final String uri = this.getClass().getClassLoader().getResource("aws.properties").toExternalForm();
        final PropertiesLoader propsLoader = new PropertiesLoader(uri);
        final AppProperties ap = propsLoader.loadProps();

        assertTrue("Cant load file:///...aws.properties", ap.getKnownProperties().size() == 4);
        assertTrue("Unhandle property type", ap.getMissingProperties().size() == 0);
    }

    @Test()
    public void testFileSystemLoaderJson() {

        final String uri = this.getClass().getClassLoader().getResource("config.json").toExternalForm();
        final PropertiesLoader propsLoader = new PropertiesLoader(uri);
        final AppProperties ap = propsLoader.loadProps();

        assertTrue("Cant load file:///...confi.json", ap.getKnownProperties().size() == 5);
        assertTrue("Unhandle property type", ap.getMissingProperties().size() == 0);
    }


    public void testHttpLoaderJson() {

        final String uri = "http://localhost:8080/config.json";
        final PropertiesLoader propsLoader = new PropertiesLoader(uri);
        final AppProperties ap = propsLoader.loadProps();

        assertTrue("Cant load http://localhost/config.json", ap.getKnownProperties().size() == 5);
        assertTrue("Unhandle property type", ap.getMissingProperties().size() == 0);
    }

    public void testHttpLoaderProperties() {

        final String uri = "http://localhost:8080/aws.properties";
        final PropertiesLoader propsLoader = new PropertiesLoader(uri);
        final AppProperties ap = propsLoader.loadProps();

        assertTrue("Cant load http://localhost/aws.properties", ap.getKnownProperties().size() == 4);
        assertTrue("Unhandle property type", ap.getMissingProperties().size() == 0);
    }

}
