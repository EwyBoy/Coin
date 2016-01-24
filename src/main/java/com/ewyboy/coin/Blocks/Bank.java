package com.ewyboy.coin.Blocks;

import com.ewyboy.coin.Economics.PlayerWallet;
import com.ewyboy.coin.Items.ItemCoin;
import com.ewyboy.coin.Loaders.CoinLoader;
import com.ewyboy.coin.Proxies.ClientProxy;
import com.ewyboy.coin.TileEntities.TileBank;
import com.ewyboy.coin.Util.Logger;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Bank extends BaseBlock implements ITileEntityProvider {

    public Bank() {
        super(Material.iron, Reference.BlockNames.blockBankName);
        GameRegistry.registerTileEntity(TileBank.class, Reference.BlockNames.blockBankName);
    }

    private TileBank getTileEntity(World world, int x, int y, int z) {
        return (TileBank) world.getTileEntity(x,y,z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            PlayerWallet playerWallet = new PlayerWallet();
            Logger.info(player.getHeldItem());

            ItemStack coin = new ItemStack(CoinLoader.ItemCoins[0]);

            if (player.getHeldItem() == null) return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);

            if (player.isSneaking()) {
                if (playerWallet.canTakeMoney(1)) {
                    playerWallet.takeMoney(1, player);
                    if (!player.inventory.addItemStackToInventory(coin)) {
                        EntityItem entityItem = new EntityItem(world, x + 0.5, y + 1.25, z + 0.5, coin);
                        world.spawnEntityInWorld(entityItem);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
                }
            } else if (player.inventory.getCurrentItem().getItem() instanceof ItemCoin) {
                ItemCoin itemCoin = (ItemCoin) player.inventory.getCurrentItem().getItem();
                playerWallet.giveMoney(itemCoin.getCoinValue(), player);
                player.getHeldItem().stackSize--;
                player.openContainer.detectAndSendChanges();
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBank();
    }
}
