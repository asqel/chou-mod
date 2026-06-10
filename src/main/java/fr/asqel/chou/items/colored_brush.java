package fr.asqel.chou.items;

import fr.asqel.chou.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class colored_brush extends Item {
    public static final int MAX_DURA = 256;
    public final String color;

    public colored_brush(String color) {
        this.color = color;
        super(new Properties().stacksTo(1).setId(ModItems.keyOfItem(color + "_colored_brush")).durability(MAX_DURA).component(DataComponents.DAMAGE, MAX_DURA));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide())
            return InteractionResult.PASS;
        // !TODO check offhand for bottle and increase durability (loss if no room except 0)
        return super.use(level, player, hand);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        // !TODO color block
        return super.useOn(context);
    }
}
