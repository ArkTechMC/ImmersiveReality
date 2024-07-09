package com.iafenvoy.rlcraft.mixin;

import com.iafenvoy.rlcraft.IconStorage;
import net.minecraft.client.util.Icons;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;

@Mixin(Icons.class)
public class IconsMixin {
    @Inject(method = "getIcon", at = @At("HEAD"), cancellable = true)
    private void returnBetterFile(ResourcePack resourcePack, String fileName, CallbackInfoReturnable<InputSupplier<InputStream>> cir) {
        final InputStream stream = IconStorage.getOrNull(fileName);
        if (stream != null)
            cir.setReturnValue(() -> stream);
    }
}
