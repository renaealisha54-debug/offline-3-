// HashMapExample.java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        // Add entries
        map.put("lines", 150);
        map.put("errors", 0);
        map.put("executions", 42);
        map.put("characters", 2450);

        // Iterate
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Get value
        int execCount = map.getOrDefault("executions", 0);
        System.out.println("Executions: " + execCount);
    }
}
