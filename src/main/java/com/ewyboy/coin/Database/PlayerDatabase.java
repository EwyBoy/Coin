package com.ewyboy.coin.Database;

import com.ewyboy.coin.Util.Reference;
import com.sun.org.apache.xpath.internal.operations.String;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerDatabase {

    public boolean isPlayerOnline(String playerName, EntityPlayer player) {
        return player.getEntityData().getBoolean(Reference.NBT.ISPLAYERONLINE + playerName);
    }
}
