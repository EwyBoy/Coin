package com.ewyboy.coin.Economics;

import com.ewyboy.coin.Loaders.ConfigLoader;
import com.ewyboy.coin.Util.Logger;
import net.minecraft.entity.player.EntityPlayer;

import static com.ewyboy.coin.Util.Reference.NBT;

public class PlayerWallet {

    private int playerWalletAmount;

    public boolean canTakeMoney(int amount) {
        return (playerWalletAmount - amount) <= 0;
    }

    public void setMoney(int amount, EntityPlayer player) {
        playerWalletAmount = amount;
        writePlayerWalletToNBT(player);
    }

    public void giveMoney(int amount, EntityPlayer player) {
        playerWalletAmount += amount;
        writePlayerWalletToNBT(player);
    }

    public void takeMoney(int amount, EntityPlayer player) {
        if (canTakeMoney(amount)) {
            playerWalletAmount -= amount;
            writePlayerWalletToNBT(player);
        }
    }

    public int getPlayerWalletAmount(EntityPlayer player) {
        if (ConfigLoader.debugMode) Logger.info("Player Wallet = " + playerWalletAmount);
        return player.getEntityData().getInteger(NBT.PLAYERWALLET);
    }

    public void writePlayerWalletToNBT(EntityPlayer player) {
        player.getEntityData().setInteger(NBT.PLAYERWALLET, playerWalletAmount);
    }

    public void syncPlayerWalletToNBT(EntityPlayer player) {
        if (playerWalletAmount != getPlayerWalletAmount(player)) writePlayerWalletToNBT(player);
    }
}
