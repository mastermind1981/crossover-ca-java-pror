package com.crossover.trial.properties.parsers;

import com.amazonaws.regions.Regions;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Created by thiago on 12/28/15.
 */
public class PropTypeMapper {

    private static Map<String, Function<String, Boolean>> converters = new ConcurrentHashMap<>();

    static{
        converters.put("aws_access_key", v -> v != null && Pattern.compile("^[A-Z]+$").matcher(v).find());
        converters.put("aws_secret_key", PropTypeMapper::isNonEmptyString);
        converters.put("aws_account_id", PropTypeMapper::isInteger);
        converters.put("aws_region_id", PropTypeMapper::isAwsRegion);

        converters.put("auth.endpoint.uri", PropTypeMapper::isValidURL);
        converters.put("job.timeout", PropTypeMapper::isInteger);
        converters.put("sns.broadcast.topic_name", PropTypeMapper::isNonEmptyString);
        converters.put("score.factor", PropTypeMapper::isDouble);
        converters.put("jpa.showSql", PropTypeMapper::isBoolean);

        converters.put("JDBC_DRIVER", PropTypeMapper::isValidJavaIdentifier);



//        JDBC_DRIVER=com.mysql.jdbc.Driver
//        JDBC_URL=jdbc:mysql://localhost/test
//        JDBC_USERNAME=username123
//        JDBC_PASSWORD=password123
//        JPA_SHOWSQL=true

//                "jpa.showSql": false

    }

    private static final Pattern validJavaIdentifier = Pattern
            .compile("(\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*\\.)*\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*");


    private static Boolean isBoolean(String s) {
        return Boolean.parseBoolean(s) || !Boolean.parseBoolean(s);
    }

    private static Boolean isDouble(String s) {
        return new DoubleValidator().isValid(s);
    }

    private static Boolean isNonEmptyString(String s) {
        return s != null && !s.isEmpty();
    }

    public Function<String, Boolean> getConverterFunction(String key){
        return converters.getOrDefault(key, v -> true);
    }

    private static boolean isAwsRegion(String v){
        try{
            return v != null && Regions.fromName(v) != null;
        }catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isValidURL(String url){
        return new UrlValidator(new String[]{"http","https"}).isValid(url);
    }

    private static boolean isInteger(String i){
        return new IntegerValidator().isValid(i);
    }

    private static boolean isValidJavaIdentifier(String i){
        return validJavaIdentifier.matcher(i).matches();
    }


}
