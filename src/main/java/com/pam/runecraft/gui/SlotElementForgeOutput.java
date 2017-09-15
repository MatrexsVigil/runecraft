package com.pam.runecraft.gui;

import com.pam.runecraft.item.ItemRegistry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotElementForgeOutput extends SlotItemHandler {
	public SlotElementForgeOutput(IItemHandler inventory, int index, int xPos, int yPos) {
		super(inventory, index, xPos, yPos);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return (par1ItemStack.getItem() == ItemRegistry.air_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.animal_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.chaos_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.death_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.earth_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.fire_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.life_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.light_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.lightning_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.metal_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.mind_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.order_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.plant_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.spirit_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.void_element_item)
				|| (par1ItemStack.getItem() == ItemRegistry.water_element_item);
	}
}
