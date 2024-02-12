package net.pitan76.memoryusagetitle.fabric;

import net.pitan76.memoryusagetitle.MemoryUsageTitle;
import net.fabricmc.api.ModInitializer;

public class MemoryUsageTitleFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MemoryUsageTitle.init();
    }
}