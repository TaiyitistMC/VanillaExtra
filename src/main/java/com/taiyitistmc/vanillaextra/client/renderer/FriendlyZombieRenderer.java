package com.taiyitistmc.vanillaextra.client.renderer;

import com.taiyitistmc.vanillaextra.client.model.FriendlyZombieModel;
import com.taiyitistmc.vanillaextra.entity.FriendlyZombie;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlyZombieRenderer extends HumanoidMobRenderer<FriendlyZombie, FriendlyZombieModel> {

    private static final ResourceLocation ZOMBIE_LOCATION =
            ResourceLocation.withDefaultNamespace("textures/entity/zombie/zombie.png");

    public FriendlyZombieRenderer(EntityRendererProvider.Context context, FriendlyZombieModel model, FriendlyZombieModel innerModel, FriendlyZombieModel outerModel) {
        super(context, model, 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, innerModel, outerModel, context.getModelManager()));
    }

    public FriendlyZombieRenderer(EntityRendererProvider.Context p_174456_) {
        this(p_174456_, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public FriendlyZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation zombieLayer, ModelLayerLocation innerArmor, ModelLayerLocation outerArmor) {
        this(context, new FriendlyZombieModel(context.bakeLayer(zombieLayer)), new FriendlyZombieModel(context.bakeLayer(innerArmor)), new FriendlyZombieModel(context.bakeLayer(outerArmor)));
    }

    @Override
    public ResourceLocation getTextureLocation(FriendlyZombie entity) {
        return ZOMBIE_LOCATION;
    }
}
