package com.crossover.trial.properties;

import java.util.*;

/**
 * A dummy implementation of TrialAppProperties, this clearly doesn't work. Candidates SHOULD change 
 * this class to add their implementation. You are also free to create additional classes
 *
 * note: a default constructor is required.
 *
 * @author code test administrator
 */
public class TrialAppProperties implements AppProperties {

    private Map<String,Object> props;

    public TrialAppProperties(Map<String,Object> props) {
        this.props = props;
    }

    @Override
    public List<String> getMissingProperties() {
        return Collections.emptyList();
    }

    @Override
    public List<String> getKnownProperties() {
        return new ArrayList(props.keySet());
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(String key) {
        return "dummy";
    }

    @Override
    public AppProperties concat(AppProperties that) {
        return null;
    }
}

