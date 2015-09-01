package be.driesstelten.LetsModReboot.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import be.driesstelten.LetsModReboot.block.BlockLMRB;
import be.driesstelten.LetsModReboot.block.BlockLamp;
import be.driesstelten.LetsModReboot.block.BlockPizzaOven_active;
import be.driesstelten.LetsModReboot.block.BlockPizzaOven_idle;
import be.driesstelten.LetsModReboot.block.BlockTiles;
import be.driesstelten.LetsModReboot.block.BlockWaaw;
import be.driesstelten.LetsModReboot.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	
	public static final BlockLMRB tiles = new BlockTiles();
	public static final BlockLMRB lamp = new BlockLamp();
	public static final BlockLMRB waaw = new BlockWaaw();
	public static final BlockLMRB pizzaOven_active = new BlockPizzaOven_active();
	public static final BlockLMRB pizzaOven_idle = new BlockPizzaOven_idle();
	
	public static void init() {
		
		GameRegistry.registerBlock(tiles, "tiles");
		GameRegistry.registerBlock(lamp, "lamp");
		GameRegistry.registerBlock(waaw, "waaw");
		GameRegistry.registerBlock(pizzaOven_active, "pizzaOven_active");
		GameRegistry.registerBlock(pizzaOven_idle, "pizzaOven_idle");
		
	}

}
