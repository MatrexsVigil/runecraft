package com.pam.runecraft.tileentity;

import com.pam.runecraft.item.ItemRegistry;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityElementForge extends TileEntity implements ITickable{
	
	private ItemStackHandler itemstackhandlerinput = new ItemStackHandler(17);
	public static final int SIZE = 17;
	public int AIR;
	public int ANIMAL;
	public int CHAOS;
	public int DEATH;
	public int EARTH;
	public int FIRE;
	public int LIFE;
	public int LIGHT;
	public int LIGHTNING;
	public int METAL;
	public int MIND;
	public int ORDER;
	public int PLANT;
	public int SPIRIT;
	public int VOID;
	public int WATER;
	public int cookTime;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemstackhandlerinput);
		}
		return super.getCapability(capability, facing);
	}
	
	// This item handler will hold our nine inventory slots
    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
        	TileEntityElementForge.this.markDirty();
        }
    };
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		itemstackhandlerinput.deserializeNBT((NBTTagCompound) compound.getTag("Input"));
		//itemstackhandleroutput.deserializeNBT((NBTTagCompound) compound.getTag("Output"));
		cookTime = compound.getInteger("cookTime");
		this.AIR = compound.getInteger("airTotal");
		this.ANIMAL = compound.getInteger("animalTotal");
		this.CHAOS = compound.getInteger("chaosTotal");
		this.DEATH = compound.getInteger("deathTotal");
		this.EARTH = compound.getInteger("earthTotal");
		this.FIRE = compound.getInteger("fireTotal");
		this.LIFE = compound.getInteger("lifeTotal");
		this.LIGHT = compound.getInteger("lightTotal");
		this.LIGHTNING = compound.getInteger("lightningTotal");
		this.METAL = compound.getInteger("metalTotal");
		this.MIND = compound.getInteger("mindTotal");
		this.ORDER = compound.getInteger("orderTotal");
		this.PLANT = compound.getInteger("plantTotal");
		this.SPIRIT = compound.getInteger("spiritTotal");
		this.VOID = compound.getInteger("voidTotal");
		this.WATER = compound.getInteger("waterTotal");
	}

	@Override
	@MethodsReturnNonnullByDefault
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setTag("Input", itemstackhandlerinput.serializeNBT());
		compound.setInteger("airTotal", (short)this.AIR);
		compound.setInteger("animalTotal", (short)this.ANIMAL);
		compound.setInteger("chaosTotal", (short)this.CHAOS);
		compound.setInteger("deathTotal", (short)this.DEATH);
		compound.setInteger("earthTotal", (short)this.EARTH);
		compound.setInteger("fireTotal", (short)this.FIRE);
		compound.setInteger("lifeTotal", (short)this.LIFE);
		compound.setInteger("lightTotal", (short)this.LIGHT);
		compound.setInteger("lightningTotal", (short)this.LIGHTNING);
		compound.setInteger("metalTotal", (short)this.METAL);
		compound.setInteger("mindTotal", (short)this.MIND);
		compound.setInteger("orderTotal", (short)this.ORDER);
		compound.setInteger("plantTotal", (short)this.PLANT);
		compound.setInteger("spiritTotal", (short)this.SPIRIT);
		compound.setInteger("voidTotal", (short)this.VOID);
		compound.setInteger("waterTotal", (short)this.WATER);
		//compound.setTag("Output", itemstackhandleroutput.serializeNBT());

		return compound;
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	@SideOnly(value = Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		return cookTime * scale / 125;
	}
	
	 public int getInventoryStackLimit()
	 {
	    return 64;
	 }

	 @Override
		public void update() {
			boolean needsUpdate = false;

			if(world.isRemote)
				return;

			if(canRun()) {
				++cookTime;

				if(cookTime >= 125) {
					cookTime = 0;
					createElement();
					needsUpdate = true;
				}
			}
			else {
				cookTime = 0;
			}

			if(needsUpdate != cookTime > 0) {
				needsUpdate = true;
			}

			if(needsUpdate) {
				markDirty();
				world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
			}
		}
	 
	 private boolean canRun() {
		 if(itemstackhandlerinput.getStackInSlot(0).isEmpty())
		 {
				return false;
		 }
		 
		 	return true;
		}
	 
	 private ItemStack createElement() {
		  ItemStack output = itemstackhandlerinput.getStackInSlot(1);
		  if (output.isEmpty() || output.getCount() < output.getMaxStackSize()) 
		  {
			  if(itemstackhandlerinput.getStackInSlot(0).getItem() == Items.FEATHER)
		    {
		      itemstackhandlerinput.getStackInSlot(0).shrink(1);
		      this.AIR += 8;
		    }
		  
		    if (this.AIR >= 64)
		    {
		      this.AIR -= 64;
		      if (output.isEmpty())
		      {
		        itemstackhandlerinput.setStackInSlot(1, new ItemStack(ItemRegistry.air_element_item));  
		      } else  { 
		        itemstackhandlerinput.getStackInSlot(1).grow(1);
		      }
		    }  
		  }
		return null;
		}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		final NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);

		return new SPacketUpdateTileEntity(getPos(), 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		// If we are too far away from this tile entity you cannot use it
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

	public String getGuiID() {
		return "runecraft:elementforge";
	}



	
}
