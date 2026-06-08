package fr.asqel.chou;

import java.util.function.Supplier;

import fr.asqel.chou.blocks.chain_lamp;
import fr.asqel.chou.blocks.flower_pot;
import fr.asqel.chou.blocks.medusa_lamp;
import fr.asqel.chou.blocks.plate;
import fr.asqel.chou.blocks.rope_ladder;
import fr.asqel.chou.blocks.ball_flower;
import fr.asqel.chou.blocks.cardboard;
import fr.asqel.chou.blocks.hanging_garden;
import fr.asqel.chou.blocks.rope;
import fr.asqel.chou.blocks.sieve;
import fr.asqel.chou.blocks.starfish_flower;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {

	private static Block register(String name, Supplier<Block> blockFactory) {
		ResourceKey<Block> blockKey = keyOfBlock(name);
		Block block = blockFactory.get();

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	public static final Block MEDUSA_LAMP = register("medusa_lamp", medusa_lamp::new);
	public static final Block ROPE_LADDER = register("rope_ladder", rope_ladder::new);
	public static final Block CHAIN_LAMP = register("chain_lamp", chain_lamp::new);
	public static final Block YELLOW_BALL_FLOWER = register("yellow_ball_flower", () -> {return new ball_flower(MapColor.COLOR_YELLOW, "yellow_ball_flower");});
	public static final Block MAGENTA_BALL_FLOWER = register("magenta_ball_flower", () -> {return new ball_flower(MapColor.COLOR_MAGENTA, "magenta_ball_flower");});
	public static final Block FLOWER_POT = register("flower_pot", flower_pot::new);
	public static final Block HANGING_GARDEN = register("hanging_garden", hanging_garden::new);
	public static final Block CARDBOARD = register("cardboard", cardboard::new);
	public static final Block BLACK_ROPE = register("black_rope", () -> {return new rope(MapColor.COLOR_BLACK, "black_rope");});
	public static final Block WHITE_ROPE = register("white_rope", () -> {return new rope(MapColor.NONE, "white_rope");});
	public static final Block PLATE = register("plate", plate::new);
	public static final Block SIEVE = register("sieve", sieve::new);
	public static final Block STARFISH_FLOWER = register("starfish_flower", starfish_flower::new);
	public static final Block YELLOW_BALL_FLOWER_POTTED = register("yellow_ball_flower_potted", () -> {return new FlowerPotBlock(YELLOW_BALL_FLOWER, Blocks.flowerPotProperties().setId(keyOfBlock("yellow_ball_flower_potted")));});
	public static final Block MAGENTA_BALL_FLOWER_POTTED = register("magenta_ball_flower_potted", () -> {return new FlowerPotBlock(MAGENTA_BALL_FLOWER, Blocks.flowerPotProperties().setId(keyOfBlock("magenta_ball_flower_potted")));});

	public static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Chou.MOD_ID, name));
	}

	public static void initialize() {}
}
