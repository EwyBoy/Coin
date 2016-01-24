package com.ewyboy.coin.Loaders;

import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@GameRegistry.ObjectHolder(Reference.ModInfo.ModID)
public class CreativeTabLoader {
    public static CreativeTabs CoinTab = new CreativeTabs(Reference.ModInfo.ModName) {
        public ItemStack getIconItemStack(){return new ItemStack(CoinLoader.ItemCoins[4]);}
        @Override
        public Item getTabIconItem() {return null;}
    };
    public static CreativeTabs CoinBlockTab = new CreativeTabs(Reference.ModInfo.ModName) {
        public ItemStack getIconItemStack(){return new ItemStack(BlockLoader.Bank);}
        @Override
        public Item getTabIconItem() {return null;}
    };
}
