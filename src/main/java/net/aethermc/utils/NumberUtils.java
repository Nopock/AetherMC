package net.aethermc.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class NumberUtils {


    private static final String[] FORMAT_SUFFIXES = {"", "K", "M", "B", "T"};
    private static final DecimalFormat DEFAULT_FORMAT = new DecimalFormat("#.##");
    private static final DecimalFormat REGEX_FORMAT = new DecimalFormat("#,###");

    public static String format(double number) {
        final StringBuilder builder = new StringBuilder();
        int magIndex = 0;
        while (number >= 1000 && magIndex < FORMAT_SUFFIXES.length - 1) {
            number /= 1000;
            magIndex++;
        }
        builder.append(DEFAULT_FORMAT.format(number)).append(FORMAT_SUFFIXES[magIndex]);
        return builder.toString();
    }

    public static String regex(double number) {
        return REGEX_FORMAT.format(number);
    }

    public static String percentage(double number, double total) {
        return DEFAULT_FORMAT.format(number / total * 100) + "%";
    }

    public static String scientific(double number) {
        return String.format("%E", number);
    }

    public static String binary(double number) {
        return Integer.toBinaryString((int) number);
    }

    public static String hex(double number) {
        return Integer.toHexString((int) number);
    }

    public static String octal(double number) {
        return Integer.toOctalString((int) number);
    }

    public static double getRandomDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static int getRandomInt(int min, int max) {
        return (int) Math.round(Math.random() * (max - min) + min);
    }

    public static long getRandomLong(long min, long max) {
        return (long) (Math.random() * (max - min) + min);
    }

    public static float getRandomFloat(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }

    public static short getRandomShort(short min, short max) {
        return (short) (Math.random() * (max - min) + min);
    }

    public static byte getRandomByte(byte min, byte max) {
        return (byte) (Math.random() * (max - min) + min);
    }
}
