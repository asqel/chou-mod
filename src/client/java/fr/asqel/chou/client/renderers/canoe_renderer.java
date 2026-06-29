package fr.asqel.chou.client.renderers;

import org.joml.AxisAngle4d;
import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.asqel.chou.Chou;
import fr.asqel.chou.client.ModEntityModelLayers;
import fr.asqel.chou.client.entityModels.canoe_model;
import fr.asqel.chou.client.renderstates.canoe_renderstate;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;

public class canoe_renderer extends AbstractBoatRenderer {
	
	private final EntityModel<BoatRenderState> model;
   private final Model.Simple waterPatchModel;

	public canoe_renderer(Context context) {
		super(context, Identifier.fromNamespaceAndPath(Chou.MOD_ID, "textures/entity/canoe.png"));
		this.waterPatchModel = new Model.Simple(context.bakeLayer(ModEntityModelLayers.CANOE_PATCH), (t) -> RenderTypes.waterMask());
		this.model = new canoe_model(context.bakeLayer(canoe_model.LAYER_LOCATION));
	}

	@Override
	protected EntityModel<BoatRenderState> model() {
		return this.model;
	}

	@Override
	public void submit(BoatRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
			CameraRenderState camera) {
		//poseStack.translate(0, 1.25, 0);
		//poseStack.rotateAround(new Quaternionf(new AxisAngle4d(Math.toRadians(90), 0, 1, 0)), 0, 0, 0);
		//this.model.getChildPart("front").y = 1.25f;
		//this.model.getChildPart("left").y = 1.25f;
		//this.model.getChildPart("right").y = 1.25f;
		//this.model.getChildPart("back").y = 1.25f;
		//this.model.getChildPart("bottom").y = 1.25f;

		//float rot = (float)Math.toRadians(90);

		//this.model.getChildPart("front").yRot = rot;
		//this.model.getChildPart("left").yRot = rot;
		//this.model.getChildPart("right").yRot = rot;
		//this.model.getChildPart("back").yRot = rot;
		//this.model.getChildPart("bottom").yRot = rot;

		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	protected void submitTypeAdditions(final BoatRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords) {
    	if (!state.isUnderWater || true) {
        	submitNodeCollector.submitModel(waterPatchModel, Unit.INSTANCE, poseStack, this.texture, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, (ModelFeatureRenderer.CrumblingOverlay)null);
    	}
	}


}
