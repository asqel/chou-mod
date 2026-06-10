package fr.asqel.chou.items;

import fr.asqel.chou.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class colored_bottle extends Item {

    public final String color;
    public colored_bottle(String color) {
        this.color = color;
        super(new Properties().setId(ModItems.keyOfItem(color + "_water_bottle")).stacksTo(64));
    }

    public static Item get_item_from_color(String color) {
        switch (color) {
            case "white":
            	return ModItems.WHITE_WATER_BOTTLE;
            case "light_gray":
            	return ModItems.LIGHT_GRAY_WATER_BOTTLE;
            case "gray":
            	return ModItems.GRAY_WATER_BOTTLE;
            case "black":
            	return ModItems.BLACK_WATER_BOTTLE;
            case "brown":
            	return ModItems.BROWN_WATER_BOTTLE;
            case "red":
            	return ModItems.RED_WATER_BOTTLE;
            case "orange":
            	return ModItems.ORANGE_WATER_BOTTLE;
            case "yellow":
            	return ModItems.YELLOW_WATER_BOTTLE;
            case "lime":
            	return ModItems.LIME_WATER_BOTTLE;
            case "green":
            	return ModItems.GREEN_WATER_BOTTLE;
            case "cyan":
            	return ModItems.CYAN_WATER_BOTTLE;
            case "light_blue":
            	return ModItems.LIGHT_BLUE_WATER_BOTTLE;
            case "blue":
            	return ModItems.BLUE_WATER_BOTTLE;
            case "purple":
            	return ModItems.PURPLE_WATER_BOTTLE;
            case "magenta":
            	return ModItems.MAGENTA_WATER_BOTTLE;
            case "pink":
            	return ModItems.PINK_WATER_BOTTLE;
        
            default:
                return Items.AIR;
        }
    }
    
}
