package stickyduck.machinesandmagic.core.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoreItems 
{
	public static ItemBase ingotCopper;
	public static ItemBase ingotTin;
	
	public static ItemBase gemRuby;
	public static ItemBase gemSapphire;
	public static ItemBase gemAmethyst;
	public static ItemBase gemEmerald;
	
	public static ItemBase gemBRuby;
	public static ItemBase gemBSapphire;
	public static ItemBase gemBAmethyst;
	public static ItemBase gemBDiamond;
	
	public static void init()
	{
		ingotCopper = register(new ItemBase("ingot_copper"));
		ingotTin = register(new ItemBase("ingot_tin"));
		
		gemRuby = register(new ItemBase("gem_ruby"));
		gemSapphire = register(new ItemBase("gem_sapphire"));
		gemAmethyst = register(new ItemBase("gem_amethyst"));
		gemEmerald = register(new ItemBase("gem_emerald"));
		
		gemBRuby = register(new ItemBase("gem_ruby_brilliant"));
		gemBSapphire = register(new ItemBase("gem_sapphire_brilliant"));
		gemBAmethyst = register(new ItemBase("gem_amethyst_brilliant"));
		gemBDiamond = register(new ItemBase("gem_diamond_brilliant"));
	}
	
	protected static <T extends Item> T register(T item)
	{
		GameRegistry.register(item);
		
		if(item instanceof ItemBase)
		{
			((ItemBase)item).registerItemModel();
		}
		
		return item;
	}
}
