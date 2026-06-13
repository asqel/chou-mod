package fr.asqel.chou.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class concrete {
	public static Block from_color(int color) {
		switch (color) {
			case 0:
				return Blocks.WHITE_CONCRETE;
			case 1:
				return Blocks.LIGHT_GRAY_CONCRETE;
			case 2:
				return Blocks.GRAY_CONCRETE;
			case 3:
				return Blocks.BLACK_CONCRETE;
			case 4:
				return Blocks.BROWN_CONCRETE;
			case 5:
				return Blocks.RED_CONCRETE;
			case 6:
				return Blocks.ORANGE_CONCRETE;
			case 7:
				return Blocks.YELLOW_CONCRETE;
			case 8:
				return Blocks.LIME_CONCRETE;
			case 9:
				return Blocks.GREEN_CONCRETE;
			case 10:
				return Blocks.CYAN_CONCRETE;
			case 11:
				return Blocks.LIGHT_BLUE_CONCRETE;
			case 12:
				return Blocks.BLUE_CONCRETE;
			case 13:
				return Blocks.PURPLE_CONCRETE;
			case 14:
				return Blocks.MAGENTA_CONCRETE;
			case 15:
				return Blocks.PINK_CONCRETE;
			default:
				return Blocks.WHITE_CONCRETE;
		}
	}
}
