package net.aethermc.json;

import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonReader {

    public static final Gson gson = new Gson();

    public static Collection<JsonObject> readFromFile(Path path) {
        Collection<JsonObject> jsonObjects = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(reader);
            JsonArray array = element.getAsJsonArray();

            for (JsonElement jsonElement : array) {
                jsonObjects.add(jsonElement.getAsJsonObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObjects;
    }

    public static Gson getGson() {
        return gson;
    }
}
