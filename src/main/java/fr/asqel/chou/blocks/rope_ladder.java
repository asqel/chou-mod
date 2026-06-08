package fr.asqel.chou.blocks;

import fr.asqel.chou.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

public class rope_ladder extends LadderBlock {

    public rope_ladder() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).setId(ModBlocks.keyOfBlock("rope_ladder")).noOcclusion().forceSolidOff());
    }

    private boolean canAttachTo(final BlockGetter level, final BlockPos pos, final Direction direction) {
        BlockState blockState = level.getBlockState(pos);
        return blockState.isFaceSturdy(level, pos, direction);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = (Direction)state.getValue(FACING);
        BlockState above = level.getBlockState(pos.above());
        if (above.getBlock() == state.getBlock() && above.getValue(FACING) == direction)
            return true;

        return this.canAttachTo(level, pos.relative(direction.getOpposite()), direction);
    }

    @Override
    protected BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos pos, final Direction directionToNeighbour, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        if (!this.canSurvive(state, level, pos))
            return Blocks.AIR.defaultBlockState();
        return state;
    }
    
}
