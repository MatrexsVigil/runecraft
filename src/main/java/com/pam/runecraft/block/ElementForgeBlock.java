package com.pam.runecraft.block;

import java.util.Random;

import com.pam.runecraft.ItemStackUtils;
import com.pam.runecraft.RuneCraft;
import com.pam.runecraft.gui.GuiHandler;
import com.pam.runecraft.tileentity.TileEntityElementForge;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class ElementForgeBlock extends Block implements ITileEntityProvider{
	
	public static final String registryName = "elementforge";
	private static boolean keepInventory;
	
	public ElementForgeBlock() {
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		setCreativeTab(RuneCraft.modTab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityElementForge();
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		keepInventory = true;
		if(!keepInventory) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if(tileentity instanceof TileEntityElementForge) {
			ItemStackUtils.dropInventoryItems(worldIn, pos,
					tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
			worldIn.updateComparatorOutputLevel(pos, this);
		}
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return true;
		}
		TileEntity te = world.getTileEntity(pos);
		if(!(te instanceof TileEntityElementForge)) {
			return false;
		}
		player.openGui(RuneCraft.instance, GuiHandler.GUIID_ELEMENTFORGE, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
