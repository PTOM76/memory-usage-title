package net.pitan76.memoryusagetitle.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.pitan76.memoryusagetitle.MemoryUsageTitle;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final private Window window;

    @Inject(method = "getWindowTitle", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> ci, StringBuilder stringBuilder) {
        MemoryUsageTitle.CACHE_TITLE = stringBuilder.toString();

        stringBuilder.append(" - ").append(MemoryUsageTitle.getUsageString());
        ci.setReturnValue(stringBuilder.toString());
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (window == null) return;
        if (MemoryUsageTitle.CACHE_TITLE.isEmpty()) return;

        window.setTitle(MemoryUsageTitle.CACHE_TITLE + " - " + MemoryUsageTitle.getUsageString());
    }
}
