package fr.asqel.chou.items;

import fr.asqel.chou.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class great_sword extends Item {

    public great_sword() {
        super(new Properties().sword(ToolMaterial.IRON, 5, -2.7f).setId(ModItems.keyOfItem("great_sword")).durability(2000));
    }
    
}
