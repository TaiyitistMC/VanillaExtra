package com.taiyitistmc.vanillaextra.client.renderer;

import com.taiyitistmc.vanillaextra.client.model.FriendlySkeletonModel;
import com.taiyitistmc.vanillaextra.common.entity.FriendlySkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlySkeletonRenderer extends HumanoidMobRenderer<FriendlySkeleton, FriendlySkeletonModel> {

    private static final ResourceLocation SKELETON_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png");

    public FriendlySkeletonRenderer(EntityRendererProvider.Context context) {
        this(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    public FriendlySkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation skeletonLayer, ModelLayerLocation innerModelLayer, ModelLayerLocation outerModelLayer) {
        this(context, innerModelLayer, outerModelLayer, new FriendlySkeletonModel(context.bakeLayer(skeletonLayer)));
    }

    public FriendlySkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation skeletonLayer, ModelLayerLocation innerModelLayer, FriendlySkeletonModel model) {
        super(context, model, 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new FriendlySkeletonModel(context.bakeLayer(skeletonLayer)), new FriendlySkeletonModel(context.bakeLayer(innerModelLayer)), context.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(FriendlySkeleton friendlySkeleton) {
        return SKELETON_LOCATION;
    }
}
