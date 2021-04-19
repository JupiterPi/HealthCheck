package jupiterpi.healthcheck;

import jupiterpi.tools.files.Path;
import jupiterpi.tools.files.TextFile;

import java.util.HashMap;
import java.util.Map;

public class ResourcesFile {
    private static final Path resourcesFile = Path.getRunningDirectory().file("res");

    private static Map<String, String> fields;

    static {
        fields = new HashMap<>();
        TextFile file = new TextFile(resourcesFile);
        for (String line : file.getFile()) {
            if (!line.isEmpty()) {
                try {
                    String[] parts = line.split(": ");
                    fields.put(parts[0], parts[1]);
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }

    public static String get(String key) {
        return fields.get(key);
    }
}