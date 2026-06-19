package fr.asqel.chou.entity;

import java.util.function.Supplier;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class canoe_entity extends Boat {

    public canoe_entity(EntityType<? extends Boat> type, Level level) {
        super(type, level, () -> Items.ACACIA_BOAT);
    }
    
}
