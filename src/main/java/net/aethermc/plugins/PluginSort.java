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

    // for testing
    public static void main(String[] args) {
        List<Plugin> plugins = new ArrayList<>();
        plugins.add(new Plugin("plugin_a", List.of("plugin_b", "plugin_c"), List.of()));
        plugins.add(new Plugin("plugin_b", List.of("plugin_d"), List.of()));
        plugins.add(new Plugin("plugin_c", List.of("plugin_d"), List.of()));
        plugins.add(new Plugin("plugin_d", List.of(), List.of()));
        plugins.add(new Plugin("plugin_e", List.of(), List.of("plugin_b")));
        plugins.add(new Plugin("plugin_f", List.of("plugin_a", "plugin_g"), List.of()));
        plugins.add(new Plugin("plugin_g", List.of("plugin_e"), List.of()));
        plugins.add(new Plugin("plugin_h", List.of("plugin_i"), List.of()));
        plugins.add(new Plugin("plugin_i", List.of(), List.of()));
        plugins.add(new Plugin("plugin_j", List.of("plugin_k"), List.of()));
        plugins.add(new Plugin("plugin_k", List.of("plugin_l", "plugin_m"), List.of()));
        plugins.add(new Plugin("plugin_l", List.of("plugin_m", "plugin_n"), List.of()));
        plugins.add(new Plugin("plugin_m", List.of(), List.of()));
        plugins.add(new Plugin("plugin_n", List.of("plugin_o"), List.of()));
        plugins.add(new Plugin("plugin_o", List.of(), List.of()));
        plugins.add(new Plugin("plugin_p", List.of("plugin_q", "plugin_r"), List.of()));
        plugins.add(new Plugin("plugin_q", List.of(), List.of()));
        plugins.add(new Plugin("plugin_r", List.of(), List.of()));
        plugins.add(new Plugin("plugin_s", List.of("plugin_t"), List.of()));
        plugins.add(new Plugin("plugin_t", List.of(), List.of()));
        plugins.add(new Plugin("plugin_u", List.of("plugin_v"), List.of()));
        plugins.add(new Plugin("plugin_v", List.of("plugin_w"), List.of()));
        plugins.add(new Plugin("plugin_w", List.of(), List.of()));
        plugins.add(new Plugin("plugin_x", List.of("plugin_y"), List.of()));
        plugins.add(new Plugin("plugin_y", List.of(), List.of()));
        plugins.add(new Plugin("plugin_z", List.of("plugin_a"), List.of()));
        plugins.add(new Plugin("plugin_aa", List.of("plugin_bb", "plugin_cc"), List.of()));
        plugins.add(new Plugin("plugin_bb", List.of("plugin_dd"), List.of()));
        plugins.add(new Plugin("plugin_cc", List.of("plugin_dd"), List.of()));
        plugins.add(new Plugin("plugin_dd", List.of(), List.of()));
        plugins.add(new Plugin("plugin_ee", List.of(), List.of("plugin_bb")));
        plugins.add(new Plugin("plugin_ff", List.of("plugin_aa", "plugin_gg"), List.of()));
        plugins.add(new Plugin("plugin_gg", List.of("plugin_ee"), List.of()));
        plugins.add(new Plugin("plugin_hh", List.of("plugin_ii"), List.of()));
        plugins.add(new Plugin("plugin_ii", List.of(), List.of()));
        plugins.add(new Plugin("plugin_jj", List.of("plugin_kk"), List.of()));
        plugins.add(new Plugin("plugin_kk", List.of("plugin_ll", "plugin_mm"), List.of()));
        plugins.add(new Plugin("plugin_ll", List.of("plugin_mm", "plugin_nn"), List.of()));
        plugins.add(new Plugin("plugin_mm", List.of(), List.of()));
        plugins.add(new Plugin("plugin_nn", List.of("plugin_oo"), List.of()));
        plugins.add(new Plugin("plugin_oo", List.of(), List.of()));
        plugins.add(new Plugin("plugin_pp", List.of("plugin_qq", "plugin_rr"), List.of()));
        plugins.add(new Plugin("plugin_qq", List.of(), List.of()));
        plugins.add(new Plugin("plugin_rr", List.of(), List.of()));
        plugins.add(new Plugin("plugin_ss", List.of("plugin_tt"), List.of()));
        plugins.add(new Plugin("plugin_tt", List.of(), List.of()));
        plugins.add(new Plugin("plugin_uu", List.of("plugin_vv"), List.of()));
        plugins.add(new Plugin("plugin_vv", List.of("plugin_ww"), List.of()));
        plugins.add(new Plugin("plugin_ww", List.of(), List.of()));
        plugins.add(new Plugin("plugin_xx", List.of("plugin_yy"), List.of()));
        plugins.add(new Plugin("plugin_yy", List.of(), List.of()));
        plugins.add(new Plugin("plugin_zz", List.of("plugin_aa"), List.of()));

        long start = System.currentTimeMillis();
        List<Plugin> sortedPlugins = sortPluginsByWeight(plugins);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + "ms (Amount: " + sortedPlugins.size() + ")");
    }

    public static double calculatePluginWeight(Plugin plugin, List<Plugin> pluginsList) {
        double weight = plugin.getDependencies().size() + 0.5 * plugin.getSoftDependencies().size();
        for (String depName : plugin.getDependencies()) {
            for (Plugin otherPlugin : pluginsList) {
                if (!otherPlugin.getName().equals(depName)) continue;

                weight += calculatePluginWeight(otherPlugin, pluginsList);
            }
        }
        return weight;
    }

    public static List<Plugin> sortPluginsByWeight(List<Plugin> pluginsList) {
        // Use a HashSet to keep track of plugin names
        pluginsList.removeIf(plugin -> plugin.getDependencies().stream().anyMatch(dep -> pluginsList.stream().noneMatch(p -> p.getName().equals(dep))));

        // Calculate weights for each plugin
        for (Plugin plugin : pluginsList) {
            plugin.setWeight(calculatePluginWeight(plugin, pluginsList));
        }

        // Sort plugins based on weight in ascending order
        quickSort(pluginsList, 0, pluginsList.size() - 1);

        return pluginsList;
    }

    public static int partition(List<Plugin> pluginsList, int begin, int end) {
        int pivot = end;
        int counter = begin;

        for (int i = begin; i < end; i++) {
            if (pluginsList.get(i).getWeight() > pluginsList.get(pivot).getWeight()) {
                Plugin temp = pluginsList.get(counter);
                pluginsList.set(counter, pluginsList.get(i));
                pluginsList.set(i, temp);
                counter++;
            }
        }

        Plugin temp = pluginsList.get(pivot);
        pluginsList.set(pivot, pluginsList.get(counter));
        pluginsList.set(counter, temp);

        return counter;
    }

    public static void quickSort(List<Plugin> pluginsList, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(pluginsList, begin, end);
        quickSort(pluginsList, begin, pivot - 1);
        quickSort(pluginsList, pivot + 1, end);
    }
}
