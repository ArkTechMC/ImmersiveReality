package com.iafenvoy.rlcraft;

import com.iafenvoy.rlcraft.gui.PreLaunchWindow;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RLCraftPreLaunch implements PreLaunchEntrypoint {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onPreLaunch() {
        try (InputStream stream = RLCraftPreLaunch.class.getResourceAsStream("/splash.txt")) {
            assert stream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            bufferedReader.lines().forEach(LOGGER::info);
        } catch (IOException e) {
            LOGGER.error("Failed to print RLCraft splash: ", e);
        }
        if (isWindows() && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
            PreLaunchWindow.display();
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
