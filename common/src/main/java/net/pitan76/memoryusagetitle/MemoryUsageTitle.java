package net.pitan76.memoryusagetitle;

public class MemoryUsageTitle {
    public static final String MOD_ID = "memoryusagetitle";

    public static String CACHE_TITLE = "";

    public static void init() {
    }

    public static String getUsageString() {
        long total = Runtime.getRuntime().totalMemory();
        long usedMB = (total - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        long totalMB = total / 1024 / 1024;

        return "Memory: " + String.format("%,d", usedMB) + "MB / " + String.format("%,d", totalMB) + "MB";
    }
}