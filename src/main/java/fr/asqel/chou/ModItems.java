package fr.asqel.chou;

import com.google.common.base.Supplier;

import fr.asqel.chou.items.colored_bottle;
import fr.asqel.chou.items.great_sword;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModItems {

	private static Item register(String name, Supplier<Item> blockFactory) {
		ResourceKey<Item> itemKey = ModItems.keyOfItem(name);
		return Registry.register(BuiltInRegistries.ITEM, itemKey, blockFactory.get());
    }

	private static Item register_block(String name, Block block) {
		ResourceKey<Item> itemKey = keyOfItem(name);
		return Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()));
	}

    public static final Item GREAT_SWORD = register("great_sword", great_sword::new);
	public static final Item MEDUSA_LAMP = register_block("medusa_lamp", ModBlocks.MEDUSA_LAMP);
	public static final Item ROPE_LADDER = register_block("rope_ladder", ModBlocks.ROPE_LADDER);
	public static final Item CHAIN_LAMP = register_block("chain_lamp", ModBlocks.CHAIN_LAMP);
	public static final Item YELLOW_BALL_FLOWER = register_block("yellow_ball_flower", ModBlocks.YELLOW_BALL_FLOWER);
	public static final Item MAGENTA_BALL_FLOWER = register_block("magenta_ball_flower", ModBlocks.MAGENTA_BALL_FLOWER);
	public static final Item FLOWER_POT = register_block("flower_pot", ModBlocks.FLOWER_POT);
	public static final Item HANGING_GARDEN = register_block("hanging_garden", ModBlocks.HANGING_GARDEN);
	public static final Item CARDBOARD = register_block("cardboard", ModBlocks.CARDBOARD);
	public static final Item BLACK_ROPE = register_block("black_rope", ModBlocks.BLACK_ROPE);
	public static final Item WHITE_ROPE = register_block("white_rope", ModBlocks.WHITE_ROPE);
	public static final Item PLATE = register_block("plate", ModBlocks.PLATE);
	public static final Item SIEVE = register_block("sieve", ModBlocks.SIEVE);

	public static final Item WHITE_WATER_BOTTLE = register("white_water_bottle", () -> {return new colored_bottle("white");});
	public static final Item LIGHT_GRAY_WATER_BOTTLE = register("light_gray_water_bottle", () -> {return new colored_bottle("light_gray");});
	public static final Item GRAY_WATER_BOTTLE = register("gray_water_bottle", () -> {return new colored_bottle("gray");});
	public static final Item BLACK_WATER_BOTTLE = register("black_water_bottle", () -> {return new colored_bottle("black");});
	public static final Item BROWN_WATER_BOTTLE = register("brown_water_bottle", () -> {return new colored_bottle("brown");});
	public static final Item RED_WATER_BOTTLE = register("red_water_bottle", () -> {return new colored_bottle("red");});
	public static final Item ORANGE_WATER_BOTTLE = register("orange_water_bottle", () -> {return new colored_bottle("orange");});
	public static final Item YELLOW_WATER_BOTTLE = register("yellow_water_bottle", () -> {return new colored_bottle("yellow");});
	public static final Item LIME_WATER_BOTTLE = register("lime_water_bottle", () -> {return new colored_bottle("lime");});
	public static final Item GREEN_WATER_BOTTLE = register("green_water_bottle", () -> {return new colored_bottle("green");});
	public static final Item CYAN_WATER_BOTTLE = register("cyan_water_bottle", () -> {return new colored_bottle("cyan");});
	public static final Item LIGHT_BLUE_WATER_BOTTLE = register("light_blue_water_bottle", () -> {return new colored_bottle("light_blue");});
	public static final Item BLUE_WATER_BOTTLE = register("blue_water_bottle", () -> {return new colored_bottle("blue");});
	public static final Item PURPLE_WATER_BOTTLE = register("purple_water_bottle", () -> {return new colored_bottle("purple");});
	public static final Item MAGENTA_WATER_BOTTLE = register("magenta_water_bottle", () -> {return new colored_bottle("magenta");});
	public static final Item PINK_WATER_BOTTLE = register("pink_water_bottle", () -> {return new colored_bottle("pink");});


	public static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Chou.MOD_ID, name));
	}

	public static void initialize() {}
}