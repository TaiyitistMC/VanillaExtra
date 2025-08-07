package com.taiyitistmc.vanillaextra.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.taiyitistmc.vanillaextra.client.model.BlackDogModel;
import com.taiyitistmc.vanillaextra.client.renderer.layers.BlackDogArmorLayer;
import com.taiyitistmc.vanillaextra.client.renderer.layers.BlackDogCollarLayer;
import com.taiyitistmc.vanillaextra.common.entity.BlackDog;
import com.taiyitistmc.vanillaextra.common.util.Helpers;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackDogRenderer extends MobRenderer<BlackDog, BlackDogModel<BlackDog>> {

    public BlackDogRenderer(EntityRendererProvider.Context context) {
        super(context, new BlackDogModel<>(context.bakeLayer(ModelLayers.WOLF)), 0.5F);
        this.addLayer(new BlackDogArmorLayer(this, context.getModelSet()));
        this.addLayer(new BlackDogCollarLayer(this));
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    protected float getBob(BlackDog livingBase, float partialTicks) {
        return livingBase.getTailAngle();
    }

    @Override
    public void render(BlackDog entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isWet()) {
            float f = entity.getWetShade(partialTicks);
            this.model.setColor(FastColor.ARGB32.colorFromFloat(1.0F, f, f, f));
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        if (entity.isWet()) {
            this.model.setColor(-1);
        }
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getTextureLocation(BlackDog blackDog) {
        return Helpers.identifier("textures/entity/black_dog.png");
    }
}
