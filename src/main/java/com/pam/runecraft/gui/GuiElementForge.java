package com.pam.runecraft.gui;

import org.lwjgl.opengl.GL11;

import com.pam.runecraft.tileentity.TileEntityElementForge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiElementForge extends GuiContainer {
	private final TileEntityElementForge entityForge;
    private static final ResourceLocation gui = new ResourceLocation("runecraft:textures/gui/elementforge.png");
    public static final int WIDTH = 175;
    public static final int HEIGHT = 180;


    public GuiElementForge(InventoryPlayer invPlayer, TileEntityElementForge elementforgeTileEntity) {
        super(new ContainerElementForge(invPlayer, elementforgeTileEntity));
        this.entityForge = elementforgeTileEntity;
        xSize = WIDTH;
        ySize = HEIGHT;
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString("Element Forge", 4, 4, 4210752);
        fontRenderer.drawString(I18n.format("container.inventory"), 4, 89, 4210752);
       
        
        fontRenderer.drawString(String.valueOf(entityForge.AIR), 31, 19, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.ANIMAL), 31, 38, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.CHAOS), 31, 57, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.DEATH), 31, 76, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.EARTH), 67, 19, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.FIRE), 67, 38, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.LIFE), 67, 57, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.LIGHT), 67, 76, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.LIGHTNING), 103, 19, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.METAL), 103, 38, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.MIND), 103, 57, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.ORDER), 103, 76, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.PLANT), 139, 19, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.SPIRIT), 139, 38, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.VOID), 139, 57, 4210752);
        fontRenderer.drawString(String.valueOf(entityForge.WATER), 139, 76, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(gui);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        int progress = entityForge.getCookProgressScaled(24);
        drawTexturedModalRect(x + 7, 87, 176, 4, progress + 1, 16);
    }
    
}

