package fr.asqel.chou.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class candle {
	public static Block from_color(int color) {
		switch (color) {
			case 0:
				return Blocks.WHITE_CANDLE;
			case 1:
				return Blocks.LIGHT_GRAY_CANDLE;
			case 2:
				return Blocks.GRAY_CANDLE;
			case 3:
				return Blocks.BLACK_CANDLE;
			case 4:
				return Blocks.BROWN_CANDLE;
			case 5:
				return Blocks.RED_CANDLE;
			case 6:
				return Blocks.ORANGE_CANDLE;
			case 7:
				return Blocks.YELLOW_CANDLE;
			case 8:
				return Blocks.LIME_CANDLE;
			case 9:
				return Blocks.GREEN_CANDLE;
			case 10:
				return Blocks.CYAN_CANDLE;
			case 11:
				return Blocks.LIGHT_BLUE_CANDLE;
			case 12:
				return Blocks.BLUE_CANDLE;
			case 13:
				return Blocks.PURPLE_CANDLE;
			case 14:
				return Blocks.MAGENTA_CANDLE;
			case 15:
				return Blocks.PINK_CANDLE;
			default:
				return Blocks.WHITE_CANDLE;
		}
	}
}
