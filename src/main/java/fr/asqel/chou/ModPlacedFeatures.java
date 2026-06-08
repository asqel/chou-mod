package fr.asqel.chou;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BALL_FLOWERS = placedFeature("ball_flowers_placed");
    public static final ResourceKey<PlacedFeature> STARFISH_FLOWERS = placedFeature("starfish_flowers_placed");

    public static ResourceKey<PlacedFeature> placedFeature(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Chou.MOD_ID, id));
    }
}
