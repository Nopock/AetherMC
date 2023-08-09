package net.aethermc.plugins;

import kotlin.Pair;
import net.aethermc.plugins.AetherPlugin;
import net.aethermc.plugins.Plugin;
import net.aethermc.plugins.loaders.AetherPluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PluginSort {

    private final List<AetherPlugin> pluginsList;

    public PluginSort(@NotNull List<AetherPlugin> pluginsList) {
        this.pluginsList = pluginsList;
    }

    /**
     * Calculates the weight of a plugin (depends on dependencies and soft dependencies)
     *
     * @param plugin
     * @return
     */
    public double calculatePluginWeight(AetherPlugin plugin) {
        double weight = plugin.getDependencies().size() + 0.5 * plugin.getSoftDependencies().size();
        for (String depName : plugin.getDependencies()) {
            for (AetherPlugin otherPlugin : pluginsList) {
                if (!otherPlugin.getName().equals(depName)) continue;

                weight += calculatePluginWeight(otherPlugin);
            }
        }
        return weight;
    }

    /**
     * Sorts the whole list of plugins by weight
     *
     * @return
     */
    public List<AetherPlugin> sortPluginsByWeight() {
        pluginsList.removeIf(plugin -> plugin.getDependencies().stream().anyMatch(dep -> pluginsList.stream().noneMatch(p -> p.getName().equals(dep))));

        for (AetherPlugin plugin : pluginsList)
            plugin.setWeight(calculatePluginWeight(plugin));
        quickSort(0, pluginsList.size() - 1);

        return pluginsList;
    }

    /**
     * Partitioning for quick sort
     *
     * @param begin
     * @param end
     * @return
     */
    public int partition(int begin, int end) {
        int pivot = end;
        int counter = begin;

        for (int i = begin; i < end; i++) {
            if (pluginsList.get(i).getWeight() > pluginsList.get(pivot).getWeight()) {
                AetherPlugin temp = pluginsList.get(counter);
                pluginsList.set(counter, pluginsList.get(i));
                pluginsList.set(i, temp);
                counter++;
            }
        }

        AetherPlugin temp = pluginsList.get(pivot);
        pluginsList.set(pivot, pluginsList.get(counter));
        pluginsList.set(counter, temp);

        return counter;
    }

    /**
     * Quick sort algorithm
     *
     * @param begin
     * @param end
     */
    public void quickSort(int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(begin, end);
        quickSort(begin, pivot - 1);
        quickSort(pivot + 1, end);
    }
}
