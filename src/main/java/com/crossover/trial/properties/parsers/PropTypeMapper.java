package com.crossover.trial.properties.parsers;

import com.amazonaws.regions.Regions;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Maps each property to a type using a validation function
 * Created by thiago on 12/28/15.
 */
public class PropTypeMapper {

    private static Map<String, Function<String, Optional<Object>>> converters = new ConcurrentHashMap<>();

    static {
        converters.put("aws_access_key", PropTypeMapper::isUpperCase);
        converters.put("aws_secret_key", PropTypeMapper::isNonEmptyString);
        converters.put("aws_account_id", PropTypeMapper::toInteger);
        converters.put("aws_region_id", PropTypeMapper::toAwsRegion);

        converters.put("auth.endpoint.uri", PropTypeMapper::isValidURL);
        converters.put("job.timeout", PropTypeMapper::toDouble);
        converters.put("sns.broadcast.topic_name", PropTypeMapper::isNonEmptyString);
        converters.put("score.factor", PropTypeMapper::toDouble);
        converters.put("jpa.showSql", PropTypeMapper::toBoolean);

        converters.put("JDBC_DRIVER", PropTypeMapper::isValidJavaIdentifier);
        converters.put("JDBC_URL", PropTypeMapper::isValidJDBCURL);
        converters.put("JDBC_USERNAME", PropTypeMapper::isNonEmptyString);
        converters.put("JDBC_PASSWORD", PropTypeMapper::isNonEmptyString);
        converters.put("JPA_SHOWSQL", PropTypeMapper::toBoolean);
    }

    /**
     * Returns the validation function to a given property
     * or the value itself if no function is found
     * @param key property key
     * @return Function<String, Optional<Object>> - validation function
     */
    public static Function<String, Optional<Object>> getConverterFunction(String key) {
        return converters.getOrDefault(key, v -> Optional.of(v));
    }

    private static final Pattern validJavaIdentifier = Pattern
            .compile("(\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*\\.)*\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*");

    private static Optional<Object> isUpperCase(String s) {
        return s != null && Pattern.compile("^[A-Z0-9]+$").matcher(s).matches() ?
                Optional.of(s) : Optional.of(String.class);
    }


    private static Optional<Object> toBoolean(String s) {
        return Boolean.parseBoolean(s) || !Boolean.parseBoolean(s) ?
                Optional.of(Boolean.parseBoolean(s)) : Optional.of(String.class);
    }

    private static Optional<Object> toDouble(String s) {
        return new DoubleValidator().isValid(s) ?
                Optional.of(Double.valueOf(s)) : Optional.of(Double.class);
    }

    private static Optional<Object> isNonEmptyString(String s) {
        return s != null && !s.isEmpty() ?
                Optional.of(s) : Optional.of(String.class);
    }


    private static Optional<Object> toAwsRegion(String v) {
        try {
            return Optional.of(Regions.fromName(v));
        } catch (IllegalArgumentException e) {
            return Optional.of(Regions.class);
        }
    }

    private static Optional<Object> isValidURL(String url) {
        return new UrlValidator(new String[]{"http", "https"},
                UrlValidator.ALLOW_LOCAL_URLS).isValid(url) ?
                Optional.of(url) : Optional.empty();
    }

    private static Optional<Object> isValidJDBCURL(String url) {
        return url != null && url.startsWith("jdbc") ?
                Optional.of(url) : Optional.of(String.class);
    }


    private static Optional<Object> toInteger(String i) {
        return new IntegerValidator().isValid(i) ?
                Optional.of(Integer.parseInt(i)) : Optional.of(Integer.class);
    }

    private static Optional<Object> isValidJavaIdentifier(String i) {
        return validJavaIdentifier.matcher(i).matches() ?
                Optional.of(i) : Optional.of(String.class);
    }

}
