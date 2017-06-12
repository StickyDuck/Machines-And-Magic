package stickyduck.machinesandmagic;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import stickyduck.machinesandmagic.client.CoreTab;
import stickyduck.machinesandmagic.core.blocks.CoreBlocks;
import stickyduck.machinesandmagic.core.items.CoreItems;
import stickyduck.machinesandmagic.core.recipe.CoreRecipes;
import stickyduck.machinesandmagic.core.world.CoreWorldGeneration;
import stickyduck.machinesandmagic.magic.blocks.MagicBlocks;
import stickyduck.machinesandmagic.magic.items.MagicItems;
import stickyduck.machinesandmagic.proxy.*;

@Mod(modid = MachinesAndMagic.MODID, name = MachinesAndMagic.NAME, version = MachinesAndMagic.VERSION)
public class MachinesAndMagic 
{
	@SidedProxy(serverSide = "stickyduck.machinesandmagic.proxy.CommonProxy", clientSide = "stickyduck.machinesandmagic.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	public static final String MODID = "machinesandmagic";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "Machines and Magic";
	
	public static final CoreTab creativeTab  = new CoreTab();
	
	@Mod.Instance(MODID)
	public static MachinesAndMagic instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.println("Loading Machines and Magic!");
		CoreItems.init();
		CoreBlocks.init();
		CoreWorldGeneration.init();
		
		MagicItems.init();
		MagicBlocks.init();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		CoreRecipes.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
