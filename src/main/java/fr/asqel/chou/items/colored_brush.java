package fr.asqel.chou.items;

import fr.asqel.chou.ModComponent;
import fr.asqel.chou.ModItems;
import fr.asqel.chou.ModTab;
import fr.asqel.chou.ModTags;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class colored_brush extends Item {
    public static final int MAX_DURA = 256;

    public colored_brush() {
        super(new Properties().stacksTo(1).setId(ModItems.keyOfItem("colored_brush")).durability(MAX_DURA).component(DataComponents.DAMAGE, MAX_DURA).component(ModComponent.COLOR_IDX, -1));
    }

    public static Integer get_color(Item item) {
        if (!(item instanceof colored_bottle bott))
            return 0;
        switch (bott.color) {
            case "white":
                return 0;
            case "light_gray":
                return 1;
            case "gray":
                return 2;
            case "black":
                return 3;
            case "brown":
                return 4;
            case "red":
                return 5;
            case "orange":
                return 6;
            case "yellow":
                return 7;
            case "lime":
                return 8;
            case "green":
                return 9;
            case "cyan":
                return 10;
            case "light_blue":
                return 11;
            case "blue":
                return 12;
            case "purple":
                return 13;
            case "magenta":
                return 14;
            case "pink":
                return 15;
            default:
                return 0;
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide())
            return InteractionResult.PASS;

        if (!player.getOffhandItem().is(ModTags.COLORED_BOTTLES))
            return InteractionResult.PASS;
        if (player.getMainHandItem().getItem() != ModItems.COLORED_BRUSH)
            return InteractionResult.PASS;

        ItemStack brush = player.getMainHandItem();
        ItemStack bottle = player.getOffhandItem();
        Integer current_dura = brush.getComponents().get(DataComponents.DAMAGE);
        System.out.println(current_dura);
        if (current_dura <= 0)
            return InteractionResult.PASS;
        if (brush.getComponents().get(ModComponent.COLOR_IDX) == -1)
            brush.applyComponents(DataComponentPatch.builder().set(ModComponent.COLOR_IDX, get_color(bottle.getItem())).build());
        if (brush.getComponents().get(ModComponent.COLOR_IDX) != get_color(bottle.getItem()))
            return InteractionResult.PASS;
    
        current_dura -= 16;
        if (current_dura < 0)
            current_dura = 0;
        brush.applyComponents(DataComponentPatch.builder().set(DataComponents.DAMAGE, current_dura).build());
        player.getInventory().clearOrCountMatchingItems(s -> {return s.getItem() == bottle.getItem();}, 1, null);
        player.swing(hand);
        return InteractionResult.PASS;
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide())
            return InteractionResult.PASS;
        // !TODO color block
        return super.useOn(context);
    }

    public static Block get_wool(Integer color) {

    }
}
