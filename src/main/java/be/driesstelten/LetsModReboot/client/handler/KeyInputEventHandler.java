package be.driesstelten.LetsModReboot.client.handler;

import be.driesstelten.LetsModReboot.client.settings.Keybindings;
import be.driesstelten.LetsModReboot.reference.Key;
import be.driesstelten.LetsModReboot.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler {
	
	
	private static Key getPressedKeybinding() {
	
		if (Keybindings.herp.isPressed()) {
			return Key.HERP;
		} else if (Keybindings.derp.isPressed()) {
			return Key.DERP;
		}
		return Key.UNKNOWN;
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		
		//LogHelper.info(getPressedKeybinding());
		
	}

}
