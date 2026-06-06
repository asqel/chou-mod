package fr.asqel.chou.items;

import fr.asqel.chou.ModItems;
import net.minecraft.world.item.Item;

public class colored_bottle extends Item {

    public final String color;

    public colored_bottle(String color) {
        this.color = color;
        super(new Properties().setId(ModItems.keyOfItem(color + "_water_bottle")).stacksTo(64));
    }
    
}
