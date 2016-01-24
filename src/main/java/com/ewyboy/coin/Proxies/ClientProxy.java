package com.ewyboy.coin.Proxies;

import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    public static boolean shiftPressed() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
    }
    public static boolean ctrlPressed() {
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
    }

}
