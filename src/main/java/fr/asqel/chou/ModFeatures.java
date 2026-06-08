package fr.asqel.chou;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModFeatures {

    
    public static void add_feature(TagKey<Biome> tag, ResourceKey<PlacedFeature> feature) {
        BiomeModifications.addFeature(BiomeSelectors.tag(tag), GenerationStep.Decoration.VEGETAL_DECORATION, feature);
    }

    static void initialize() {
        add_feature(BiomeTags.IS_OVERWORLD, ModPlacedFeatures.BALL_FLOWERS);
        add_feature(BiomeTags.IS_OVERWORLD, ModPlacedFeatures.STARFISH_FLOWERS);
    }
}
