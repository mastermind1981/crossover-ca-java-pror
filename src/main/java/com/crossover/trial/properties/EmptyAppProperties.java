package com.crossover.trial.properties;

import java.util.List;

/**
 * Created by thiago-rs on 12/28/15.
 */
public class EmptyAppProperties implements AppProperties{

    @Override
    public List<String> getMissingProperties() {
        return null;
    }

    @Override
    public List<?> getKnownProperties() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public AppProperties concat(AppProperties that) {
        return null;
    }
}
