package fr.asqel.chou.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class carpet {
	public static Block from_color(int color) {
		switch (color) {
			case 0:
				return Blocks.WHITE_CARPET;
			case 1:
				return Blocks.LIGHT_GRAY_CARPET;
			case 2:
				return Blocks.GRAY_CARPET;
			case 3:
				return Blocks.BLACK_CARPET;
			case 4:
				return Blocks.BROWN_CARPET;
			case 5:
				return Blocks.RED_CARPET;
			case 6:
				return Blocks.ORANGE_CARPET;
			case 7:
				return Blocks.YELLOW_CARPET;
			case 8:
				return Blocks.LIME_CARPET;
			case 9:
				return Blocks.GREEN_CARPET;
			case 10:
				return Blocks.CYAN_CARPET;
			case 11:
				return Blocks.LIGHT_BLUE_CARPET;
			case 12:
				return Blocks.BLUE_CARPET;
			case 13:
				return Blocks.PURPLE_CARPET;
			case 14:
				return Blocks.MAGENTA_CARPET;
			case 15:
				return Blocks.PINK_CARPET;
			default:
				return Blocks.WHITE_CARPET;
		}
	}
}
