package be.driesstelten.LetsModReboot.block;

import be.driesstelten.LetsModReboot.creativeTab.CreativeTabLMRB;
import net.minecraft.block.material.Material;

public class BlockLamp extends BlockLMRB {
	
public BlockLamp() {
		
		super(Material.glass);
		this.setBlockName("lamp");
		this.setLightLevel(1.0F);
		this.setHardness(0.3F);
		this.setStepSound(soundTypeGlass);
		this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
	}

}
