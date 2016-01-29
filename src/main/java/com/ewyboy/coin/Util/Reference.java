package com.ewyboy.coin.Util;

public class Reference {

    public static final class ModInfo {
        public static final String ModID = "coin";
        public static final String ModName = "Coin";
        public static final String VersionMajor = "1";
        public static final String VersionMinor = "0";
        public static final String VersionPatch = "0";
        public static final String BuildVersion = VersionMajor + "." + VersionMinor + "." + VersionPatch;
        public static final String MinecraftVersion = "1.7.10";
    }

    public static final class Paths {
        public static final String wailaPath = "com.ewyboy.coin.Waila.Waila.onWailaCall";
        public static final String clientProxyPath = "com.ewyboy.coin.Proxies.ClientProxy";
        public static final String commonProxyPath = "com.ewyboy.coin.Proxies.CommonProxy";
    }

    public static final class NBT {
        public static final String OWNER = "Owner";
        public static final String DIRECTION = "TileEntityDirection";
        public static final String PLAYERWALLET = "PlayerWallet";
        public static final String HASPLAYERJOINEDBEFORE = "HasPlayerJoinedBefore";
        public static final String USERNAME = "Username";
        public static final String ISPLAYERONLINE = "IsPlayerOnline";
        public static final String CAPACITY = "Capacity";
        public static final String TRANSFER = "Transfer";
        public static final String ENERGYSTORED = "EnergyStored";
    }

    public static final class BlockNames {
        public static final String blockBankName = "BlockBank";
        public static final String blockBankerName = "BlockBanker";
        public static final String blockTaxerName = "BlockTaxer";
        public static final String blockExchangerName = "BlockExchanger";
        public static final String blockMarketName = "BlockMarket";
    }

    public static final class CoinProperties {
        public static final String[] coinMaterials = {"Copper", "Bronze", "Silver", "Gold", "Platinum"};
        public static final String coinNames[] = {"ItemCoinCopper", "ItemCoinBronze", "ItemCoinSilver", "ItemCoinGold", "ItemCoinPlatinum"};
        public static int[] coinValues = {1, 10, 100, 1000, 10000};
    }

    public static final class ConfigCategoryNames {
        public static final String coinProperty = "Coin Properties";
        public static final String togglables = "Togglables";
    }

    public static final class ConfigDescriptions {
        public static final String debugMode = "WARNING: Only used by developers to debug!";
    }

    public static final class Commands {
        public static final String BASE_COMMAND;
        public Commands() {}
        static {BASE_COMMAND = ModInfo.ModID.toLowerCase();}
    }
}