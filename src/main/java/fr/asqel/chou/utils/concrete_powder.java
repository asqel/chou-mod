package fr.asqel.chou.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class concrete_powder {
	public static Block from_color(int color) {
		switch (color) {
			case 0:
				return Blocks.WHITE_CONCRETE_POWDER;
			case 1:
				return Blocks.LIGHT_GRAY_CONCRETE_POWDER;
			case 2:
				return Blocks.GRAY_CONCRETE_POWDER;
			case 3:
				return Blocks.BLACK_CONCRETE_POWDER;
			case 4:
				return Blocks.BROWN_CONCRETE_POWDER;
			case 5:
				return Blocks.RED_CONCRETE_POWDER;
			case 6:
				return Blocks.ORANGE_CONCRETE_POWDER;
			case 7:
				return Blocks.YELLOW_CONCRETE_POWDER;
			case 8:
				return Blocks.LIME_CONCRETE_POWDER;
			case 9:
				return Blocks.GREEN_CONCRETE_POWDER;
			case 10:
				return Blocks.CYAN_CONCRETE_POWDER;
			case 11:
				return Blocks.LIGHT_BLUE_CONCRETE_POWDER;
			case 12:
				return Blocks.BLUE_CONCRETE_POWDER;
			case 13:
				return Blocks.PURPLE_CONCRETE_POWDER;
			case 14:
				return Blocks.MAGENTA_CONCRETE_POWDER;
			case 15:
				return Blocks.PINK_CONCRETE_POWDER;
			default:
				return Blocks.WHITE_CONCRETE_POWDER;
		}
	}
}
