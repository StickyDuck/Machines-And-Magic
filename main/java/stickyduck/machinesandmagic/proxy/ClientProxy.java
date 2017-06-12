package stickyduck.machinesandmagic.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import stickyduck.machinesandmagic.MachinesAndMagic;

public class ClientProxy extends CommonProxy 
{
	 public void registerItemRenderer(Item item, int meta, String id) 
	 { 
		 ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MachinesAndMagic.MODID + ":" + id, "inventory"));
	 }
	 
	 @Override
		public String localise(String unlocalised, Object... args) {
			return I18n.format(unlocalised, args);
		}

}
