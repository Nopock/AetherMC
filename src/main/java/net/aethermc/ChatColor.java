package net.aethermc;

import net.kyori.adventure.text.Component;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AetherMC(since = "1.0.0")
public enum ChatColor {
    BLACK('0'   ),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    OBFUSCATED('k', true),
    BOLD('l', true),
    STRIKETHROUGH('m', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    RESET('r');

    public static final char COLOR_CHAR = '§';
    public static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    public static final Pattern STRIP_PATTERN = Pattern.compile("(?i)" + COLOR_CHAR + "[0-9A-FK-OR]");
    public static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    private final char code;
    private final boolean isFormat;
    private final String toString;

    ChatColor(char code) {
        this(code, false);
    }

    ChatColor(char code, boolean isFormat) {
        this.code = code;
        this.isFormat = isFormat;
        this.toString = new String(new char[] {COLOR_CHAR, code});
    }

    public boolean isColor() {
        return !isFormat && this != RESET;
    }

    public char getCode() {
        return code;
    }

    public boolean isFormat() {
        return isFormat;
    }

    public String getToString() {
        return toString;
    }

    public static ChatColor getCharColor(char color) {
        for (ChatColor color1 : ChatColor.values()) {
            if (color1.getCode() == color) return color1;
        }
        return null;
    }

    public static String color(@NotNull String string) {
        return string.replace('&', COLOR_CHAR);
    }

    public static String colorHex(@NotNull String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        while (matcher.find()) {
            String hexString = matcher.group(1);
            string = string.replace(matcher.group(0), COLOR_CHAR + "x" + COLOR_CHAR + hexString);
        }
        return color(string);
    }

    public static String stripColor(@NotNull String string) {
        return STRIP_PATTERN.matcher(color(string)).replaceAll("");
    }

    public static ChatColor getChatColor(@NotNull String string) {
        return ChatColor.valueOf(string.toUpperCase());
    }

    public static String of(String hex) {
        // TODO: Finish this before snapshot.3
        StringBuilder builder = new StringBuilder();
        char[] strChars = hex.toCharArray();
        for (char c : HEX_CHARS) {

        }
        return builder.toString();
    }

    public static Component toComponent(@NotNull String string) {
        return Component.text(string);
    }
}
