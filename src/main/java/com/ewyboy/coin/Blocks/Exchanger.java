package com.ewyboy.coin.Blocks;

import com.ewyboy.coin.Items.ItemCoin;
import com.ewyboy.coin.Loaders.CoinLoader;
import com.ewyboy.coin.TileEntities.TileExchanger;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Exchanger extends BaseBlock implements ITileEntityProvider {

    public Exchanger() {
        super(Material.iron, Reference.BlockNames.blockExchangerName);
        GameRegistry.registerTileEntity(TileExchanger.class, Reference.BlockNames.blockExchangerName);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (player.getHeldItem() == null) return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
            if (player.inventory.getCurrentItem().getItem() instanceof ItemCoin) {
                ItemCoin itemCoin = (ItemCoin) player.inventory.getCurrentItem().getItem();
                if (side==1) {
                    switch (itemCoin.getCoinValue()) {
                        case 1: exchangeUp(player, world, x,y,z, 1);
                            break;
                        case 10: exchangeUp(player, world, x,y,z, 2);
                            break;
                        case 100: exchangeUp(player, world, x,y,z, 3);
                            break;
                        case 1000: exchangeUp(player, world, x,y,z, 4);
                            break;
                    }
                } else {
                    switch (itemCoin.getCoinValue()) {
                        case 10: exchangeDown(player, world, x,y,z, 0);
                            break;
                        case 100: exchangeDown(player, world, x,y,z, 1);
                            break;
                        case 1000: exchangeDown(player, world, x,y,z, 2);
                            break;
                        case 10000: exchangeDown(player, world, x,y,z, 3);
                            break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void exchangeUp(EntityPlayer player, World world, int x, int y, int z, int exchangedCoinType) {
        ItemStack coin = new ItemStack(CoinLoader.ItemCoins[exchangedCoinType]);
        boolean canExchange = false;

        if(player.getHeldItem().stackSize >= 10) {
            player.getHeldItem().stackSize-=10;
            canExchange = true;
        }
        if (canExchange) {
            if (!player.inventory.addItemStackToInventory(coin)) {
                EntityItem entityItem = new EntityItem(world, x + 0.5, y + 1.25, z + 0.5, coin);
                world.spawnEntityInWorld(entityItem);
            } else {
                player.openContainer.detectAndSendChanges();
            }
        }
    }

    public void exchangeDown(EntityPlayer player, World world, int x, int y, int z, int exchangedCoinType) {
        ItemStack coin = new ItemStack(CoinLoader.ItemCoins[exchangedCoinType]);
        boolean canExchange = false;

        if (player.getHeldItem().stackSize >= 1) {
            player.getHeldItem().stackSize--;
            canExchange = true;
        }
        if (canExchange) {
            if (!player.inventory.addItemStackToInventory(coin.splitStack(10))) {
                EntityItem entityItem = new EntityItem(world, x + 0.5, y + 1.25, z + 0.5, coin.splitStack(10));
                world.spawnEntityInWorld(entityItem);
            } else {
                player.openContainer.detectAndSendChanges();
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileExchanger();
    }
}
