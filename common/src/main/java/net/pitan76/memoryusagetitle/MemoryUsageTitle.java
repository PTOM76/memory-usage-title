package net.pitan76.memoryusagetitle;

public class MemoryUsageTitle {
    public static final String MOD_ID = "memoryusagetitle";

    public static String CACHE_TITLE = "";

    public static void init() {
    }

    public static String getUsageString() {
        long total = Runtime.getRuntime().totalMemory();
        long usedMB = (total - Runtime.getRuntime().freeMemory()) / 1024L / 1024L;
        long totalMB = total / 1024L / 1024L;

        return "Memory: " + String.format("%,d", usedMB) + "MB / " + String.format("%,d", totalMB) + "MB";
    }
}