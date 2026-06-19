package fr.asqel.chou.client.entityModels;
// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import fr.asqel.chou.Chou;
import fr.asqel.chou.client.renderstates.canoe_renderstate;
import fr.asqel.chou.entity.canoe_entity;
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
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class canoe_model extends EntityModel<BoatRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Chou.MOD_ID, "canoe"), "main");
	private final ModelPart front;
	private final ModelPart left;
	private final ModelPart right;
	private final ModelPart back;
	private final ModelPart bottom;
	private final ModelPart paddle_left;
	private final ModelPart paddle_right;

	public canoe_model(ModelPart root) {
        super(root);
		this.front = root.getChild("front");
		this.left = root.getChild("left");
		this.right = root.getChild("right");
		this.back = root.getChild("back");
		this.bottom = root.getChild("bottom");
		this.paddle_left = root.getChild("paddle_left");
		this.paddle_right = root.getChild("paddle_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 22.0F, -15.0F, 0.0F, -3.1416F, 0.0F));

		PartDefinition front_r1 = front.addOrReplaceChild("front_r1", CubeListBuilder.create().texOffs(48, 68).addBox(-1.7F, -5.5F, -16.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -15.9F, 7.7F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition front_r2 = front.addOrReplaceChild("front_r2", CubeListBuilder.create().texOffs(12, 64).addBox(6.3F, -1.0F, -11.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 55).addBox(3.3F, -1.0F, -14.0F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.9F, 8.7F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition front_r3 = front.addOrReplaceChild("front_r3", CubeListBuilder.create().texOffs(12, 72).addBox(0.6F, -2.6F, -16.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, -16.9F, 8.4F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition front_r4 = front.addOrReplaceChild("front_r4", CubeListBuilder.create().texOffs(60, 19).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 4.5F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition front_r5 = front.addOrReplaceChild("front_r5", CubeListBuilder.create().texOffs(66, 79).addBox(-8.7F, -6.6F, -10.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(84, 76).addBox(-5.7F, -4.6F, -9.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(30, 64).addBox(-2.7F, -2.6F, -8.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, -16.0F, 8.7F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 19).addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 22.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_r1 = left.addOrReplaceChild("left_r1", CubeListBuilder.create().texOffs(78, 0).addBox(2.0F, -1.25F, 14.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(26, 76).addBox(0.0F, -2.25F, 17.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 75).addBox(-2.0F, -3.15F, 20.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(84, 49).addBox(-4.0F, -4.15F, 23.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.75F, -5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition left_r2 = left.addOrReplaceChild("left_r2", CubeListBuilder.create().texOffs(72, 65).addBox(-4.0F, -1.25F, 14.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(84, 18).addBox(2.0F, -4.15F, 23.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 55).addBox(0.0F, -3.15F, 20.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 19).addBox(-2.0F, -2.25F, 17.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.75F, -5.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 27).addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 22.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition right_r1 = right.addOrReplaceChild("right_r1", CubeListBuilder.create().texOffs(84, 67).addBox(2.0F, -4.0F, 23.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(80, 39).addBox(0.0F, -3.0F, 20.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(80, 29).addBox(-2.0F, -2.1F, 17.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(12, 80).addBox(-4.0F, -1.1F, 14.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.9F, -5.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right_r2 = right.addOrReplaceChild("right_r2", CubeListBuilder.create().texOffs(50, 79).addBox(2.0F, -1.25F, 14.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 79).addBox(0.0F, -2.25F, 17.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 79).addBox(-2.0F, -3.15F, 20.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(84, 58).addBox(-4.0F, -4.15F, 23.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.75F, -5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(4.0F, 22.0F, 15.0F));

		PartDefinition back_r1 = back.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-1.5F, -6.0F, -1.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 4.5F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition back_r2 = back.addOrReplaceChild("back_r2", CubeListBuilder.create().texOffs(60, 68).addBox(-3.5F, -4.0F, 14.9F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 15.0F, 9.5F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition back_r3 = back.addOrReplaceChild("back_r3", CubeListBuilder.create().texOffs(78, 10).addBox(-0.5F, -2.0F, 11.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(30, 68).addBox(5.5F, -1.0F, 16.5F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 10.6F, 9.5F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition back_r4 = back.addOrReplaceChild("back_r4", CubeListBuilder.create().texOffs(20, 55).addBox(2.5F, -1.0F, 13.4F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 10.5F, 9.5F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition back_r5 = back.addOrReplaceChild("back_r5", CubeListBuilder.create().texOffs(34, 64).addBox(-4.0F, -2.6F, 1.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, -9.0F, 10.0F, 0.0F, -1.5708F, -1.5708F));

		PartDefinition back_r6 = back.addOrReplaceChild("back_r6", CubeListBuilder.create().texOffs(84, 84).addBox(-1.0F, -4.6F, 0.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, -7.0F, 4.0F, 0.0F, -1.5708F, -1.5708F));

		PartDefinition back_r7 = back.addOrReplaceChild("back_r7", CubeListBuilder.create().texOffs(62, 79).addBox(2.0F, -6.6F, -1.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, -5.0F, -2.0F, 0.0F, -1.5708F, -1.5708F));

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, -9.0F, -3.0F, 36.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 21.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition paddle_left = partdefinition.addOrReplaceChild("paddle_left", CubeListBuilder.create().texOffs(0, 35).addBox(-0.7495F, -0.5F, -12.75F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(40, 55).addBox(-0.7505F, -3.5F, 0.25F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.7908F, 18.6575F, -5.3273F, 2.1642F, 0.8727F, 2.8798F));

		PartDefinition paddle_right = partdefinition.addOrReplaceChild("paddle_right", CubeListBuilder.create().texOffs(40, 35).addBox(-1.2505F, -0.5F, -12.75F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(56, 55).addBox(-0.2495F, -3.5F, 0.25F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.7908F, 18.6575F, -5.3273F, 2.1642F, -0.8727F, -2.8798F));


		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(BoatRenderState state) {
		if (state.rowingTimeLeft == 0)
			this.paddle_left.resetPose();
		else
			animatePaddle(state.rowingTimeLeft, 1, this.paddle_left);

		if (state.rowingTimeRight == 0)
			this.paddle_right.resetPose();
		else
			animatePaddle(state.rowingTimeRight, 1, this.paddle_right);
	}

   	private static void animatePaddle(final float time, final int side, final ModelPart paddle) {
    	paddle.xRot = -Mth.clampedLerp((Mth.sin((double)(-time)) + 1.0F) / 2.0F, (-(float)Math.PI / 3F), -0.2617994F);
    	paddle.yRot = Mth.clampedLerp((Mth.sin((double)(-time + 1.0F)) + 1.0F) / 2.0F, (-(float)Math.PI / 4F), ((float)Math.PI / 4F));
    	if (side == 1) {
        	paddle.yRot = (float)Math.PI - paddle.yRot;
      	}
   }

	//@Override
	//public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	//}

	//@Override
	//public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
	//	front.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	//	left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	//	right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	//	back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	//	bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	//	paddle_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	//	paddle_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	//}
}
