package fr.asqel.chou.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class stained_glass_pane {
	public static Block from_color(int color) {
		switch (color) {
			case 0:
				return Blocks.WHITE_STAINED_GLASS_PANE;
			case 1:
				return Blocks.LIGHT_GRAY_STAINED_GLASS_PANE;
			case 2:
				return Blocks.GRAY_STAINED_GLASS_PANE;
			case 3:
				return Blocks.BLACK_STAINED_GLASS_PANE;
			case 4:
				return Blocks.BROWN_STAINED_GLASS_PANE;
			case 5:
				return Blocks.RED_STAINED_GLASS_PANE;
			case 6:
				return Blocks.ORANGE_STAINED_GLASS_PANE;
			case 7:
				return Blocks.YELLOW_STAINED_GLASS_PANE;
			case 8:
				return Blocks.LIME_STAINED_GLASS_PANE;
			case 9:
				return Blocks.GREEN_STAINED_GLASS_PANE;
			case 10:
				return Blocks.CYAN_STAINED_GLASS_PANE;
			case 11:
				return Blocks.LIGHT_BLUE_STAINED_GLASS_PANE;
			case 12:
				return Blocks.BLUE_STAINED_GLASS_PANE;
			case 13:
				return Blocks.PURPLE_STAINED_GLASS_PANE;
			case 14:
				return Blocks.MAGENTA_STAINED_GLASS_PANE;
			case 15:
				return Blocks.PINK_STAINED_GLASS_PANE;
			default:
				return Blocks.WHITE_STAINED_GLASS_PANE;
		}
	}
}
