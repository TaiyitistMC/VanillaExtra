package com.taiyitistmc.vanillaextra.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Map;

import com.taiyitistmc.vanillaextra.client.model.BlackDogModel;
import com.taiyitistmc.vanillaextra.common.entity.BlackDog;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor.ARGB32;
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.entity.Crackiness.Level;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AnimalArmorItem.BodyType;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackDogArmorLayer extends RenderLayer<BlackDog, BlackDogModel<BlackDog>> {
    private final BlackDogModel<BlackDog> model;
    private static final Map<Crackiness.Level, ResourceLocation> ARMOR_CRACK_LOCATIONS;

    public BlackDogArmorLayer(RenderLayerParent<BlackDog, BlackDogModel<BlackDog>> renderer, EntityModelSet models) {
        super(renderer);
        this.model = new BlackDogModel<>(models.bakeLayer(ModelLayers.WOLF_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, BlackDog livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.hasArmor()) {
            ItemStack itemstack = livingEntity.getBodyArmorItem();
            Item var13 = itemstack.getItem();
            if (var13 instanceof AnimalArmorItem) {
                AnimalArmorItem animalarmoritem = (AnimalArmorItem)var13;
                if (animalarmoritem.getBodyType() == BodyType.CANINE) {
                    ((BlackDogModel)this.getParentModel()).copyPropertiesTo(this.model);
                    this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTick);
                    this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(animalarmoritem.getTexture()));
                    this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
                    this.maybeRenderColoredLayer(poseStack, bufferSource, packedLight, itemstack, animalarmoritem);
                    this.maybeRenderCracks(poseStack, bufferSource, packedLight, itemstack);
                    return;
                }
            }
        }

    }

    private void maybeRenderColoredLayer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ItemStack armorStack, AnimalArmorItem armorItem) {
        if (armorStack.is(ItemTags.DYEABLE)) {
            int i = DyedItemColor.getOrDefault(armorStack, 0);
            if (ARGB32.alpha(i) == 0) {
                return;
            }

            ResourceLocation resourcelocation = armorItem.getOverlayTexture();
            if (resourcelocation == null) {
                return;
            }

            this.model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutoutNoCull(resourcelocation)), packedLight, OverlayTexture.NO_OVERLAY, ARGB32.opaque(i));
        }

    }

    private void maybeRenderCracks(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ItemStack armorStack) {
        Crackiness.Level crackiness$level = Crackiness.WOLF_ARMOR.byDamage(armorStack);
        if (crackiness$level != Level.NONE) {
            ResourceLocation resourcelocation = (ResourceLocation)ARMOR_CRACK_LOCATIONS.get(crackiness$level);
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(resourcelocation));
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        }

    }

    static {
        ARMOR_CRACK_LOCATIONS = Map.of(Level.LOW, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_low.png"), Level.MEDIUM, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_medium.png"), Level.HIGH, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_high.png"));
    }
}
