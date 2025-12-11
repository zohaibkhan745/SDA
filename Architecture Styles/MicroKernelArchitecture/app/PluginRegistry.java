package app;

import java.util.HashMap;
import java.util.Map;

public class PluginRegistry {
    private static Map<String, String> plugins = new HashMap<>();

    static {
        plugins.put("SpellCheck", "plugins.SpellCheckPlugin");
        plugins.put("WordCount", "plugins.WordCountPlugin");
    }

    public static Map<String, String> getPlugins() { 
        return plugins; 
    }
    
    
    public static plugins.Plugin createPlugin(String name) {
        String className = plugins.get(name);
        if (className == null) {
            return null;
        }
        
        switch (className) {
            case "plugins.SpellCheckPlugin":
                return new plugins.SpellCheckPlugin();
            case "plugins.WordCountPlugin":
                return new plugins.WordCountPlugin();
            default:
                return null;
        }
    }
}
