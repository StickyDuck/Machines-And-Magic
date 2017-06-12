package stickyduck.machinesandmagic.magic.blocks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import stickyduck.machinesandmagic.magic.tileentity.TileEssenceRemover;

public class ContainerEssenceRemover extends Container 
{
	private final TileEssenceRemover tileEssenceRemover;
	private int[] EssenceStorage = new int[] {0,0,0,0,0,0};
	private int CookTime;
	private int Mana;
	
	public ContainerEssenceRemover(InventoryPlayer playerInventory, final TileEssenceRemover machine)
	{
		IItemHandler inventory = machine.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		tileEssenceRemover = machine;
		this.addSlotToContainer(new SlotItemHandler(inventory, 0, 68, 17));
		this.addSlotToContainer(new SlotItemHandler(inventory, 1, 8, 33));
		this.addSlotToContainer(new SlotItemHandler(inventory, 2, 128, 33));
		
		for (int i = 0; i < 6; i++)
		{
			this.addSlotToContainer(new SlotItemHandler(inventory, 3 + i, 8 + (24 * i), 109));
		}
		
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 198));
        }
	}
	
	public void addListener(IContainerListener listener)
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileEssenceRemover);
	}
	
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);
            
            for (int j = 0; j < 6; j++)
            {
            	if(this.EssenceStorage[j] != this.tileEssenceRemover.getField(3+j))
            	icontainerlistener.sendProgressBarUpdate(this, 3 + j, this.tileEssenceRemover.getField(3+j));
            }
            
            if (this.CookTime != this.tileEssenceRemover.getField(1))
            {
                icontainerlistener.sendProgressBarUpdate(this, 1, this.tileEssenceRemover.getField(1));
            }

            if (this.Mana != this.tileEssenceRemover.getField(0))
            {
                icontainerlistener.sendProgressBarUpdate(this, 0, this.tileEssenceRemover.getField(0));
            }
        }
		
		for (int i = 0; i < 6; i++)
		{
			this.EssenceStorage[i] = this.tileEssenceRemover.getField(3+i);
		}
		this.CookTime = this.tileEssenceRemover.getField(1);
        this.Mana = this.tileEssenceRemover.getField(0);
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		this.tileEssenceRemover.setField(id, data);
	}
	
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.tileEssenceRemover.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
	
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
	
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
	
			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
	
			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
	
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
	
			slot.onTake(player, itemstack1);
		}
	
		return itemstack;
	}
}
