package com.ewyboy.coin;

import com.ewyboy.coin.Commands.CoinCommandBase;
import com.ewyboy.coin.Events.CoinEventHandler;
import com.ewyboy.coin.Loaders.BlockLoader;
import com.ewyboy.coin.Loaders.CoinLoader;
import com.ewyboy.coin.Loaders.ConfigLoader;
import com.ewyboy.coin.Proxies.CommonProxy;
import com.ewyboy.coin.Util.Logger;
import com.ewyboy.coin.Util.Reference;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

import java.util.concurrent.TimeUnit;

@Mod(modid = Reference.ModInfo.ModID, name = Reference.ModInfo.ModName, version = Reference.ModInfo.BuildVersion, acceptedMinecraftVersions = "["+ Reference.ModInfo.MinecraftVersion+"]")
public class Coin {

    @Mod.Instance(Reference.ModInfo.ModID)
    public static Coin instance;

    @SidedProxy(modId = Reference.ModInfo.ModID, clientSide = Reference.Paths.clientProxyPath, serverSide = Reference.Paths.commonProxyPath)
    public static CommonProxy proxy;

    private long launchTime;

    @Mod.EventHandler
    public void serverStartInit(FMLServerStartingEvent event) {
        Logger.info("Hay Ewy! I am starting..");
    }

    @Mod.EventHandler
    public void serverCloseInit(FMLServerStoppingEvent event) {
        Logger.info("Bye Ewy! I am closing..");
    }

    public static CoinEventHandler coinEventHandler;

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading commands started");
            MinecraftServer server = MinecraftServer.getServer();
            ICommandManager command = server.getCommandManager();
            ServerCommandManager manager = (ServerCommandManager) command;
            manager.registerCommand(new CoinCommandBase());
        Logger.info("Commands successfully loaded after + " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
            ConfigLoader.init(event.getSuggestedConfigurationFile());
            FMLInterModComms.sendMessage("Waila", "register", Reference.Paths.wailaPath);
            launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
            coinEventHandler = new CoinEventHandler();
            FMLCommonHandler.instance().bus().register(coinEventHandler);
            //MinecraftForge.EVENT_BUS.register(new CoinEventHandler());
        Logger.info("Pre-Initialization process successfully done");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Initialization started");
                CoinLoader.loadCoins();
                BlockLoader.loadBlocks();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Initialization process successfully done");
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Post-Initialization started");
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Post-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Post-Initialization process successfully done");
        Logger.info("Total Initialization time was " + launchTime);
    }
}
