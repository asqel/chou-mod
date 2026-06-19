package fr.asqel.chou.client;

import fr.asqel.chou.Chou;
import fr.asqel.chou.client.entityModels.canoe_model;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModEntityModelLayers {
    public static final ModelLayerLocation CANOE = createMain("canoe");

	private static ModelLayerLocation createMain(String name) {
		return new ModelLayerLocation(Identifier.fromNamespaceAndPath(Chou.MOD_ID, name), "main");
	}

	public static void registerModelLayers() {
		ModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CANOE, canoe_model::createBodyLayer);
	}
    
}
