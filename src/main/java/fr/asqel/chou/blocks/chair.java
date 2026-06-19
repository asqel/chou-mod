package fr.asqel.chou.blocks;

import java.util.List;
import java.util.Map;

import fr.asqel.chou.ModBlocks;
import fr.asqel.chou.ModEntities;
import fr.asqel.chou.entity.chair_entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class chair extends Block {
    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(Block.box(1, 0, 1, 15, 11, 15));
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public chair() {
        super(BlockBehaviour.Properties.of().setId(ModBlocks.keyOfBlock("chair")).sound(SoundType.WOOD).noOcclusion().strength(1.5f));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level instanceof ServerLevel server))
            return InteractionResult.SUCCESS;
        List<Entity> entities = level.getEntities(player, new AABB(pos));
        for (Entity entity : entities) {
            if (entity instanceof chair_entity) {
                if (entity.getPassengers().isEmpty()) {
                    player.startRiding(entity);
                    return InteractionResult.SUCCESS;
                }
                else
                    return InteractionResult.FAIL;
            }
        }
    
        chair_entity ent = new chair_entity(ModEntities.CHAIR, level);
        ent.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        server.addFreshEntity(ent);
        player.startRiding(ent);
        return InteractionResult.SUCCESS;
    }
}
