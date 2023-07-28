package net.aethermc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColor {

    static final Pattern pattern = Pattern.compile("&[0-9a-fA-F]");

    public static String color(String string) {
        return string.replaceAll("&", "§");
    }

    public static String decolor(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String group = matcher.group();
            string = string.replace(group, "");
        }
        return string;
    }
}
