package net.aethermc.plugins;

import net.aethermc.AetherMC;
import net.aethermc.plugins.loaders.PluginClassLoader;
import net.aethermc.plugins.loaders.PluginLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@AetherMC(since = "1.0.0-snapshot.4")
public abstract class AetherPlugin extends Plugin {

    protected String name;
    protected String version;
    protected String author;
    protected String description;
    protected List<String> dependencies;
    protected List<String> softDependencies;
    protected double weight = 0D;
    protected boolean loadAsync = false;
    protected boolean unloadAsync = false;

    public boolean enabled = false;
    public boolean loaded = false;
    public PluginLogger logger;
    public PluginClassLoader classLoader;

    public AetherPlugin() {
    }

    public AetherPlugin(String name, String version, String author, String description) {
        this(name, version, author, description, new ArrayList<>(), new ArrayList<>());
    }

    public AetherPlugin(String name, String version, String author, String description, List<String> dependencies, List<String> softDependencies) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.description = description;
        this.dependencies = dependencies;
        this.softDependencies = softDependencies;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public PluginLogger getLogger() {
        return logger;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public List<String> getSoftDependencies() {
        return softDependencies;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isLoadAsync() {
        return loadAsync;
    }

    public boolean isUnloadAsync() {
        return unloadAsync;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public PluginClassLoader getClassLoader() {
        return classLoader;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setVersion(String version) {
        this.version = version;
    }

    protected void setAuthor(String author) {
        this.author = author;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setLogger(PluginLogger logger) {
        this.logger = logger;
    }

    protected void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    protected void setSoftDependencies(List<String> softDependencies) {
        this.softDependencies = softDependencies;
    }

    protected void setLoadAsync(boolean loadAsync) {
        this.loadAsync = loadAsync;
    }

    protected void setUnloadAsync(boolean unloadAsync) {
        this.unloadAsync = unloadAsync;
    }

    protected void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    protected void setClassLoader(PluginClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public File getDataFolder() {
        return new File("plugins/" + getName());
    }

    protected void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;

        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public void reload() {
        // TODO: Make a safe reload for plugin
        onReload();
    }

    public boolean hasDataFolder() {
        return getDataFolder().exists();
    }

    public void createDataFolder() {
        File dataFile = getDataFolder();
        if (dataFile.exists()) return;

        dataFile.mkdir();
    }

    public List<Class<?>> getClasses() {
        return classLoader.getClasses();
    }
}
