package com.ewyboy.coin.Items;

import com.ewyboy.coin.Loaders.CreativeTabLoader;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemCoin extends Item {

    private int coinValue;
    private String coinName;

    public ItemCoin(String coinName, int coinValue) {
        setCreativeTab(CreativeTabLoader.CoinTab);
        GameRegistry.registerItem(this, coinName);
        setUnlocalizedName(coinName);
        setCoinName(coinName);
        setCoinValue(coinValue);
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }

    public int getCoinValue() {
        return coinValue;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean useExtraInfo) {
        if (coinValue > 0) info.add("Value: " + coinValue);
        else info.add("Value: ERROR");
    }

    @SideOnly(Side.CLIENT)
    private IIcon Texture;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        Texture = register.registerIcon(Reference.ModInfo.ModID + ":" + coinName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage (int dmg) {
        return Texture;
    }
}
