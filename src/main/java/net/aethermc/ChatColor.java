package net.aethermc;

public class ChatColor {

    public static String color(String string) {
        return string.replaceAll("&", "§");
    }
}
