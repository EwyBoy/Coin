package com.ewyboy.coin.Waila;

import com.ewyboy.coin.Blocks.BaseBlock;
import com.ewyboy.coin.Util.Logger;
import com.google.common.base.Stopwatch;
import mcp.mobius.waila.api.IWailaRegistrar;

import java.util.concurrent.TimeUnit;

public class Waila {
    public static void onWailaCall(IWailaRegistrar registrar) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Found " + mcp.mobius.waila.Waila.instance);
            Logger.info("Loading Waila features");
                registrar.registerStackProvider(new WailaBlockBase(), BaseBlock.class);
                registrar.registerBodyProvider(new WailaBlockBase(), BaseBlock.class);
                registrar.registerNBTProvider(new WailaBlockBase(), BaseBlock.class);
        Logger.info("Waila features finished loading after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
