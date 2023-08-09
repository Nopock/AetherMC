package net.aethermc.plugins;

import net.aethermc.AetherMC;

import java.util.List;

@AetherMC(since = "1.0.0-snapshot.4")
public class Plugin {

    private String name;
    private List<String> dependencies;
    private List<String> softDependencies;
    private double weight;

    private boolean isAsyncEnable = false;
    private boolean isAsyncDisable = false;

    public Plugin(String name, List<String> dependencies, List<String> softDependencies) {
        this.name = name;
        this.dependencies = dependencies;
        this.softDependencies = softDependencies;
        this.weight = 0;
    }

    public void onEnable() {}

    public void onDisable() {}

    public void onLoad() {}

    public void onReload() {}

    public String getName() {
        return name;
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

    public boolean isAsyncEnable() {
        return isAsyncEnable;
    }

    public boolean isAsyncDisable() {
        return isAsyncDisable;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    protected void setSoftDependencies(List<String> softDependencies) {
        this.softDependencies = softDependencies;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    protected void setAsyncEnable(boolean asyncEnable) {
        isAsyncEnable = asyncEnable;
    }

    protected void setAsyncDisable(boolean asyncDisable) {
        isAsyncDisable = asyncDisable;
    }
}
