package fr.asqel.chou.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class wool {
	public static Block from_color(int color) {
		switch (color) {
			case 0:
				return Blocks.WHITE_WOOL;
			case 1:
				return Blocks.LIGHT_GRAY_WOOL;
			case 2:
				return Blocks.GRAY_WOOL;
			case 3:
				return Blocks.BLACK_WOOL;
			case 4:
				return Blocks.BROWN_WOOL;
			case 5:
				return Blocks.RED_WOOL;
			case 6:
				return Blocks.ORANGE_WOOL;
			case 7:
				return Blocks.YELLOW_WOOL;
			case 8:
				return Blocks.LIME_WOOL;
			case 9:
				return Blocks.GREEN_WOOL;
			case 10:
				return Blocks.CYAN_WOOL;
			case 11:
				return Blocks.LIGHT_BLUE_WOOL;
			case 12:
				return Blocks.BLUE_WOOL;
			case 13:
				return Blocks.PURPLE_WOOL;
			case 14:
				return Blocks.MAGENTA_WOOL;
			case 15:
				return Blocks.PINK_WOOL;
			default:
				return Blocks.WHITE_WOOL;
		}
	}
}
