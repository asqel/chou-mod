package fr.asqel.chou;

import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModComponent {

    public static final DataComponentType<Integer> COLOR_IDX = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        Identifier.fromNamespaceAndPath(Chou.MOD_ID, "color_idx"),
        DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static void initialize() {}
    
}
