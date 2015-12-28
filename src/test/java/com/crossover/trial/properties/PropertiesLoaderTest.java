package com.crossover.trial.properties;

import com.crossover.trial.properties.loaders.*;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by thiago-rs on 12/28/15.
 */
public class PropertiesLoaderTest {

//    private final PropertiesLoaderFactory plFactory = new PropertiesLoaderFactory();
//
//    @Test(expected = NoSuchElementException.class)
//    public void testUnsuportedProtocolLoader(){
//
//        final String uri = "xxx:resources/jdbc.properties";
//
//        plFactory.getLoader(uri).orElseThrow(() -> new NoSuchElementException(
//                String.format("%s: %s", Messages.CANT_LOAD_PROPERTIES, uri)));
//
//        fail();
//    }
//
//    @Test(expected = NoSuchElementException.class)
//    public void testNullProtocolLoader(){
//
//        plFactory.getLoader(null).orElseThrow(() -> new NoSuchElementException(
//                String.format("%s: %s", Messages.CANT_LOAD_PROPERTIES, "")));
//
//        fail();
//    }
//
//
//    @Test()
//    public void testClasspathLoader(){
//
//        final String uri = "classpath:resources/jdbc.properties";
//
//        assertTrue("Wrong loader for classpath",
//                plFactory.getLoader(uri).get().getClass().isAssignableFrom(ClasspathPropertiesLoader.class));
//
//    }
//
//    @Test()
//    public void testFileSystemLoader(){
//
//        final String uri = "file:///tmp/aws.properties";
//
//        assertTrue("Wrong loader for file system",
//                plFactory.getLoader(uri).get().getClass().isAssignableFrom(FileSystemPropertiesLoader.class));
//
//    }
//
//    @Test()
//    public void testHttpLoader(){
//
//        final String uri = "http://localhost/global/config.json";
//
//        assertTrue("Wrong loader for file system",
//                plFactory.getLoader(uri).get().getClass().isAssignableFrom(HttpPropertiesLoader.class));
//
//    }
//
//

}
