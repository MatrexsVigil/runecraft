package com.pam.runecraft.bauble;


import com.pam.runecraft.RuneCraft;
import com.pam.runecraft.item.ItemRegistry;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBaubleBelt extends Item implements IBauble{

	  public ItemBaubleBelt()
	    {
	        super();
	        setMaxStackSize(1);
	        setCreativeTab(RuneCraft.modTab);
	    }
	  
	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.BELT;
	}
	
	 @Override
	    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
	        if (itemstack.getItemDamage()==0) {
	        	if (itemstack.getItem() == ItemRegistry.belt_move_air_item)
	        	{
	        	        player.fallDistance = 0;
	        	}
	        }
	    }

	    @Override
	    public boolean hasEffect(ItemStack par1ItemStack) {
	        return false;
	    }

	    @Override
	    public EnumRarity getRarity(ItemStack par1ItemStack) {
	        return EnumRarity.UNCOMMON;
	    }
	    
	    @Override
	    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	        if(!world.isRemote) {
	            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
	            for(int i = 0; i < baubles.getSlots(); i++)
	                if((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
	                    baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
	                    if(!player.capabilities.isCreativeMode){
	                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
	                    }
	                    onEquipped(player.getHeldItem(hand), player);
	                    break;
	                }
	        }
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	    }

}
