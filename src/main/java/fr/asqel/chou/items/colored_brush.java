package fr.asqel.chou.items;

import java.util.function.IntFunction;

import fr.asqel.chou.ModComponent;
import fr.asqel.chou.ModItems;
import fr.asqel.chou.ModTags;
import fr.asqel.chou.utils.candle;
import fr.asqel.chou.utils.carpet;
import fr.asqel.chou.utils.concrete;
import fr.asqel.chou.utils.concrete_powder;
import fr.asqel.chou.utils.stained_glass;
import fr.asqel.chou.utils.stained_glass_pane;
import fr.asqel.chou.utils.terracotta;
import fr.asqel.chou.utils.wool;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class colored_brush extends Item {
    public static final int MAX_DURA = 256;
    public static final String[] color_names = {
            "white",
            "light_gray",
            "gray",
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "lime",
            "green",
            "cyan",
            "light_blue",
            "blue",
            "purple",
            "magenta",
            "pink"
    };

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
        if (current_dura == MAX_DURA)
            brush.applyComponents(DataComponentPatch.builder().set(ModComponent.COLOR_IDX, -1).build());
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
        ItemStack hand = context.getItemInHand();
        if (hand.getItem() != ModItems.COLORED_BRUSH)
            return InteractionResult.PASS;
        if (hand.getComponents().get(DataComponents.DAMAGE) >= MAX_DURA)
            return InteractionResult.PASS;
        int color = hand.getComponents().get(ModComponent.COLOR_IDX);
        BlockState current_state = context.getLevel().getBlockState(context.getClickedPos());
        final Block to_replace;

        if (current_state.is(BlockTags.CANDLES))
            to_replace = candle.from_color(color);
        else if (current_state.is(BlockTags.WOOL_CARPETS))
            to_replace = carpet.from_color(color);
        else if (current_state.is(BlockTags.CONCRETE_POWDER))
            to_replace = concrete_powder.from_color(color);
        else if (current_state.is(ModTags.CONCRETES))
            to_replace = concrete.from_color(color);
        else if (current_state.is(ModTags.GLASS_PANE))
            to_replace = stained_glass_pane.from_color(color);
        else if (current_state.is(ModTags.GLASS))
            to_replace = stained_glass.from_color(color);
        else if (current_state.is(BlockTags.TERRACOTTA))
            to_replace = terracotta.from_color(color);
        else if (current_state.is(BlockTags.WOOL))
            to_replace = wool.from_color(color);
        else
            return InteractionResult.FAIL;
        BlockState new_state = to_replace.withPropertiesOf(current_state);
        context.getLevel().setBlock(context.getClickedPos(), new_state, Block.UPDATE_ALL);
        hand.applyComponents(DataComponentPatch.builder().set(DataComponents.DAMAGE, hand.getComponents().get(DataComponents.DAMAGE) + 1).build());
        if (hand.getComponents().get(DataComponents.DAMAGE) >= MAX_DURA)
            hand.applyComponents(DataComponentPatch.builder().set(ModComponent.COLOR_IDX, -1).build());

        context.getPlayer().swing(InteractionHand.MAIN_HAND);
        return InteractionResult.SUCCESS;
    }

    public static Block get_wool(Integer color) {
        return null;
    }
}
