package fr.asqel.chou;


import fr.asqel.chou.entity.canoe_entity;
import fr.asqel.chou.entity.chair_entity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {


    public static final EntityType<chair_entity> CHAIR = register("chair", EntityType.Builder.<chair_entity>of(chair_entity::new, MobCategory.MISC)
					.sized(0.75f, 0.73f));

    public static final EntityType<canoe_entity> CANOE = register("canoe", EntityType.Builder.<canoe_entity>of(canoe_entity::new, MobCategory.MISC)
					.sized(1.125f, 0.73f));

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
		ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Chou.MOD_ID, name));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
	}

    
    public static void initialize() {}
}
