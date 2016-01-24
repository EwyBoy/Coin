package com.ewyboy.coin.Loaders;

import com.ewyboy.coin.Items.ItemCoin;
import com.ewyboy.coin.Util.Logger;
import com.ewyboy.coin.Util.Reference;
import com.google.common.base.Stopwatch;
import net.minecraft.item.Item;

import java.util.concurrent.TimeUnit;

public class CoinLoader {

    public static Item ItemCoinCopper, ItemCoinBronze, ItemCoinSilver, ItemCoinGold, ItemCoinPlatinum;
    public static final Item[] ItemCoins = {ItemCoinCopper, ItemCoinBronze, ItemCoinSilver, ItemCoinGold, ItemCoinPlatinum};

    public static void log(String item) {Logger.info("  " + item + " successfully loaded");}

    public static void loadCoins() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading tools started");
                for (int i = 0; i < Reference.CoinProperties.coinNames.length; i++) {
                    ItemCoins[i] = new ItemCoin(Reference.CoinProperties.coinNames[i], Reference.CoinProperties.coinValues[i]);
                    log(Reference.CoinProperties.coinNames[i]);
                }
        Logger.info("Loading tools finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
