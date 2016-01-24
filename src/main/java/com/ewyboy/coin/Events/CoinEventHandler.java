package com.ewyboy.coin.Events;

import com.ewyboy.coin.Economics.PlayerWallet;
import com.ewyboy.coin.Loaders.ConfigLoader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

import static com.ewyboy.coin.Util.Reference.NBT;

public class CoinEventHandler {

    PlayerWallet playerWallet = new PlayerWallet();

    @SubscribeEvent
    public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        String playerName = event.player.getDisplayName();

        if (!event.player.getEntityData().getBoolean(NBT.HASPLAYERJOINEDBEFORE)) {
            playerWallet.setMoney(0, event.player);
            event.player.getEntityData().setBoolean(NBT.HASPLAYERJOINEDBEFORE, true);
            event.player.getEntityData().setString(NBT.USERNAME, playerName);
        } else {
            playerWallet.setMoney(playerWallet.getPlayerWalletAmount(event.player), event.player);
            playerWallet.syncPlayerWalletToNBT(event.player);
        }
        event.player.getEntityData().setBoolean(NBT.ISPLAYERONLINE + playerName, true);
        if (ConfigLoader.debugMode) playerWallet.getPlayerWalletAmount(event.player);
    }

    @SubscribeEvent
    public void playerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        String playerName = event.player.getDisplayName();

        playerWallet.syncPlayerWalletToNBT(event.player);
        event.player.getEntityData().setBoolean(NBT.ISPLAYERONLINE + playerName, false);
    }
}
