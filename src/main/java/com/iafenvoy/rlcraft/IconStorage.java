package com.iafenvoy.rlcraft;

import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IconStorage {
    private static final List<String> MODIFIED_ICON = new ArrayList<>();

    @Nullable
    public static InputStream getOrNull(String name) {
        return MODIFIED_ICON.contains(name) ? IconStorage.class.getResourceAsStream("/icons/" + name) : null;
    }

    public static URL getUrl(String name) {
        return IconStorage.class.getResource("/icons/" + name);
    }

    static {
        MODIFIED_ICON.add("icon_16x16.png");
        MODIFIED_ICON.add("icon_32x32.png");
        MODIFIED_ICON.add("icon_48x48.png");
        MODIFIED_ICON.add("icon_128x128.png");
        MODIFIED_ICON.add("icon_256x256.png");
        MODIFIED_ICON.add("minecraft.icns");
    }
}
