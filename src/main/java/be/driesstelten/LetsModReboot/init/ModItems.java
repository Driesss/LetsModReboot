package be.driesstelten.LetsModReboot.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import be.driesstelten.LetsModReboot.item.ItemFlour;
import be.driesstelten.LetsModReboot.item.ItemLMRB;
import be.driesstelten.LetsModReboot.item.ItemPizza;
import be.driesstelten.LetsModReboot.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static final ItemLMRB pizza = new ItemPizza();
	public static final ItemLMRB flour = new ItemFlour();
	
	public static void init() {
		
		GameRegistry.registerItem(pizza, "pizza");
		GameRegistry.registerItem(flour, "flour");
		
		OreDictionary.registerOre("dustFlour", ModItems.flour);
	}

}
