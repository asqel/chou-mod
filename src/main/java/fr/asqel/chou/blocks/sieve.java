package fr.asqel.chou.blocks;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import fr.asqel.chou.ModBlocks;
import fr.asqel.chou.blockentity.sieve_blockentity;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys.Server;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

public class sieve extends BaseEntityBlock {

    public static final int MAX_PROGRESS = 10;
    public static final float PROGRESS_PROBA = 1;
    public static final float BREAK_PROBA = 0.03f;

    public sieve() {
        super(BlockBehaviour.Properties.of().setId(ModBlocks.keyOfBlock("sieve")).sound(SoundType.WOOD).noOcclusion().randomTicks());
    }

    public sieve(Properties pr) {
        super(pr);
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
        if (!(level.getBlockEntity(pos) instanceof sieve_blockentity sieve_ent)) {
		    return InteractionResult.PASS;
	    }

        if (sieve_ent.getItem(1).isEmpty()) {
            sieve_ent.setItem(1, sieve_ent.getItem(0));
            sieve_ent.setItem(0, new ItemStack(Items.AIR));
        }

        player.swing(hand); 
        return InteractionResult.SUCCESS;
    }

    public static boolean is_water(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() != Blocks.WATER)
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


        sieve_ent.progress++;
    }
}
