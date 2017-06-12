package stickyduck.machinesandmagic.magic.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import stickyduck.machinesandmagic.GuiHandler;
import stickyduck.machinesandmagic.MachinesAndMagic;
import stickyduck.machinesandmagic.core.blocks.BlockTileEntity;
import stickyduck.machinesandmagic.magic.tileentity.TileEssenceRemover;

public class BlockEssenceRemover extends BlockTileEntity<TileEssenceRemover>
{
	public BlockEssenceRemover()
	{
		super(Material.WOOD, "essence_remover");
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			TileEssenceRemover tile = getTileEntity(world, pos);
			
			if (!player.isSneaking())
			{
				player.openGui(MachinesAndMagic.instance, GuiHandler.EssenceRemover, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEssenceRemover tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public Class<TileEssenceRemover> getTileEntityClass()
	{
		return TileEssenceRemover.class;
	}
	
	@Nullable
	@Override
	public TileEssenceRemover createTileEntity(World world, IBlockState state)
	{
		return new TileEssenceRemover();
	}
}
