package com.ewyboy.coin.Blocks;

import com.ewyboy.coin.TileEntities.TileMarket;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Market extends BaseBlock implements ITileEntityProvider {

    public Market() {
        super(Material.iron, Reference.BlockNames.blockMarketName);
        GameRegistry.registerTileEntity(TileMarket.class, Reference.BlockNames.blockMarketName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMarket();
    }
}
