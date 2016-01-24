package com.ewyboy.coin.Blocks;

import com.ewyboy.coin.Loaders.ConfigLoader;
import com.ewyboy.coin.Loaders.CreativeTabLoader;
import com.ewyboy.coin.TileEntities.TileBase;
import com.ewyboy.coin.Util.Logger;
import com.ewyboy.coin.Util.NBTHelper;
import com.ewyboy.coin.Util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BaseBlock extends Block {

    public BaseBlock(Material material, String name) {
        super(material);
        setCreativeTab(CreativeTabLoader.CoinBlockTab);
        setBlockName(name);
        GameRegistry.registerBlock(this,name);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        EntityPlayer player = (EntityPlayer) placer;
        if (!hasOwner(stack)) setOwner(stack, player);

        if (world.getTileEntity(x, y, z) instanceof TileBase) {
            int direction;
            int facing = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

            switch (facing) {
                case 0: direction = ForgeDirection.NORTH.ordinal();
                    break;
                case 1: direction = ForgeDirection.EAST.ordinal();
                    break;
                case 2: direction = ForgeDirection.SOUTH.ordinal();
                    break;
                case 3: direction = ForgeDirection.WEST.ordinal();
                    break;
                default: direction = ForgeDirection.NORTH.ordinal();
            }
            ((TileBase) world.getTileEntity(x, y, z)).setOrientation(direction);
            if (ConfigLoader.debugMode) Logger.info("Direction: " + direction); Logger.info("Owner: " + getOwner(stack));
        }
    }

    public static void setOwner(ItemStack stack, EntityPlayer player) {
        NBTHelper.setString(stack, Reference.NBT.OWNER, player.getDisplayName());
    }

    public static boolean hasOwner(ItemStack stack) {
        return NBTHelper.hasTag(stack, Reference.NBT.OWNER);
    }

    public static String getOwner(ItemStack stack) {
        if (hasOwner(stack)) {
            return NBTHelper.getString(stack, Reference.NBT.OWNER);
        }
        return StatCollector.translateToLocal("No owner!");
    }

    @SideOnly(Side.CLIENT)
    private IIcon Texture;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        Texture = register.registerIcon(Reference.ModInfo.ModID + ":" + "Default");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return Texture;
    }
}
