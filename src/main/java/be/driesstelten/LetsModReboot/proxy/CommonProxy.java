package be.driesstelten.LetsModReboot.proxy;

import be.driesstelten.LetsModReboot.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy{
	
	public void registerTileEntities() {
		
		GameRegistry.registerTileEntity(PizzaOvenTileEntity.class, Reference.MOD_ID + ":PizzaOvenTileEntity");
		
	}

}
