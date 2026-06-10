package fr.asqel.chou;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<Item> COLORED_BOTTLES = create("colored_bottles");

    private static TagKey<Item> create(final String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Chou.MOD_ID, name));
    }
}
