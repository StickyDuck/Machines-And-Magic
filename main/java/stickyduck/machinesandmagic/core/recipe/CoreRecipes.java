package stickyduck.machinesandmagic.core.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import stickyduck.machinesandmagic.core.blocks.CoreBlocks;
import stickyduck.machinesandmagic.core.items.CoreItems;

public class CoreRecipes 
{
	public static void init()
	{
		//Copper Ingot x9 -> Copper Block x1
		itemToBlock(CoreItems.ingotCopper, CoreBlocks.blockCopper);
		//Copper Block x1 -> Copper Ingot x9
		blockToItem(CoreBlocks.blockCopper, CoreItems.ingotCopper);
		//Copper Ore x1 -S> Copper Ingot x1
		GameRegistry.addSmelting(CoreBlocks.oreCopper, new ItemStack(CoreItems.ingotCopper), 0.7f);
		
		//Copper Ingot x9 -> Copper Block x1
		itemToBlock(CoreItems.ingotTin, CoreBlocks.blockTin);	
		//Copper Block x1 -> Copper Ingot x9
		blockToItem(CoreBlocks.blockTin, CoreItems.ingotTin);	
		//Copper Ore x1 -S> Copper Ingot x1
		GameRegistry.addSmelting(CoreBlocks.oreTin, new ItemStack(CoreItems.ingotTin), 0.7f);
		
	}
	
	protected static void itemToBlock(Item item, Block block)
	{
		GameRegistry.addShapelessRecipe(new ItemStack(block),
				item, item, item,
				item, item, item,
				item, item, item
				);
	}
	
	protected static void blockToItem(Block block, Item item)
	{
		GameRegistry.addShapelessRecipe(new ItemStack(item, 9), block);
	}
}
