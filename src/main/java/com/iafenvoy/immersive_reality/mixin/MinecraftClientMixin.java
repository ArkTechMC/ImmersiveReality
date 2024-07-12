package com.iafenvoy.immersive_reality.mixin;

import com.iafenvoy.immersive_reality.gui.PreLaunchWindow;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Overlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    public abstract void updateWindowTitle();

    @Unique
    private boolean loaded = false;

    @ModifyReturnValue(method = "getWindowTitle", at = @At("RETURN"))
    private String addRlcName(String original) {
        return original + " | Immersive Reality" + (this.loaded ? "" : " Loading");
    }

    @Inject(method = "setOverlay", at = @At("RETURN"))
    private void onInitComplete(Overlay overlay, CallbackInfo ci) {
        if (overlay == null) {
            this.loaded = true;
            this.updateWindowTitle();
        } else PreLaunchWindow.remove();
    }
}
