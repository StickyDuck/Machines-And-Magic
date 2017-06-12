package stickyduck.machinesandmagic.magic.items;

import stickyduck.machinesandmagic.core.items.ItemBase;

public class ManaCrystal extends ItemBase
{
	private static int MaxManaStorage;
	private int ManaStored = 0;
	public ManaCrystal(String name, int MaxStorage)
	{
		super(name);
		
		MaxManaStorage = MaxStorage;
	}
	
	public int getMaxManaStorage()		{ return MaxManaStorage;}
	public int getManaStorage()			{ return this.ManaStored;}
	public boolean isFull()				{ return this.ManaStored == MaxManaStorage;}
	public boolean isEmpty()			{ return this.ManaStored > 0;}
	public void addMana(int amount) 	{ this.ManaStored += amount;}
	public void removeMana(int amount) 	{ this.ManaStored -= amount;}
}
