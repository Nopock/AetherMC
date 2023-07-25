package net.aethermc.plugins;

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
