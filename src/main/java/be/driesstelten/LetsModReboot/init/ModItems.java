package be.driesstelten.LetsModReboot.init;

import cpw.mods.fml.common.registry.GameRegistry;
import be.driesstelten.LetsModReboot.item.ItemLMRB;
import be.driesstelten.LetsModReboot.item.ItemPizza;

public class ModItems {
	
	public static final ItemLMRB pizza = new ItemPizza();
	
	public static void init() {
		
		GameRegistry.registerItem(pizza, "pizza");
	}

}
