package be.driesstelten.LetsModReboot.block;

import be.driesstelten.LetsModReboot.creativeTab.CreativeTabLMRB;

public class BlockTiles extends BlockLMRB {
	
	public BlockTiles() {
		
		super();
		this.setBlockName("tiles");
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
	}

}
