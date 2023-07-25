package net.aethermc.plugins;

import net.aethermc.AetherMC;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

@AetherMC(since = "1.0.0")
public class PluginLogger {

    public static final Logger logger = Logger.getLogger("Aether");

    private final String LOGGER_FORMAT = "[%s] %s";
    private final AetherPlugin plugin;

    public PluginLogger(AetherPlugin plugin) {
        this.plugin = plugin;
    }

    private String getFormatted(String str) {
        return String.format(LOGGER_FORMAT, plugin.getName(), str);
    }

    public void info(String str) {
        logger.info(getFormatted(str));
    }

    public void warning(String str) {
        logger.warning(getFormatted(str));
    }

    public void severe(String str) {
        logger.severe(getFormatted(str));
    }

    public void config(String str) {
        logger.config(getFormatted(str));
    }

    public void fine(String str) {
        logger.fine(getFormatted(str));
    }

    public void finer(String str) {
        logger.finer(getFormatted(str));
    }

    public void finest(String str) {
        logger.finest(getFormatted(str));
    }
}
