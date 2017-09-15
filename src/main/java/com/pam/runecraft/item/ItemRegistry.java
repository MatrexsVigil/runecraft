package com.pam.runecraft.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pam.runecraft.RuneCraft;
import com.pam.runecraft.bauble.ItemBaubleBelt;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	
	public static final List<Item> itemlist = new ArrayList<Item>();
	public static final HashMap<String, Item> items = new HashMap<String, Item>();
	
	public static Item air_element_item;
	public static Item animal_element_item;
	public static Item chaos_element_item;
	public static Item death_element_item;
	public static Item earth_element_item;
	public static Item fire_element_item;
	public static Item life_element_item;
	public static Item light_element_item;
	public static Item lightning_element_item;
	public static Item metal_element_item;
	public static Item mind_element_item;
	public static Item order_element_item;
	public static Item plant_element_item;
	public static Item spirit_element_item;
	public static Item void_element_item;
	public static Item water_element_item;
	
	public static Item changespellitem;
	public static Item createspellitem;
	public static Item movespellitem;
	public static Item protectspellitem;
	public static Item evokespellitem;
	public static Item healspellitem;
	public static Item infusespellitem;
	public static Item commandspellitem;
	
	public static Item blankspellitem;
	
	
	public static Item leatherbeltitem;
	public static Item charmstoneitem;
	public static Item charmfuramuletitem;
	public static Item goldringitem;
	public static Item golddiademitem;
	public static Item goldnecklaceitem;
	
	public static Item belt_move_air_item;
	
	public static boolean initialized = false;

	public static void registerItems() {
		
		registerElements();
		//registerRunes();
		//registerSpells();
		registerBaubles();
			
		initialized = true;
	}
	
	private static void registerElements(){
		 air_element_item = registerGenericItem("air_element_item");
		 animal_element_item = registerGenericItem("animal_element_item");
		 chaos_element_item = registerGenericItem("chaos_element_item");
		 death_element_item = registerGenericItem("death_element_item");
		 earth_element_item = registerGenericItem("earth_element_item");
		 fire_element_item = registerGenericItem("fire_element_item");
		 life_element_item = registerGenericItem("life_element_item");
		 light_element_item = registerGenericItem("light_element_item");
		 lightning_element_item = registerGenericItem("lightning_element_item");
		 metal_element_item = registerGenericItem("metal_element_item");
		 mind_element_item = registerGenericItem("mind_element_item");
		 order_element_item = registerGenericItem("order_element_item");
		 plant_element_item = registerGenericItem("plant_element_item");
		 spirit_element_item = registerGenericItem("spirit_element_item");
		 void_element_item = registerGenericItem("void_element_item");
		 water_element_item = registerGenericItem("water_element_item");
	}
	
	private static void registerRunes(){
		
	}
	
	private static void registerSpells(){
		
	}
	
	private static void registerBaubles(){
		belt_move_air_item = registerBeltItem("belt_move_air_item");
	}

	private static Item registerGenericItem(String registryName) {
		final Item item = new Item();

		return registerItem(item, registryName);
	}
	
	private static Item registerBeltItem(String registryName) {
		final Item item = new ItemBaubleBelt();

		return registerItem(item, registryName);
	}
	public static Item registerItem(Item item, String registryName) {
        item.setCreativeTab(RuneCraft.modTab);
        item.setRegistryName(registryName);
        item.setUnlocalizedName(registryName);
        itemlist.add(item);
        return item;
    }
	
	@SubscribeEvent
    public void onItemRegistry(RegistryEvent.Register<Item> e) {
        IForgeRegistry<Item> reg = e.getRegistry();
        reg.registerAll(itemlist.toArray(new Item[0]));     
	}
}
