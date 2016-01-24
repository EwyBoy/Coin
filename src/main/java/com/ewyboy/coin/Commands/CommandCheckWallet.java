package com.ewyboy.coin.Commands;

import com.ewyboy.coin.Economics.PlayerWallet;
import com.ewyboy.coin.Util.Logger;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandCheckWallet extends CoinCommandBase {

    @Override
    public String getCommandName() {
        return "checkwallet";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "checking your balance in your wallet and bank";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] strings) {
        PlayerWallet wallet = new PlayerWallet();
        Logger.info("Command " + getCommandName() + " just got used");
        sender.addChatMessage(new ChatComponentText("You wallet balance is " + wallet.getPlayerWalletAmount(getCommandSenderAsPlayer(sender))));
    }
}
