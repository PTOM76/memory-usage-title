package net.pitan76.memoryusagetitle.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.memoryusagetitle.MemoryUsageTitle;

@Mod(MemoryUsageTitle.MOD_ID)
public class MemoryUsageTitleForge {
    public MemoryUsageTitleForge() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    public void onClientSetup(final FMLClientSetupEvent e) {
        MemoryUsageTitle.init();
    }
}