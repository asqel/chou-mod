package fr.asqel.chou;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTab {
    public static final ResourceKey<CreativeModeTab> CHOU_CREATIVE_TAB_KEY = ResourceKey.create(
    		BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Chou.MOD_ID, "chou")
    );

    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
    		.icon(() -> new ItemStack(ModItems.FLOWER_POT))
    		.title(Component.translatable("creativeTab.chou"))
    		.displayItems((params, output) -> {
    			output.accept(ModItems.GREAT_SWORD);
	            output.accept(ModItems.MEDUSA_LAMP);
	            output.accept(ModItems.ROPE_LADDER);
	            output.accept(ModItems.CHAIN_LAMP);
	            output.accept(ModItems.MAGENTA_BALL_FLOWER);
	            output.accept(ModItems.YELLOW_BALL_FLOWER);
				output.accept(ModItems.STARFISH_FLOWER);
	            output.accept(ModItems.FLOWER_POT);
	            output.accept(ModItems.HANGING_GARDEN);
	            output.accept(ModItems.CARDBOARD);
	            output.accept(ModItems.BLACK_ROPE);
	            output.accept(ModItems.WHITE_ROPE);
	            output.accept(ModItems.PLATE);
				output.accept(ModItems.SIEVE);
				output.accept(ModItems.WHITE_WATER_BOTTLE);
				output.accept(ModItems.LIGHT_GRAY_WATER_BOTTLE);
				output.accept(ModItems.GRAY_WATER_BOTTLE);
				output.accept(ModItems.BLACK_WATER_BOTTLE);
				output.accept(ModItems.BROWN_WATER_BOTTLE);
				output.accept(ModItems.RED_WATER_BOTTLE);
				output.accept(ModItems.ORANGE_WATER_BOTTLE);
				output.accept(ModItems.YELLOW_WATER_BOTTLE);
				output.accept(ModItems.LIME_WATER_BOTTLE);
				output.accept(ModItems.GREEN_WATER_BOTTLE);
				output.accept(ModItems.CYAN_WATER_BOTTLE);
				output.accept(ModItems.LIGHT_BLUE_WATER_BOTTLE);
				output.accept(ModItems.BLUE_WATER_BOTTLE);
				output.accept(ModItems.PURPLE_WATER_BOTTLE);
				output.accept(ModItems.MAGENTA_WATER_BOTTLE);
				output.accept(ModItems.PINK_WATER_BOTTLE);


    		})
    		.build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CHOU_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
    }
}
