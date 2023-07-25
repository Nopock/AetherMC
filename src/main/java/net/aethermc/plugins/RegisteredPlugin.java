package net.aethermc.plugins;

import net.aethermc.AetherMC;

@AetherMC(since = "1.0.0")
public class RegisteredPlugin {

    private final String name;
    private final String description;
    private final String version;
    private final String author;

    public RegisteredPlugin(String name, String description, String version, String author) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }
}
