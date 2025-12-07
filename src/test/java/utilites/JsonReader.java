package utilites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    public static String readJsonFileAsString(String relativePath) {
        // relativePath like "src/test/resources/payloads/testdata.json"
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(relativePath));
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + relativePath, e);
        }
    }
}
