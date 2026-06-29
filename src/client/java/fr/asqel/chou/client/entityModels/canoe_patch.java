package fr.asqel.chou.client.entityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.BoatRenderState;

public class canoe_patch extends EntityModel<BoatRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart bb_main;

	public canoe_patch(ModelPart root) {
        super(root);
		this.bb_main = root.getChild("bone").getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0, -24, 0, 0, (float)Math.toRadians(90), 0));

		PartDefinition bb_main = bone.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(-40, -26).addBox(-8.0F, -5.0F, -14.0F, 16.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(-12, -2).addBox(-6.0F, -5.0F, -18.0F, 12.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(-7, -1).addBox(-4.0F, -5.0F, -21.0F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(-12, -2).addBox(-6.0F, -5.0F, 14.0F, 12.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(-7, -1).addBox(-4.0F, -5.0F, 18.0F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

}