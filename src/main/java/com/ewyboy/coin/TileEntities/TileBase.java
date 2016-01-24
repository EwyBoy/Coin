package com.ewyboy.coin.TileEntities;

import com.ewyboy.coin.Util.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileBase extends TileEntity {

    protected ForgeDirection orientation;
    protected String customName;

    public TileBase() {
        orientation = ForgeDirection.NORTH;
    }

    public ForgeDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation) {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        if (nbtTagCompound.hasKey((Reference.NBT.DIRECTION))) {
            this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte((Reference.NBT.DIRECTION)));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setByte(Reference.NBT.DIRECTION, (byte) orientation.ordinal());
    }
}
