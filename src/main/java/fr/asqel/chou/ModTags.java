package fr.asqel.chou;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Item> COLORED_BOTTLES = create("colored_bottles");
    public static final TagKey<Block> CONCRETES = create("c", "concretes");
    public static final TagKey<Block> GLASS = create("c", "glass_blocks");
    public static final TagKey<Block> GLASS_PANE = create("c", "glass_panes");

    private static TagKey<Item> create(final String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Chou.MOD_ID, name));
    }
    private static TagKey<Block> create(final String namespace, final String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(namespace, name));
    }
}
