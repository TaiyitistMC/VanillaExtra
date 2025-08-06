package com.taiyitistmc.vanillaextra.client;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.client.model.BlackDogModel;
import com.taiyitistmc.vanillaextra.client.model.FriendlySkeletonModel;
import com.taiyitistmc.vanillaextra.client.model.FriendlyZombieModel;
import com.taiyitistmc.vanillaextra.client.renderer.BlackDogRenderer;
import com.taiyitistmc.vanillaextra.client.renderer.FriendlySkeletonRenderer;
import com.taiyitistmc.vanillaextra.client.renderer.FriendlyZombieRenderer;
import com.taiyitistmc.vanillaextra.entity.BlackDog;
import com.taiyitistmc.vanillaextra.entity.FriendlySkeleton;
import com.taiyitistmc.vanillaextra.entity.FriendlyZombie;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModEntities;
import com.taiyitistmc.vanillaextra.init.ModEntityModelLayers;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@SuppressWarnings({"removal", "deprecation"})
@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = VanillaExtra.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class VanillaExtraClient {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(1315860),
                ModBlocks.COAL_ORE_STEM.get(),
                ModBlocks.ATTACHED_COAL_ORE_STEM.get());
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(16382457),
                ModBlocks.IRON_ORE_STEM.get(),
                ModBlocks.ATTACHED_IRON_ORE_STEM.get());
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(16579584),
                ModBlocks.GOLD_ORE_STEM.get(),
                ModBlocks.ATTACHED_GOLD_ORE_STEM.get());
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(686780),
                ModBlocks.LAPIS_ORE_STEM.get(),
                ModBlocks.ATTACHED_LAPIS_ORE_STEM.get());
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(44975),
                ModBlocks.DIAMOND_ORE_STEM.get(),
                ModBlocks.ATTACHED_DIAMOND_ORE_STEM.get());
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(5349438),
                ModBlocks.EMERALD_ORE_STEM.get(),
                ModBlocks.ATTACHED_EMERALD_ORE_STEM.get());
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(11013646),
                ModBlocks.REDSTONE_ORE_STEM.get(),
                ModBlocks.ATTACHED_REDSTONE_ORE_STEM.get());
        event.register((blockState, blockAndTintGetter, blockPos, i) -> FastColor.ARGB32.opaque(15826224),
                ModBlocks.COPPER_ORE_STEM.get(),
                ModBlocks.ATTACHED_COPPER_ORE_STEM.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((itemStack, i) -> FastColor.ARGB32.opaque(1315860),
                ModBlocks.COAL_ORE_STEM.get(),
                ModBlocks.ATTACHED_COAL_ORE_STEM.get());
        event.register((itemStack, i) -> FastColor.ARGB32.opaque(16382457),
                ModBlocks.IRON_ORE_STEM.get(),
                ModBlocks.ATTACHED_IRON_ORE_STEM.get());
        event.register((itemStack, i) -> FastColor.ARGB32.opaque(16579584),
                ModBlocks.GOLD_ORE_STEM.get(),
                ModBlocks.ATTACHED_GOLD_ORE_STEM.get());
        event.register((itemStack, i)  -> FastColor.ARGB32.opaque(686780),
                ModBlocks.LAPIS_ORE_STEM.get(),
                ModBlocks.ATTACHED_LAPIS_ORE_STEM.get());
        event.register((itemStack, i) -> FastColor.ARGB32.opaque(44975),
                ModBlocks.DIAMOND_ORE_STEM.get(),
                ModBlocks.ATTACHED_DIAMOND_ORE_STEM.get());
        event.register((itemStack, i) -> FastColor.ARGB32.opaque(5349438),
                ModBlocks.EMERALD_ORE_STEM.get(),
                ModBlocks.ATTACHED_EMERALD_ORE_STEM.get());
        event.register((itemStack, i) -> FastColor.ARGB32.opaque(11013646),
                ModBlocks.REDSTONE_ORE_STEM.get(),
                ModBlocks.ATTACHED_REDSTONE_ORE_STEM.get());
        event.register((itemStack, i) -> FastColor.ARGB32.opaque(15826224),
                ModBlocks.COPPER_ORE_STEM.get(),
                ModBlocks.ATTACHED_COPPER_ORE_STEM.get(),
                ModItems.COPPER_ORE_SEEDS.get());
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BLACK_DOG.get(), BlackDog.registerAttributes().build());
        event.put(ModEntities.FRIENDLY_ZOMBIE.get(), FriendlyZombie.registerAttributes().build());
        event.put(ModEntities.FRIENDLY_SKELETON.get(), FriendlySkeleton.registerAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BLACK_DOG.get(),
                BlackDogRenderer::new);
        event.registerEntityRenderer(ModEntities.FRIENDLY_ZOMBIE.get(),
                FriendlyZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.FRIENDLY_SKELETON.get(),
                FriendlySkeletonRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.BLACK_DOG, () -> LayerDefinition.create(BlackDogModel.createBodyLayer(CubeDeformation.NONE), 64, 64));
        event.registerLayerDefinition(ModEntityModelLayers.FRIENDLY_ZOMBIE, () -> LayerDefinition.create(FriendlyZombieModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 32));
        event.registerLayerDefinition(ModEntityModelLayers.FRIENDLY_SKELETON, FriendlySkeletonModel::createBodyLayer);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerBlockClients(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAND_KELP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAND_KELP_PLANT.get(), RenderType.cutout());
        ModBlocks.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
            if (blockDeferredHolder.get().getDescriptionId().contains("sapling")) {
                ItemBlockRenderTypes.setRenderLayer(blockDeferredHolder.get(), RenderType.cutout());
            } else if (blockDeferredHolder.get().getDescriptionId().contains("plant")) {
                ItemBlockRenderTypes.setRenderLayer(blockDeferredHolder.get(), RenderType.cutout());
            } else if (blockDeferredHolder.get().getDescriptionId().contains("leaves")) {
                ItemBlockRenderTypes.setRenderLayer(blockDeferredHolder.get(), RenderType.cutoutMipped());
            } else if (blockDeferredHolder.get().getDescriptionId().contains("stem")) {
                ItemBlockRenderTypes.setRenderLayer(blockDeferredHolder.get(), RenderType.cutoutMipped());
            }
        });
    }
}
