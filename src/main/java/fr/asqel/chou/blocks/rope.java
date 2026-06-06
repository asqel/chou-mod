package fr.asqel.chou.blocks;

import fr.asqel.chou.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class rope extends Block {

    public static final VoxelShape SHAPE = Block.box(7, 0, 7, 9, 16, 9);

    public rope(MapColor color, String id) {
        super(Properties.of().setId(ModBlocks.keyOfBlock(id)).mapColor(color).noCollision().instabreak().sound(SoundType.WOOL));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
