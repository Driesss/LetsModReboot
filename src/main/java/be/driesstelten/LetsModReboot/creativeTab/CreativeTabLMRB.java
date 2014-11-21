package be.driesstelten.LetsModReboot.creativeTab;

import be.driesstelten.LetsModReboot.init.ModItems;
import be.driesstelten.LetsModReboot.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabLMRB {
	
	public static final CreativeTabs LMRB_TAB = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return ModItems.pizza;
		}
		
	};

}
