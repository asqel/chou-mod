package fr.asqel.chou.blockentity;

import org.jspecify.annotations.Nullable;

import fr.asqel.chou.ModBlockEntities;
import fr.asqel.chou.utils.ImplementedContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class sieve_blockentity extends BlockEntity implements ImplementedContainer, WorldlyContainer {
    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    public int progress = 0;

    public sieve_blockentity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SIEVE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }
    @Override
    protected void loadAdditional(ValueInput input) {
	    super.loadAdditional(input);
	    ContainerHelper.loadAllItems(input, this.items);
        this.progress = input.getInt("progress").orElse(0);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
	    ContainerHelper.saveAllItems(output, this.items);
        output.putInt("progress", this.progress);
	    super.saveAdditional(output);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.UP)
            return new int[] {0};
        if (side == Direction.DOWN)
            return new int[] {1};
    	return new int[] {0, 1};
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        if (slot == 1)
            return false;
        if (stack.getItem() != Items.GLASS_BOTTLE)
            return false;
    	return dir != Direction.DOWN;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
    	return dir != Direction.UP;
    }
}
