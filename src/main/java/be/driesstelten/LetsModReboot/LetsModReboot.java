package be.driesstelten.LetsModReboot;

import net.minecraftforge.common.config.Configuration;
import sun.rmi.log.LogHandler;
import be.driesstelten.LetsModReboot.handler.ConfigurationHandler;
import be.driesstelten.LetsModReboot.init.ModItems;
import be.driesstelten.LetsModReboot.proxy.IProxy;
import be.driesstelten.LetsModReboot.reference.Reference;
import be.driesstelten.LetsModReboot.utility.logHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class LetsModReboot {
	
	@Mod.Instance(Reference.MOD_ID)
	public static LetsModReboot instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModItems.init();
		logHelper.info("Pre Initialization Complete!");
		
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
		logHelper.info("Initialization Complete!");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		logHelper.info("Post Initialization Complete!");
		
	}
	

}
