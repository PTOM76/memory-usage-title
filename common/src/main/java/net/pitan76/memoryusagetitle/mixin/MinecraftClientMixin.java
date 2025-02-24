package net.pitan76.memoryusagetitle.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.pitan76.memoryusagetitle.MemoryUsageTitle;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;

@Mixin(value = MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Final private Window window;

    @Shadow protected abstract String getWindowTitle();

    @Unique
    private static boolean mut$flagGetWindowTitle = false;

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void mut$getWindowTitle(CallbackInfoReturnable<String> ci) {
        if (mut$flagGetWindowTitle) return;

        mut$flagGetWindowTitle = true;
        MemoryUsageTitle.CACHE_TITLE = getWindowTitle();
        mut$flagGetWindowTitle = false;

        ci.setReturnValue(MemoryUsageTitle.CACHE_TITLE + " - " + MemoryUsageTitle.getUsageString());
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void mut$tick(CallbackInfo ci) {
        if (window == null) return;
        if (MemoryUsageTitle.CACHE_TITLE.isEmpty()) return;

        window.setTitle(MemoryUsageTitle.CACHE_TITLE + " - " + MemoryUsageTitle.getUsageString());
    }
}
