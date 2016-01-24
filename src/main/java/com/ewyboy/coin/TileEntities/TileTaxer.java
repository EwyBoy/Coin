package com.ewyboy.coin.TileEntities;

import com.ewyboy.coin.Loaders.BlockLoader;
import com.ewyboy.coin.Util.Logger;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import static com.ewyboy.coin.Util.Reference.NBT;

public class TileTaxer extends TileBase {

    public boolean side0, side1, side2, side3, side4, side5;
    public boolean[] side = {side0, side1, side2, side3, side4, side5};

    public void processActivation(World world, int side) {
        switch (side) {
            case 0: side0 = true;
                break;
            case 1: side1 = true;
                break;
            case 2: side2 = true;
                break;
            case 3: side3 = true;
                break;
            case 4: side4 = true;
                break;
            case 5: side5 = true;
                break;
        }

        Logger.info("" +
                "Side0: " + side0 + " , " +
                "Side1: " + side1 + " , " +
                "Side2: " + side2 + " , " +
                "Side3: " + side3 + " , " +
                "Side4: " + side4 + " , " +
                "Side5: " + side5 + " , "
        );
        world.notifyBlockChange(xCoord,yCoord,zCoord, BlockLoader.Taxer);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        for (int i = 0; i < side.length; i++) {
            this.side[i] = nbt.getBoolean(NBT.SIDE + i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        for (int i = 0; i < side.length; i++) {
            nbt.setBoolean(NBT.SIDE + i, side[i]);
        }
    }
}
