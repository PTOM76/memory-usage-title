package net.pitan76.memoryusagetitle.mixin;

import net.minecraft.client.MinecraftClient;
import net.pitan76.memoryusagetitle.MemoryUsageTitle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "getWindowTitle", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> ci, StringBuilder stringBuilder) {
        stringBuilder.append(" - ").append(MemoryUsageTitle.getUsageString());
        ci.setReturnValue(stringBuilder.toString());
    }
}
