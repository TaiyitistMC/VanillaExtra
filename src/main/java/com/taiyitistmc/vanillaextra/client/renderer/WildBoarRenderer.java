package com.taiyitistmc.vanillaextra.client.renderer;

import com.taiyitistmc.vanillaextra.client.model.WildBoarModel;
import com.taiyitistmc.vanillaextra.common.entity.WildBoar;
import com.taiyitistmc.vanillaextra.common.init.ModEntityModelLayers;
import com.taiyitistmc.vanillaextra.common.util.Helpers;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WildBoarRenderer extends MobRenderer<WildBoar, WildBoarModel<WildBoar>> {

    public WildBoarRenderer(EntityRendererProvider.Context p_174340_) {
        super(p_174340_, new WildBoarModel(p_174340_.bakeLayer(ModEntityModelLayers.WILD_BOAR)), 0.7F);
        this.addLayer(new SaddleLayer(this, new PigModel(p_174340_.bakeLayer(ModelLayers.PIG_SADDLE)), ResourceLocation.withDefaultNamespace("textures/entity/pig/pig_saddle.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(WildBoar entity) {
        return Helpers.identifier("textures/entity/wild_boar.png");
    }
}
