package fr.asqel.chou.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class terracotta {
	public static Block from_color(int color) {
		switch (color) {
			case 0:
				return Blocks.WHITE_TERRACOTTA;
			case 1:
				return Blocks.LIGHT_GRAY_TERRACOTTA;
			case 2:
				return Blocks.GRAY_TERRACOTTA;
			case 3:
				return Blocks.BLACK_TERRACOTTA;
			case 4:
				return Blocks.BROWN_TERRACOTTA;
			case 5:
				return Blocks.RED_TERRACOTTA;
			case 6:
				return Blocks.ORANGE_TERRACOTTA;
			case 7:
				return Blocks.YELLOW_TERRACOTTA;
			case 8:
				return Blocks.LIME_TERRACOTTA;
			case 9:
				return Blocks.GREEN_TERRACOTTA;
			case 10:
				return Blocks.CYAN_TERRACOTTA;
			case 11:
				return Blocks.LIGHT_BLUE_TERRACOTTA;
			case 12:
				return Blocks.BLUE_TERRACOTTA;
			case 13:
				return Blocks.PURPLE_TERRACOTTA;
			case 14:
				return Blocks.MAGENTA_TERRACOTTA;
			case 15:
				return Blocks.PINK_TERRACOTTA;
			default:
				return Blocks.WHITE_TERRACOTTA;
		}
	}
}
