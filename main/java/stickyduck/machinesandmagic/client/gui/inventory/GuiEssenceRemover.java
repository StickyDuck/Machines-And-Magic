package stickyduck.machinesandmagic.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import stickyduck.machinesandmagic.MachinesAndMagic;
import stickyduck.machinesandmagic.magic.tileentity.TileEssenceRemover;

public class GuiEssenceRemover extends GuiContainer
{
	private static final ResourceLocation ER_GUI_TEXTURES = new ResourceLocation(MachinesAndMagic.MODID, "textures/gui/container/essence_remover.png");
	
	private final InventoryPlayer playerInventory;
	private final TileEssenceRemover tileEssenceRemover;
	
	public GuiEssenceRemover(Container container, TileEssenceRemover machineInv, InventoryPlayer playerInv)
	{
		super(container);
		this.playerInventory = playerInv;
		this.tileEssenceRemover = machineInv;
		this.ySize = 114 + 6 * 18;
	}
	
	protected void drawGuiContainerForegroundLater(int mouseX, int mouseY)
	{
		String s = this.tileEssenceRemover.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, 8, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(ER_GUI_TEXTURES);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 6 * 18 + 18);
		this.drawTexturedModalRect(i, j + 6 * 18 + 18, 0, 126, this.xSize, 96);
		
		int ManaProgress = (int)getManaRemainingScaled(108);
		this.drawTexturedModalRect(i + 152, j + 17 + 108 - ManaProgress, 240, 108 - ManaProgress, 16, ManaProgress + 1);
		
		
		int CookProgress = (int)getCookProgressScaled(15);
		this.drawTexturedModalRect(i + 69, j + 38, 176, 0, 16, CookProgress);
		
		for (int x = 0; x < 6; x++)
		{
			int EssenceProgress = (int)getEssenceStorageScaled(44, 3+x);
			this.drawTexturedModalRect(i + 11 + (24 * x), j + 59 + 44 - EssenceProgress, 176 + (10 * x), 15 + 44 - EssenceProgress, 10, EssenceProgress + 1);
		}
	}
	
	private float getManaRemainingScaled(int pixels)
	{
		float Mana = this.tileEssenceRemover.getField(0);
		float MaxMana = this.tileEssenceRemover.getField(10);
		float ManaPercentage = Mana / MaxMana;
		return pixels * ManaPercentage;
	}
	
	private float getEssenceStorageScaled(int pixels, int id)
	{
		float Essence = this.tileEssenceRemover.getField(id);
		float MaxEssence = this.tileEssenceRemover.getField(9);
		float EssencePercentage = Essence / MaxEssence;
		return pixels * EssencePercentage;
	}
	
	private float getCookProgressScaled(int pixels)
	{
		float CookTime = this.tileEssenceRemover.getField(1);
		float TotalCookTime = this.tileEssenceRemover.getField(2);
		float CookPercentage = CookTime / TotalCookTime;
		return pixels * CookPercentage;
	}
}
