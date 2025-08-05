package com.taiyitistmc.vanillaextra.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.taiyitistmc.vanillaextra.client.model.BlackDogModel;
import com.taiyitistmc.vanillaextra.entity.BlackDog;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackDogCollarLayer extends RenderLayer<BlackDog, BlackDogModel<BlackDog>> {
    private static final ResourceLocation WOLF_COLLAR_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_collar.png");

    public BlackDogCollarLayer(RenderLayerParent<BlackDog, BlackDogModel<BlackDog>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, BlackDog livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.isTame() && !livingEntity.isInvisible()) {
            int i = livingEntity.getCollarColor().getTextureDiffuseColor();
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(WOLF_COLLAR_LOCATION));
            ((BlackDogModel)this.getParentModel()).renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, i);
        }

    }
}
