package com.iafenvoy.rlcraft;

import com.iafenvoy.rlcraft.gui.PreLaunchWindow;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class RLCraftPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
            PreLaunchWindow.display();
    }
}
