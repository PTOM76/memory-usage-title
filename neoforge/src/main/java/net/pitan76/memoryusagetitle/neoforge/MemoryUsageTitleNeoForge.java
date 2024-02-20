package net.pitan76.memoryusagetitle.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.memoryusagetitle.MemoryUsageTitle;

@Mod(MemoryUsageTitle.MOD_ID)
public class MemoryUsageTitleNeoForge {
    public MemoryUsageTitleNeoForge(IEventBus modEventBus) {
        modEventBus.addListener(this::onClientSetup);
    }

    public void onClientSetup(final FMLClientSetupEvent e) {
        MemoryUsageTitle.init();
    }
}