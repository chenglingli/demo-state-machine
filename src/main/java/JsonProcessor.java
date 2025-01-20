import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonProcessor {
    public static Map<String, JSONObject> processJson(Map<String, String> data) {
        // Step 1: Unescape and parse nested JSON strings, and delete unrelated kv pairs
        Map<String, JSONObject> processedData = new HashMap<>();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (Character.isDigit(key.charAt(0))) {
                processedData.put(key, new JSONObject(value));
            }
        }

        return processedData;
    }

    public static void main(String[] args) {
        // Example usage
        Map<String, String> data = new HashMap<>();
        data.put("1key", "{\"nestedKey\": \"nestedValue\"}");
        data.put("nonNumericKey", "{\"ignoreThis\": \"yes\"}");

        Map<String, JSONObject> result = processJson(data);
        System.out.println(result);
    }
}
