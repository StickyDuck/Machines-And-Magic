package stickyduck.machinesandmagic.magic.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stickyduck.machinesandmagic.core.items.ItemBase;
import stickyduck.machinesandmagic.magic.blocks.containers.ContainerEssenceRemover;
import stickyduck.machinesandmagic.magic.items.MagicItems;

public class TileEssenceRemover extends TileEntityLockable implements ITickable, ISidedInventory
{
	/* Removes Essence from Crystals and other infused objects */
	
	private static final int[] SLOTS_TOP = new int[] {0};
	private static final int[] SLOTS_BOTTOM = new int[] {1, 2};
	private static final int[] SLOTS_SIDES = new int[] {2};
	private static final ItemBase[] Crystals = new ItemBase[] {MagicItems.crystalAir, MagicItems.crystalEarth, MagicItems.crystalFire, MagicItems.crystalWater, MagicItems.crystalLight, MagicItems.crystalDark};
	private int Mana;
	private int[] EssenceStorage = new int[] {0,0,0,0,0,0};
	
	private static int MaximumMana = 1500;
	private static int MaximumEssence = 100;
	
	private NonNullList<ItemStack> machineItemStacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	
	private int CookTime;
	private static int TotalCookTime = 300;
	
	private String machineCustomName;
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
        this.machineItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.machineItemStacks);
        this.Mana = compound.getInteger("Mana");
        this.CookTime = compound.getInteger("CookTime");
        
        for(int i = 0; i < 6; i++)
        {
        	this.EssenceStorage[i] = compound.getInteger("Essence" + i);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.machineCustomName = compound.getString("CustomName");
        
        }
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
        compound.setInteger("Mana", (short)this.Mana);
        compound.setInteger("CookTime", (short)this.CookTime);
        
        for(int i = 0; i < 6; i++)
        {
        	compound.setInteger("Essence" + i, EssenceStorage[i]);
        }
        
        ItemStackHelper.saveAllItems(compound, this.machineItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.machineCustomName);
        }

        return compound;
	}
	
	private boolean canCook()
	{
		if ((this.getStackInSlot(0)).isEmpty())
		{
			return false;
		}
		else
		{
			ItemStack input = this.getStackInSlot(0);
			ItemStack output = this.getStackInSlot(1);
			
			if (this.hasFuel())
			{
				for (int i = 0; i < 6; i++)
				{
					if (input.getItem() == Crystals[i])
					{
						if (this.EssenceStorage[i] < MaximumEssence && output.getCount() < getInventoryStackLimit())
							return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public void cookItem()
	{
		if (this.canCook())
		{
			ItemStack input = this.getStackInSlot(0);
			
			for (int i = 0; i < 6; i++)
			{
				if (input.getItem() == Crystals[i])
				{
					EssenceStorage[i] += 20;
				}
			}
			
			input.shrink(1);;
			
			if(this.getStackInSlot(1).isEmpty())
			{
				this.machineItemStacks.set(1, new ItemStack(MagicItems.crystalGlass));
			}
			else
			{
				this.getStackInSlot(1).grow(1);
			}
		}
	}
	
	private boolean hasFuel()
	{
		return this.Mana > 0;
	}
	
	public boolean isCooking()
	{
		return this.CookTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isCooking(IInventory inventory)
	{
		return inventory.getField(1) > 0;
	}
	
	public boolean hasMana()
	{
		return this.Mana > 0;
	}
	
	public void addMana(int amount)
	{
		this.Mana += amount;
	}
	
	@SideOnly(Side.CLIENT)
	public static int getMana(IInventory inventory)
	{
		return inventory.getField(0);
	}
	
	public ItemStack getStackInSlot(int index)
	{
		return (ItemStack)this.machineItemStacks.get(index);
	}
	
	public void update()
	{
		boolean flag = false;
		
		if(this.isCooking())
		{
			--this.Mana;
		}
		
		if (this.Mana < MaximumMana)
			++this.Mana;
		
		if(!this.world.isRemote)
		{
			if(this.canCook())
			{
				this.CookTime++;
				
				if (this.CookTime >= TotalCookTime)
				{
					this.CookTime = 0;
					this.cookItem();
					flag = true;
				}
			}
			//else this.CookTime = 0;
		}
		
		if(!this.isCooking())
		{
			flag = false;
		}
		
		if (flag)
		{
			this.markDirty();
		}
	}
	
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerEssenceRemover(playerInventory, this);
	}
	
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return true;
	}
	
	public void clear()
	{
		this.machineItemStacks.clear();
	}
	
	public int getSizeInventory()
	{
		return this.machineItemStacks.size();
	}
	
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.machineItemStacks, index, count);
	}
	
	public int getField(int id)
	{
		switch(id)
		{
		case 0:
			return this.Mana;
		case 1:
			return this.CookTime;
		case 2:
			return TotalCookTime;
		case 3:
			return this.EssenceStorage[0];
		case 4:
			return this.EssenceStorage[1];
		case 5:
			return this.EssenceStorage[2];
		case 6:
			return this.EssenceStorage[3];
		case 7:
			return this.EssenceStorage[4];
		case 8:
			return this.EssenceStorage[5];
		case 9:
			return MaximumEssence;
		case 10:
			return MaximumMana;
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value)
	{
		switch(id)
		{
		case 0:
			this.Mana = value;
			break;
		case 1:
			this.CookTime = value;
			break;
		}
	}
	
	public int getFieldCount()
	{
		return 11;
	}
	
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (index == 1)
		{
			return false;
		}
		else if (index == 0)
		{
			for (int i = 0; i < 6; i++)
			{
				if (stack.getItem() == Crystals[i]) return true;
			}
			return false;
		}
		else return true;
	}
	
	public boolean hasCustomName()
	{
		return this.machineCustomName != null && !this.machineCustomName.isEmpty();
	}
	
	public String getName()
	{
		return this.hasCustomName() ? this.machineCustomName : "machine.essence_remover";
	}
	
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		ItemStack itemstack = this.getStackInSlot(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStacksEqual(stack, itemstack);
		this.machineItemStacks.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());
		}
		
		if (index == 2 && !flag)
		{
			this.CookTime = 0;
			this.markDirty();
		}
	}
	
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.machineItemStacks, index);
	}
	
	public void openInventory(EntityPlayer player)
	{
		
	}
	
	public void closeInventory(EntityPlayer player)
	{
		
	}
	
	public boolean isEmpty()
    {
        for (ItemStack itemstack : this.machineItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }
	
	public String getGuiID()
	{
		return "machinesandmagic:essence_remover";
	}
	
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, stack);
	}
	
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		if(direction == EnumFacing.DOWN && index == 1)
		{
			return true;
		}
		
		return false;
	}
	
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
	}
	
}
