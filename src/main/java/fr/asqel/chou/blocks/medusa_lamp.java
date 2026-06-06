package fr.asqel.chou.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import fr.asqel.chou.ModBlocks;

public class medusa_lamp extends Block{

    public static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 16, 12);
    public medusa_lamp() {
        super(BlockBehaviour.Properties.of().setId(ModBlocks.keyOfBlock("medusa_lamp")).sound(SoundType.SPONGE).lightLevel((n) -> {return 15;}).noOcclusion().forceSolidOff());
    }
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
}
