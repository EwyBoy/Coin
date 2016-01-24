package com.ewyboy.coin.Loaders;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

import static com.ewyboy.coin.Util.Reference.*;

public class ConfigLoader {
    public static boolean debugMode;

    public static void init (File file) {
        Configuration config = new Configuration(file);

        config.load();
            debugMode = config.getBoolean("Debug Mode", ConfigCategoryNames.coinProperty, true, ConfigDescriptions.debugMode);
        config.save();
    }
}
