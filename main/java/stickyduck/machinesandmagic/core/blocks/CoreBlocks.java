package stickyduck.machinesandmagic.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoreBlocks 
{
	public static BlockOre oreCopper;
	public static BlockBase blockCopper;
	
	public static BlockOre oreTin;
	public static BlockBase blockTin;
	
	public static BlockOre oreCrystalAir;
	public static BlockOre oreCrystalEarth;
	public static BlockOre oreCrystalFire;
	public static BlockOre oreCrystalWater;
	
	public static void init()
	{
		oreCopper = register(new BlockOre("ore_copper"));
		blockCopper = register(new BlockBase(Material.IRON, "block_copper"));
		
		oreTin = register(new BlockOre("ore_tin"));
		blockTin = register(new BlockBase(Material.IRON, "block_tin"));
		
		oreCrystalAir = register(new BlockOre("ore_crystal_air"));
		oreCrystalEarth = register(new BlockOre("ore_crystal_earth"));
		oreCrystalFire = register(new BlockOre("ore_crystal_fire"));
		oreCrystalWater = register(new BlockOre("ore_crystal_water"));
	}
	
	protected static <T extends Block> T register(T block, ItemBlock itemBlock)
	{
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);
		
		if(block instanceof BlockBase)
		{
			((BlockBase)block).registerItemModel(itemBlock);
		}
		
		if(block instanceof BlockTileEntity)
		{
			GameRegistry.registerTileEntity(((BlockTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
		}
		
		return block;
	}
	
	protected static <T extends Block> T register(T block)
	{
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
}
