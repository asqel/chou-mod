package fr.asqel.chou.blocks;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import fr.asqel.chou.ModBlocks;
import fr.asqel.chou.blockentity.sieve_blockentity;
import fr.asqel.chou.items.colored_bottle;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class sieve extends BaseEntityBlock {

    public static final int MAX_PROGRESS = 10;
    public static final float BREAK_PROBA = 0.03f;
    public static final BooleanProperty HAS_BOTTLE = BooleanProperty.create("has_bottle");

    public sieve() {
        super(BlockBehaviour.Properties.of().setId(ModBlocks.keyOfBlock("sieve")).sound(SoundType.WOOD).strength(2f).noOcclusion().randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_BOTTLE, false));
    }

    public sieve(Properties pr) {
        super(pr);
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_BOTTLE, false));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(HAS_BOTTLE);
    }
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HAS_BOTTLE, false);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new sieve_blockentity(pos, state);
    }

    @Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(sieve::new);
	}

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide())
            return InteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof sieve_blockentity sieve_ent)) {
		    return InteractionResult.PASS;
	    }

        if (player.getItemInHand(hand).getItem() == Items.GLASS_BOTTLE) {
            int to_put = Math.min(64 - sieve_ent.getItem(0).count(), player.getInventory().countItem(Items.GLASS_BOTTLE));

            if (sieve_ent.getItem(0).isEmpty() && to_put != 0)
                sieve_ent.setItem(0, new ItemStack(Items.GLASS_BOTTLE, to_put));
            else if (to_put != 0)
                sieve_ent.getItem(0).setCount(sieve_ent.getItem(0).count() + to_put);
            player.getInventory().removeItem(new ItemStack(Items.GLASS_BOTTLE, to_put));
            player.getInventory().clearOrCountMatchingItems(s -> {return s.getItem() == Items.GLASS_BOTTLE;}, to_put, null);
        }
        else if(sieve_ent.getItem(1).count() != 0 && player.getItemInHand(hand).isEmpty()) {
            ItemStack s = sieve_ent.getItem(1).copy();
            if (!player.getInventory().add(s)) {
                player.drop(s, false, false);
            }
            sieve_ent.getItem(1).setCount(0);
        }
        else
            return InteractionResult.PASS;
        
        level.setBlock(pos, state.setValue(HAS_BOTTLE, !sieve_ent.getItem(0).isEmpty()), UPDATE_ALL);

        player.swing(hand); 
        return InteractionResult.SUCCESS;
    }
    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) {
            super.spawnAfterBreak(state, level, pos, tool, dropExperience);
            return ;
        }
        if (!(be instanceof sieve_blockentity sieve_ent)) {
            super.spawnAfterBreak(state, level, pos, tool, dropExperience);
            return ;
        }

        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), sieve_ent.getItem(0)));
        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), sieve_ent.getItem(1)));
        sieve_ent.getItem(0).setCount(0);
        sieve_ent.getItem(1).setCount(0);
        super.spawnAfterBreak(state, level, pos, tool, dropExperience);
    }

    public static boolean is_water(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() == Blocks.WATER)
            return true;
        if (state.getOptionalValue(BlockStateProperties.WATERLOGGED).orElse(false))
            return true;
        return false;
    }

    public static boolean is_wool(ServerLevel level, BlockPos pos, boolean white_allowed) {
        BlockState state = level.getBlockState(pos);
        if (!state.is(BlockTags.WOOL))
            return false;
        if (!white_allowed && state.getBlock() == Blocks.WHITE_WOOL)
            return false;
        return true;
    }

    public static String get_wool_color(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        String res = state.getBlock().getDescriptionId();
        res = res.replace("block.minecraft.", "");
        return res.replace("_wool", "");

    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be == null)
            return ;
        if (!(be instanceof sieve_blockentity sieve_ent))
            return ;
        if (sieve_ent.getItem(0).isEmpty() || sieve_ent.getItem(1).count() == 64)
            return ;
        BlockPos wool_pos = pos.above().above();
        BlockPos water_pos = wool_pos.above();
        if (!is_water(level, water_pos))
            return ;
        if (!is_wool(level, wool_pos, false))
            return ;
        String wool_color = get_wool_color(level, wool_pos);
        ItemStack stack = sieve_ent.getItem(1);

        if (sieve_ent.progress == MAX_PROGRESS) {
            if (stack.isEmpty())
                sieve_ent.items.set(1, new ItemStack(colored_bottle.get_item_from_color(wool_color)));
            else if (stack.getItem() == colored_bottle.get_item_from_color(wool_color))
                sieve_ent.getItem(1).setCount(sieve_ent.getItem(1).count() + 1);
            else
                return ;
            sieve_ent.getItem(0).setCount(sieve_ent.getItem(0).count() - 1);
            sieve_ent.progress = 0;
            if (random.nextFloat() < BREAK_PROBA)
                level.setBlock(wool_pos, Blocks.WHITE_WOOL.defaultBlockState(), UPDATE_ALL);
        }
        level.setBlock(pos, state.setValue(HAS_BOTTLE, !sieve_ent.getItem(0).isEmpty()), UPDATE_ALL);
        sieve_ent.progress++;
    }
}
