package com.taiyitistmc.vanillaextra.client.renderer;

import com.taiyitistmc.vanillaextra.common.entity.FriendlySpider;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;

public class FriendlySpiderRenderer <T extends FriendlySpider> extends MobRenderer<T, SpiderModel<T>> {

    private static final ResourceLocation SPIDER_LOCATION =
            ResourceLocation.withDefaultNamespace("textures/entity/spider/spider.png");

    public FriendlySpiderRenderer(EntityRendererProvider.Context p_174401_) {
        this(p_174401_, ModelLayers.SPIDER);
    }

    public FriendlySpiderRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer) {
        super(context, new SpiderModel(context.bakeLayer(layer)), 0.8F);
        this.addLayer(new SpiderEyesLayer(this));
    }

    @Override
    protected float getFlipDegrees(T livingEntity) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return SPIDER_LOCATION;
    }

}
