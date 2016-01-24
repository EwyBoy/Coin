package com.ewyboy.coin.Events;

import com.ewyboy.coin.Economics.PlayerWallet;
import com.ewyboy.coin.Loaders.ConfigLoader;
import com.ewyboy.coin.Util.Logger;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CoinEventHandler {

    PlayerWallet playerWallet = new PlayerWallet();

    @SubscribeEvent
    public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.getEntityData().getBoolean(Reference.NBT.HASPLAYERJOINEDBEFORE)) {
            playerWallet.setMoney(100, event.player);
            Logger.info("This is a new player");
            event.player.getEntityData().setBoolean(Reference.NBT.HASPLAYERJOINEDBEFORE, true);
        } else {
            playerWallet.setMoney(playerWallet.getPlayerWalletAmount(event.player),event.player);
            playerWallet.syncPlayerWalletToNBT(event.player);
            Logger.info("This is an old player");
        }
        if (ConfigLoader.debugMode) playerWallet.getPlayerWalletAmount(event.player);
    }

    @SubscribeEvent
    public void playerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        playerWallet.syncPlayerWalletToNBT(event.player);
    }
}
