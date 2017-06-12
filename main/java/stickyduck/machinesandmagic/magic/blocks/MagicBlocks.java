package stickyduck.machinesandmagic.magic.blocks;

import stickyduck.machinesandmagic.core.blocks.CoreBlocks;

public class MagicBlocks extends CoreBlocks
{
	public static BlockEssenceRemover EssenceRemover;
	
	public static void init()
	{
		EssenceRemover = register(new BlockEssenceRemover());
	}
}
