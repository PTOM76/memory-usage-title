package net.pitan76.memoryusagetitle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class MemoryUsageTitle {
    public static final String MOD_ID = "memoryusagetitle";
    public static final String MOD_NAME = "MemoryUsageTitle";

    public static Identifier id(String id) {
        return new Identifier(MOD_ID, id);
    }

    public static void init() {
        // Check client
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            clientInit(client);
        } catch (Exception e) {
            return;
        }

    }

    public static void clientInit(MinecraftClient client) {

    }

    public static String getUsageString() {
        return "Memory Usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "MB / " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "MB";
    }
}