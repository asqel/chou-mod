package fr.asqel.chou.blocks;

import com.jcraft.jorbis.Block;

import fr.asqel.chou.ModBlocks;
import fr.asqel.chou.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ball_flower extends FlowerBlock implements BonemealableBlock{

    private final String flower_name;
    public ball_flower(MapColor color, String id) {
        this.flower_name = id;
        super(MobEffects.GLOWING, 30, Properties.of().setId(ModBlocks.keyOfBlock(id)).mapColor(color).noCollision().instabreak().sound(SoundType.GRASS).offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        final ItemStack stack;
        if (this.flower_name.equals("yellow_ball_flower"))
            stack = new ItemStack(ModItems.YELLOW_BALL_FLOWER);
        else if (this.flower_name.equals("magenta_ball_flower"))
            stack = new ItemStack(ModItems.MAGENTA_BALL_FLOWER);
        else
            stack = new ItemStack(Items.BONE_MEAL);
        
            
        ItemEntity ent = new ItemEntity(level,
            pos.getX() + random.nextFloat(),
            pos.getY() + random.nextFloat(),
            pos.getZ() + random.nextFloat(),
            stack
        );
        level.addFreshEntity(ent);
    }
}
