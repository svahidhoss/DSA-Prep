package main.java.pramp;

import java.util.HashMap;
import java.util.Map;

public class FlattenDictionary {
    static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
        // your code goes here
        HashMap<String, String> result = new HashMap<>();
        helperFn("", dict, result);
        return result;
    }

    static void helperFn(String initialKey, HashMap<String, Object> dict, HashMap<String, String> result) {
        for (Map.Entry<String, Object> entry : dict.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String newKey = initialKey.isEmpty() ? key : initialKey + "." + key;
            // If the value type is HashMap, call the helper fn recursively on it
            if (value instanceof HashMap) {
                helperFn(newKey, (HashMap<String, Object>) value, result);
            } else {
                // Base case: value is a String, concatenate it to the result
                result.put(newKey, (String) value);
            }
        }
    }
}
