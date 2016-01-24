package com.ewyboy.coin.Database;

import com.ewyboy.coin.Util.Reference;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerDatabase {

    public boolean isPlayerOnline(EntityPlayer player) {
        return player.getEntityData().getBoolean(Reference.NBT.ISPLAYERONLINE + player.getDisplayName());
    }
}
