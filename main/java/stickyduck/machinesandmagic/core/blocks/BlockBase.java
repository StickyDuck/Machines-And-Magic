package stickyduck.machinesandmagic.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

import stickyduck.machinesandmagic.MachinesAndMagic;

public class BlockBase extends Block
{
	protected String name;
	
	public BlockBase(Material material, String name)
	{
		super(material);
		
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MachinesAndMagic.creativeTab);
	}
	
	public void registerItemModel(ItemBlock itemBlock)
	{
		MachinesAndMagic.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	@Override
	public BlockBase setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
