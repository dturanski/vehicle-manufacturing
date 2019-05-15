package dturanski.manufacturing.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ArgUtils {

    public static Map<String, String> argsAsMap(List<String> args) {
        Map<String,String> properties = new HashMap<>();
        args.forEach(pair-> addKeyValuePair(pair, properties));
        return properties;
    }

    public static Map<String, String> argsAsMap(String[] args) {
        return argsAsMap(Arrays.asList(args));
    }

    private static void addKeyValuePair(String pair, Map<String, String> properties) {
        int firstEquals = pair.indexOf('=');
        if (firstEquals != -1) {
            properties.put(pair.substring(0, firstEquals).trim(), pair.substring(firstEquals + 1).trim());
        }
    }
}
