package stickyduck.machinesandmagic.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import stickyduck.machinesandmagic.MachinesAndMagic;
import stickyduck.machinesandmagic.core.items.CoreItems;

public class CoreTab extends CreativeTabs
{
	public CoreTab()
	{
		super(MachinesAndMagic.MODID);
	}
	
	@Override 
	public ItemStack getTabIconItem()
	{
		return new ItemStack(CoreItems.ingotCopper);
	}
}
