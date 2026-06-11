package fr.asqel.chou.client;

import fr.asqel.chou.ModComponent;
import fr.asqel.chou.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.network.chat.Component;

public class ChouClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register(
			(stack, context, flag, lines) -> {

            if (stack.is(ModItems.COLORED_BRUSH)) {
				String color = "None";
				Integer color_idx = stack.getComponents().get(ModComponent.COLOR_IDX);
				if (color_idx < 16 && color_idx >= 0)
					color = String.valueOf(color_idx);
				
                lines.add(Component.translatable("tooltip.chou.brush." + color));
            }

        }
		);	
	}
}