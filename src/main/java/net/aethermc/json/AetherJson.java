package net.aethermc.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AetherJson {

    public static InputStream getResource(@NotNull String resourcePath) {
        return AetherJson.class.getResourceAsStream(resourcePath);
    }

    public static JsonElement parseResourceAsJson(@NotNull String resourcePath) {
        JsonParser parser = new JsonParser();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResource(resourcePath)))) {
            return parser.parse(bufferedReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
