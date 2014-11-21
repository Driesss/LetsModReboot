package be.driesstelten.LetsModReboot.proxy;

import be.driesstelten.LetsModReboot.client.settings.Keybindings;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		
		ClientRegistry.registerKeyBinding(Keybindings.herp);
		ClientRegistry.registerKeyBinding(Keybindings.derp);
		
	}

}
