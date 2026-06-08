package fr.asqel.chou.blocks;

import fr.asqel.chou.ModBlocks;
import fr.asqel.chou.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class starfish_flower extends FlowerBlock implements BonemealableBlock {

    public starfish_flower() {
        super(MobEffects.WATER_BREATHING, 15, Properties.of().setId(ModBlocks.keyOfBlock("starfish_flower")).mapColor(MapColor.COLOR_RED).noCollision().instabreak().sound(SoundType.GRASS).offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
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
        ItemStack stack = new ItemStack(ModItems.STARFISH_FLOWER);
            
        ItemEntity ent = new ItemEntity(level,
            pos.getX() + random.nextFloat(),
            pos.getY() + random.nextFloat(),
            pos.getZ() + random.nextFloat(),
            stack
        );
        level.addFreshEntity(ent);
    }
    
}
