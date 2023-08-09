package net.aethermc;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 * {@snippet :
 *     public static void main(String[] args) {
 *         Aether.getLogger().log(AetherLogger.Level.INFO, "Hello World!");
 *     }
 * }
 */
@AetherMC(since = "1.0.0")
@ApiStatus.Experimental
public class AetherLogger {

    public static final DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final String LOGGER_FORMAT = "%s[%s] %s: %s";

    private String prefix;

    public AetherLogger(String prefix) {
        this.prefix = prefix;
    }

    private String getFormattedTime() {
        return dataFormatter.format(LocalDateTime.now());
    }

    public void log(Level level, String log) {
        System.out.println(String.format(LOGGER_FORMAT + "\033[0m", level.getColor(), getFormattedTime(), level, log));
    }

    public void info(String log) {
        log(Level.INFO, log);
    }

    public void warning(String log) {
        log(Level.WARNING, log);
    }

    public void severe(String log) {
        log(Level.SEVERE, log);
    }

    public void config(String log) {
        log(Level.CONFIG, log);
    }

    public void success(String log) {
        log(Level.SUCCESS, log);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Logger levels with ANSI color codes
     */
    public enum Level {
        INFO("\033[0m"),
        CONFIG("\033[0m"),
        WARNING("\033[0;31m"),
        SUCCESS("\033[0;32m"),
        SEVERE("\033[1;31m");

        private final String color;

        Level(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    static class SaveLog {

        public static File getLogFile() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            Date date = new Date();
            return new File("logs/" + dateFormat.format(date) + ".log");
        }

        public static void saveLogToFile(File file) {
            try (FileWriter writer = new FileWriter(file)) {
                StringBuilder logger = new StringBuilder();
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine())
                    logger.append(scanner.nextLine()).append("\n");

                writer.write(logger.toString());
                Aether.getLogger().log(Level.INFO, "Successfully saved logs to " + file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
