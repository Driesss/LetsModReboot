package be.driesstelten.LetsModReboot.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import be.driesstelten.LetsModReboot.block.BlockLMRB;
import be.driesstelten.LetsModReboot.block.BlockLamp;
import be.driesstelten.LetsModReboot.block.BlockTiles;
import be.driesstelten.LetsModReboot.block.BlockWaaw;
import be.driesstelten.LetsModReboot.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	
	public static final BlockLMRB tiles = new BlockTiles();
	public static final BlockLMRB lamp = new BlockLamp();
	public static final BlockLMRB waaw = new BlockWaaw();
	
	public static void init() {
		
		GameRegistry.registerBlock(tiles, "tiles");
		GameRegistry.registerBlock(lamp, "lamp");
		GameRegistry.registerBlock(waaw, "waaw");
	}

}
