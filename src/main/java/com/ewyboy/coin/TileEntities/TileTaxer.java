package com.ewyboy.coin.TileEntities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.TileEnergyHandler;
import com.ewyboy.coin.Util.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileTaxer extends TileEnergyHandler implements IEnergyHandler {

    private EnergyStorage energy;

    public TileTaxer(int capacity, int transfer) {
        energy = new EnergyStorage(capacity, transfer);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        energy.setEnergyStored(nbt.getInteger(Reference.NBT.ENERGYSTORED));
        energy.setCapacity(nbt.getInteger(Reference.NBT.CAPACITY));
        energy.setMaxTransfer(nbt.getInteger(Reference.NBT.TRANSFER));
        energy.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger(Reference.NBT.ENERGYSTORED, energy.getEnergyStored());
        nbt.setInteger(Reference.NBT.CAPACITY, energy.getMaxEnergyStored());
        nbt.setInteger(Reference.NBT.TRANSFER, energy.getMaxExtract());
        energy.writeToNBT(nbt);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if (from != ForgeDirection.UP) {
            return 0;
        }
        return energy.receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        if (from != ForgeDirection.DOWN) {
            return 0;
        }
        return energy.extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return energy.getMaxEnergyStored();
    }
}
