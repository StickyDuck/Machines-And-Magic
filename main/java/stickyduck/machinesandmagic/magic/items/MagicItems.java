package stickyduck.machinesandmagic.magic.items;

import stickyduck.machinesandmagic.core.items.CoreItems;
import stickyduck.machinesandmagic.core.items.ItemBase;

public class MagicItems extends CoreItems
{
	public static ItemBase crystalRawAir;
	public static ItemBase crystalRawEarth;
	public static ItemBase crystalRawFire;
	public static ItemBase crystalRawWater;
	
	public static ItemBase crystalAir;
	public static ItemBase crystalEarth;
	public static ItemBase crystalFire;
	public static ItemBase crystalWater;
	public static ItemBase crystalGlass;
	public static ItemBase crystalLight;
	public static ItemBase crystalDark;
	
	public static ManaCrystal crystalManaSmall;
	public static ManaCrystal crystalManaMedium;
	public static ManaCrystal crystalManaLarge;
	public static ManaCrystal crystalManaDense;
	public static ManaCrystal crystalManaPowerful;
	public static ManaCrystal crystalManaUniversal;
	public static ManaCrystal crystalManaTemporal;
	
	public static void init()
	{
		crystalRawAir = register(new ItemBase("crystal_raw_air"));
		crystalRawEarth = register(new ItemBase("crystal_raw_earth"));
		crystalRawFire = register(new ItemBase("crystal_raw_fire"));
		crystalRawWater = register(new ItemBase("crystal_raw_water"));
		
		crystalAir = register(new ItemBase("crystal_air"));
		crystalEarth = register(new ItemBase("crystal_earth"));
		crystalFire = register(new ItemBase("crystal_fire"));
		crystalWater = register(new ItemBase("crystal_water"));
		crystalGlass = register(new ItemBase("crystal_glass"));
		crystalLight = register(new ItemBase("crystal_light"));
		crystalDark = register(new ItemBase("crystal_dark"));
		
		crystalManaSmall = register(new ManaCrystal("crystal_mana_small", 300));
		crystalManaMedium = register(new ManaCrystal("crystal_mana_medium", 1500));
		crystalManaLarge = register(new ManaCrystal("crystal_mana_large", 7500));
		crystalManaDense = register(new ManaCrystal("crystal_mana_dense", 37500));
		crystalManaPowerful = register(new ManaCrystal("crystal_mana_powerful", 187500));
		crystalManaUniversal = register(new ManaCrystal("crystal_mana_universal", 937500));
		crystalManaTemporal = register(new ManaCrystal("crystal_mana_temporal", 4687500));
	}
}
