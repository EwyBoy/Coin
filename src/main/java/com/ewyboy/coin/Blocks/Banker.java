package com.ewyboy.coin.Blocks;

import com.ewyboy.coin.TileEntities.TileBanker;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Banker extends BaseBlock implements ITileEntityProvider {

    public Banker() {
        super(Material.iron, Reference.BlockNames.blockBankerName);
        GameRegistry.registerTileEntity(TileBanker.class, Reference.BlockNames.blockBankerName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBanker();
    }
}
