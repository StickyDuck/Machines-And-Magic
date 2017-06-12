package stickyduck.machinesandmagic.core.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import stickyduck.machinesandmagic.core.items.CoreItems;
import stickyduck.machinesandmagic.magic.items.MagicItems;

import java.util.Random;

public class BlockOre extends BlockBase
{
	public BlockOre(String name)
	{
		super(Material.ROCK, name);
		
		setHardness(3f);
		setResistance(5f);
	}
	
	@Override
	public BlockOre setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override 
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return 	this == CoreBlocks.oreCrystalAir ? MagicItems.crystalRawAir : (
				this == CoreBlocks.oreCrystalEarth ? MagicItems.crystalRawEarth : (
				this == CoreBlocks.oreCrystalFire ? MagicItems.crystalRawFire : (
				this == CoreBlocks.oreCrystalWater ? MagicItems.crystalRawWater : 
					Item.getItemFromBlock(this))));
	}
	
	//Raw crystal drop rate bound
	private int cRDB = 3;
	
	@Override
	public int quantityDropped(Random random)
	{
		return 	this == CoreBlocks.oreCrystalAir ? 1 + random.nextInt(cRDB) : (
				this == CoreBlocks.oreCrystalEarth ? 1 + random.nextInt(cRDB) : (
				this == CoreBlocks.oreCrystalFire ? 1 + random.nextInt(cRDB) : (
				this == CoreBlocks.oreCrystalWater ? 1 + random.nextInt(cRDB) :
				1)));
	}
	
}
