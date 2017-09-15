package com.pam.runecraft.gui;

import javax.annotation.Nullable;

import com.pam.runecraft.tileentity.TileEntityElementForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerElementForge extends Container {
	private TileEntityElementForge te;
	private int lastCookTime = 0;

    public ContainerElementForge(IInventory playerInventory, TileEntityElementForge te) {
        this.te = te;

        // This container references items out of our own inventory (the 9 slots we hold ourselves)
        // as well as the slots from the player inventory so that the user can transfer items between
        // both inventories. The two calls below make sure that slots are defined for both inventories.
        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory) {
        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 99;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 157;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        addSlotToContainer(new SlotElementForgeInput(itemHandler, 0, 8, 37));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 1, 44, 14));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 2, 44, 33));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 3, 44, 52));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 4, 44, 71));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 5, 80, 14));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 6, 80, 33));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 7, 80, 52));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 8, 80, 71));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 9, 116, 14));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 10, 116, 33));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 11, 116, 52));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 12, 116, 71));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 13, 152, 14));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 14, 152, 33));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 15, 152, 52));
        addSlotToContainer(new SlotElementForgeOutput(itemHandler, 16, 152, 71));
    }

    @Override
    public void updateProgressBar(int id, int data) {
        if (id == 0) {
        	te.cookTime = (short) data;
        }
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener crafting : listeners) {
            if (lastCookTime == te.cookTime) continue;
            crafting.sendWindowProperty(this, 0, te.cookTime);
        }
        lastCookTime = te.cookTime;
    }
    
    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < TileEntityElementForge.SIZE) {
                if (!this.mergeItemStack(itemstack1, TileEntityElementForge.SIZE, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, TileEntityElementForge.SIZE, false)) {
                return null;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }



}
