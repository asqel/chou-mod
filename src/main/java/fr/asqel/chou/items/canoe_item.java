package fr.asqel.chou.items;

import fr.asqel.chou.ModEntities;
import fr.asqel.chou.ModItems;
import net.minecraft.world.item.BoatItem;

public class canoe_item extends BoatItem {

    public canoe_item() {
        super(ModEntities.CANOE, new Properties().setId(ModItems.keyOfItem("canoe")).stacksTo(1));
    }
    
}
