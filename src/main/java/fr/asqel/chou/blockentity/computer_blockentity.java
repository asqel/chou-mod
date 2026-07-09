package fr.asqel.chou.blockentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.asqel.chou.ModBlockEntities;
import fr.asqel.chou.blocks.computer;
import fr.asqel.chou.utils.Interpreter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WritableBookContent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class computer_blockentity extends BlockEntity {

    public Interpreter code = new Interpreter();
    public List<String> pages = new ArrayList<>();
    public String book_name = "";

    public computer_blockentity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMPUTER_BLOCK_ENTITY, pos, state);
    }
    
    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putString("chou:book_name", book_name);
        output.putInt("chou:pages_len", pages.size());
        for (int i = 0; i < pages.size(); i++)
            output.putString("chou:page_" + String.valueOf(i), pages.get(i));
        code.save_nbt(output);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        int pages_len = input.getIntOr("chou:pages_len", 0);

        this.book_name = input.getStringOr("chou:book_name", "");
        this.pages = new ArrayList<>();
        for (int i = 0; i < pages_len; i++)
            pages.add(input.getStringOr("chou:page_" + String.valueOf(i), ""));
        code.read_nbt(input);

        super.loadAdditional(input);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, computer_blockentity entity) {
        if (level.isClientSide())
            return ;

        Direction[] dirs = {
            Direction.NORTH,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST
        };

        int direction_idx = 0;
        if (blockState.getValue(computer.FACING) == Direction.NORTH) 
            direction_idx = 0;
        else if (blockState.getValue(computer.FACING) == Direction.EAST) 
            direction_idx = 1;
        else if (blockState.getValue(computer.FACING) == Direction.SOUTH) 
            direction_idx = 2;
        else if (blockState.getValue(computer.FACING) == Direction.WEST)
            direction_idx = 3;
    
        for (int i = 0; i < 4; i++)
            entity.code.ports_in[i] = (byte)level.getSignal(blockPos, dirs[(direction_idx + i) % 4]);

        entity.code.tick();
        level.updateNeighborsAt(blockPos, blockState.getBlock());
    }
    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (this.pages.size() != 0) {
            ItemStack to_spawn = new ItemStack(Items.WRITABLE_BOOK, 1);
            
            List<Filterable<String>> lst = new ArrayList<>();
            for (int i = 0; i < this.pages.size(); i++)
                lst.add(new Filterable<String>(this.pages.get(i), Optional.ofNullable(null)));
        
            WritableBookContent content = new WritableBookContent(lst);
            to_spawn.applyComponents(DataComponentPatch.builder().set(DataComponents.WRITABLE_BOOK_CONTENT, content).set(DataComponents.CUSTOM_NAME, Component.literal(this.book_name)).build());
    
            this.level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, to_spawn));
        }
        this.pages = new ArrayList<>();
        this.code.set_instructions(new int[0]);
        super.preRemoveSideEffects(pos, state);
    }
}
