package com.ewyboy.coin.Loaders;

import com.ewyboy.coin.Blocks.*;
import net.minecraft.block.Block;

public class BlockLoader {

    public static Block Bank, Banker, Taxer, Exchanger, Market;

    public static void loadBlocks() {
        Bank = new Bank();
        Banker = new Banker();
        Taxer = new Taxer();
        Exchanger = new Exchanger();
        Market = new Market();
    }
}
