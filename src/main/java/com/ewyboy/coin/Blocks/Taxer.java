package com.ewyboy.coin.Blocks;

import com.ewyboy.coin.TileEntities.TileTaxer;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Taxer extends BaseBlock implements ITileEntityProvider {

    public Taxer() {
        super(Material.iron, Reference.BlockNames.blockTaxerName);
        GameRegistry.registerTileEntity(TileTaxer.class, Reference.BlockNames.blockTaxerName);
    }

    private TileTaxer getTileEntity(World world, int x, int y, int z) {
        return (TileTaxer) world.getTileEntity(x,y,z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileTaxer tile = getTileEntity(world, x, y, z);
        if (!world.isRemote) {
            tile.processActivation(world, side);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTaxer();
    }
}
