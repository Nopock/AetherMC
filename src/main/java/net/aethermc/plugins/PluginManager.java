package net.aethermc.plugins;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PluginManager {

    public static final ConcurrentMap<String, RegisteredPlugin> registeredPlugins = new ConcurrentHashMap<>();
}
