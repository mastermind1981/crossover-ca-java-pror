package com.crossover.trial.properties;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A dummy implementation of TrialAppProperties, this clearly doesn't work. Candidates SHOULD change
 * this class to add their implementation. You are also free to create additional classes
 * <p/>
 * note: a default constructor is required.
 *
 * @author code test administrator
 */
public class TrialAppProperties implements AppProperties {

    private Map<String, Object> props;

    public TrialAppProperties(Map<String, Object> props) {
        this.props = props;
    }

    @Override
    public List<String> getMissingProperties() {
        return props.entrySet()
                .stream()
                .filter(p -> p.getValue().getClass().isAssignableFrom(Class.class))
                .map(p -> p.getKey())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getKnownProperties() {
        return props.entrySet()
                .stream()
                .map(p -> p.getKey())
                .collect(Collectors.toList());
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
        return props.get(key);
    }

    @Override
    public AppProperties concat(AppProperties that) {
        final Map<String, Object> mThis = new HashMap<>(this.props);

        that.getKnownProperties().forEach(k -> {
            final String key = String.valueOf(k);
            mThis.put(key, that.get(key));
        });

        return new TrialAppProperties(mThis);
    }
}

