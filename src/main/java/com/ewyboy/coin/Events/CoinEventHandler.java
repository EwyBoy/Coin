package com.ewyboy.coin.Events;

import com.ewyboy.coin.Economics.PlayerWallet;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.nbt.NBTTagCompound;

import static com.ewyboy.coin.Util.Reference.NBT;

public class CoinEventHandler {

    PlayerWallet playerWallet = new PlayerWallet();

    @SubscribeEvent
    public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        String playerName = event.player.getDisplayName();
        NBTTagCompound playerNBT = event.player.getEntityData();

        if (!playerNBT.getBoolean(NBT.HASPLAYERJOINEDBEFORE)) {
            playerWallet.setMoney(0, event.player);
            playerNBT.setBoolean(NBT.HASPLAYERJOINEDBEFORE, true);
            playerNBT.setString(NBT.USERNAME, playerName);
        } else {
            playerWallet.setMoney(playerWallet.getPlayerWalletAmount(event.player), event.player);
            playerWallet.syncPlayerWalletToNBT(event.player);
        }
        playerNBT.setBoolean(NBT.ISPLAYERONLINE + playerName, true);
    }

    @SubscribeEvent
    public void playerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        String playerName = event.player.getDisplayName();
        NBTTagCompound playerNBT = event.player.getEntityData();

        playerWallet.syncPlayerWalletToNBT(event.player);
        playerNBT.setBoolean(NBT.ISPLAYERONLINE + playerName, false);
    }
}
