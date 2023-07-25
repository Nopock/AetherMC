package net.aethermc.plugins;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

public class PluginLogger {

    public static final Logger logger = Logger.getLogger("Aether");

    private final String LOGGER_FORMAT = "[%s] %s";
    private final AetherPlugin plugin;

    public PluginLogger(AetherPlugin plugin) {
        this.plugin = plugin;
    }

//    public void log(Level level, String str) {
//        // TODO: Fix This
//        String format = String.format(LOGGER_FORMAT, plugin.getName(), str);
//        switch (level) {
//            case INFO -> logger.info(format);
//            case WARNING -> logger.warning(format);
//            case SEVERE -> logger.severe(format);
//            case CONFIG -> logger.config(format);
//            case FINE -> logger.fine(format);
//            case FINER -> logger.finer(format);
//            case FINEST -> logger.finest(format);
//            default -> logger.info(format);
//        }
//    }
}
