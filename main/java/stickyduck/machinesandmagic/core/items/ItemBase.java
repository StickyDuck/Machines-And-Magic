package stickyduck.machinesandmagic.core.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import stickyduck.machinesandmagic.MachinesAndMagic;

public class ItemBase extends Item
{
	protected String name;
	
	public ItemBase(String name)
	{
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MachinesAndMagic.creativeTab);
	}
	
	public void registerItemModel()
	{
		MachinesAndMagic.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	public ItemBase setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
