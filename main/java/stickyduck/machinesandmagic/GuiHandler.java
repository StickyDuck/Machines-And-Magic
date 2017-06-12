package stickyduck.machinesandmagic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import stickyduck.machinesandmagic.client.gui.inventory.GuiEssenceRemover;
import stickyduck.machinesandmagic.magic.blocks.containers.ContainerEssenceRemover;
import stickyduck.machinesandmagic.magic.tileentity.TileEssenceRemover;

public class GuiHandler implements IGuiHandler
{
	public static final int EssenceRemover = 0;
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case EssenceRemover:
				return new ContainerEssenceRemover(player.inventory, (TileEssenceRemover)world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}

	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case EssenceRemover:
			return new GuiEssenceRemover(getServerGuiElement(ID, player, world, x, y, z), (TileEssenceRemover)world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
		default:
			return null;
		}
	}
	
}
