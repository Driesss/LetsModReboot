package be.driesstelten.LetsModReboot.init;

import be.driesstelten.LetsModReboot.block.BlockLMRB;
import be.driesstelten.LetsModReboot.block.BlockWaaw;
import be.driesstelten.LetsModReboot.item.ItemLMRB;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recepies {
	
	public static void init() {
		
		GameRegistry.addRecipe(new ItemStack(ModItems.pizza), " s ", "ttt", "www", 's', new ItemStack(Items.cooked_beef), 't', ModBlocks.waaw, 'w', new ItemStack(Items.wheat));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.flour), "cropWheat"));
	}

}
