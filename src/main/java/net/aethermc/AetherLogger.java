package net.aethermc;

import org.jetbrains.annotations.ApiStatus;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

@ApiStatus.Experimental
public class AetherLogger {

    public static final DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final String LOGGER_FORMAT = "%s[%s] %s: %s";

    private String getFormattedTime() {
        return dataFormatter.format(LocalDateTime.now());
    }

    public void log(Level level, String log) {
        System.out.println(String.format(LOGGER_FORMAT, level.getColor(), getFormattedTime(), level.toString(), log));
    }

    /**
     * Logger levels with ANSI color codes
     */
    public enum Level {
        INFO("\033[0m"),
        CONFIG("\033[0m"),
        WARNING("\033[0;31m"),
        SEVERE("\033[1;31m");

        private final String color;

        Level(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return name();
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
