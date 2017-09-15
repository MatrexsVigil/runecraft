package com.pam.runecraft.proxy;

import com.pam.runecraft.block.BlockRegistry;
import com.pam.runecraft.item.ItemRegistry;
import com.pam.runecraft.tileentity.TileEntityElementForge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    	BlockRegistry.initBlockRegistry();
        MinecraftForge.EVENT_BUS.register(new BlockRegistry());
    	ItemRegistry.registerItems();
    	MinecraftForge.EVENT_BUS.register(new ItemRegistry());
    }

    public void init(FMLInitializationEvent e) {
    	onBlocksAndItemsLoaded();
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    public void onBlocksAndItemsLoaded() {
    	GameRegistry.registerTileEntity(TileEntityElementForge.class, "PamElementForge");
    }
}
