package stickyduck.machinesandmagic.core.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
import stickyduck.machinesandmagic.core.blocks.CoreBlocks;

import java.util.Random;

public class CoreWorldGeneration implements IWorldGenerator
{
	public static void init()
	{
		GameRegistry.registerWorldGenerator(new CoreWorldGeneration(), 3);
	}
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.getDimension() == 0)
		{
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		generateOre(CoreBlocks.oreCopper.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 4, 256, 5 + random.nextInt(4), 12);
		generateOre(CoreBlocks.oreTin.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 4, 64, 5 + random.nextInt(4), 12);
		
		generateOre(CoreBlocks.oreCrystalAir.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 32, 256, 1 + random.nextInt(3), 5);
		generateOre(CoreBlocks.oreCrystalEarth.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 4, 32, 1 + random.nextInt(3), 5);
		generateOre(CoreBlocks.oreCrystalFire.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 4, 32, 1 + random.nextInt(3), 5);
		generateOre(CoreBlocks.oreCrystalWater.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 32, 64, 1 + random.nextInt(3), 5);
	}
	
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances)
	{
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chances; i++)
		{
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
}
