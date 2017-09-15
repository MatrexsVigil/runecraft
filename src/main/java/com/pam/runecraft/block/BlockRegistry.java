package com.pam.runecraft.block;

import java.util.ArrayList;
import java.util.List;

import com.pam.runecraft.RuneCraft;
import com.pam.runecraft.item.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
public static final List<Block> blocks = new ArrayList<Block>();
	
	//Element Forge
	public static ElementForgeBlock elementforge;
	public static ItemBlock elementforgeItemBlock;


	private static boolean initialized = false;

	public static void initBlockRegistry() {
		registerElementForge();

		initialized = true;
	}



	private static void registerElementForge() {
		elementforge = new ElementForgeBlock();
		elementforgeItemBlock = new ItemBlock(elementforge);
		ItemRegistry.items.put(ElementForgeBlock.registryName, elementforgeItemBlock);
		registerBlock(ElementForgeBlock.registryName, elementforgeItemBlock, elementforge);
	}

	public static void registerBlock(String registerName, ItemBlock itemBlock, Block block) {
        block.setRegistryName(registerName);
        block.setUnlocalizedName(registerName);
        block.setCreativeTab(RuneCraft.modTab);
        block.setHardness(1.0F);
        blocks.add(block);

        if (itemBlock != null)
        {
        itemBlock.setRegistryName(registerName);
        itemBlock.setUnlocalizedName(registerName);
        ItemRegistry.itemlist.add(itemBlock);
        }
        return;
    }

    public static void registerBlock(String registerName, Block block) {
        final ItemBlock itemBlock = new ItemBlock(block);
        registerBlock(registerName, itemBlock, block);
    }

    
    @SubscribeEvent
    public void onBlockRegistry(RegistryEvent.Register<Block> e) {
        IForgeRegistry<Block> reg = e.getRegistry();
        reg.registerAll(blocks.toArray(new Block[0]));        
    }

}
