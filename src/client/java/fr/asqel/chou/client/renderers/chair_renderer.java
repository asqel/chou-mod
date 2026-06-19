package fr.asqel.chou.client.renderers;

import fr.asqel.chou.client.renderstates.chair_renderstate;
import fr.asqel.chou.entity.chair_entity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

public class chair_renderer extends EntityRenderer<chair_entity, chair_renderstate> {

    public chair_renderer(Context context) {
        super(context);
    }

    @Override
    public chair_renderstate createRenderState() {
        return new chair_renderstate();
    }
}
