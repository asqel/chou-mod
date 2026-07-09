package fr.asqel.chou.blocks;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import fr.asqel.chou.ModBlockEntities;
import fr.asqel.chou.ModBlocks;
import fr.asqel.chou.blockentity.computer_blockentity;
import fr.asqel.chou.utils.Assembler;
import fr.asqel.chou.utils.Interpreter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WritableBookContent;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class computer extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public computer() {
        this(BlockBehaviour.Properties.of().setId(ModBlocks.keyOfBlock("computer")).noOcclusion().sound(SoundType.BONE_BLOCK).strength(2f));
    }

    public computer(BlockBehaviour.Properties pr) {
        super(pr);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide())
            return InteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof computer_blockentity be))
            return InteractionResult.PASS;

       if (be.pages.size() != 0) {
            ItemStack to_spawn = new ItemStack(Items.WRITABLE_BOOK, 1);

            List<Filterable<String>> lst = new ArrayList<>();
            for (int i = 0; i < be.pages.size(); i++)
                lst.add(new Filterable<String>(be.pages.get(i), Optional.ofNullable(null)));

            WritableBookContent content = new WritableBookContent(lst);
            to_spawn.applyComponents(DataComponentPatch.builder().set(DataComponents.WRITABLE_BOOK_CONTENT, content).set(DataComponents.CUSTOM_NAME, Component.literal(be.book_name)).build());

            level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5 + 1, pos.getZ() + 0.5, to_spawn));

            be.pages = new ArrayList<>();
            be.code.set_instructions(new int[0]);
            player.swing(hand);
            return InteractionResult.SUCCESS;
        }

        ItemStack stack = player.getItemInHand(hand);
        if (!stack.is(Items.WRITABLE_BOOK))
            return InteractionResult.FAIL;

        List<String> pages = stack.getComponents().get(DataComponents.WRITABLE_BOOK_CONTENT).getPages(false).toList();
        if (pages.size() == 0 || pages.get(0).length() == 0) {
            player.swing(hand);
            return InteractionResult.SUCCESS;
        }

        Assembler asm = new Assembler();
        int page_idx = 0;
        boolean has_error = false;
        for (String page: pages) {
            int line_idx = 0;
            for (String line: page.split("\n")) {
                String err = asm.build_line(line);
                if (!err.equals("")) {
                    player.sendSystemMessage(Component.literal(String.format("%d:%d: %s", page_idx + 1, line_idx + 1, err)));
                    has_error = true;
                }
                line_idx++;
            }
            page_idx++;
        }
        if (has_error) {
            player.swing(hand);
            return InteractionResult.SUCCESS;
        }

        String error = asm.finalize_code();
        if (!error.equals("")) {
            player.sendSystemMessage(Component.literal(error));
            player.swing(hand);
            return InteractionResult.SUCCESS;
        }

        be.pages = pages;
        be.book_name = "";
        Component compo = stack.getComponents().get(DataComponents.CUSTOM_NAME);
        if (compo != null) {
            String str = compo.tryCollapseToString();
            if (str != null)
                be.book_name = str;
        }
        be.code = new Interpreter();
        be.code.set_instructions(asm.output);

        player.getItemInHand(hand).setCount(0);
        player.swing(hand);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new computer_blockentity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(computer::new);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.COMPUTER_BLOCK_ENTITY, computer_blockentity::tick);
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }
    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.getDirectSignal(state, level, pos, direction);
    }
    
    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (!(level.getBlockEntity(pos) instanceof computer_blockentity be))
            return 0;

        int idx = 0;

        if (state.getValue(computer.FACING) == Direction.NORTH) 
            idx = 0;
        else if (state.getValue(computer.FACING) == Direction.EAST) 
            idx = 1;
        else if (state.getValue(computer.FACING) == Direction.SOUTH) 
            idx = 2;
        else if (state.getValue(computer.FACING) == Direction.WEST)
            idx = 3;
        else
            return 0;

        if (direction == Direction.NORTH) 
            idx -= 0;
        else if (direction == Direction.EAST) 
            idx -= 1;
        else if (direction == Direction.SOUTH) 
            idx -= 2;
        else if (direction == Direction.WEST)
            idx -= 3;

        idx = -idx;
    
        if (idx < 0)
            idx += 4;
        else if (idx >= 4)
            idx -= 4;

        return be.code.ports[idx];
    }
}