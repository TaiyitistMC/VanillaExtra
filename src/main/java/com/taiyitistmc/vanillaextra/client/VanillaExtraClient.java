package com.taiyitistmc.vanillaextra.client;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@SuppressWarnings({"removal", "deprecation"})
@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = VanillaExtra.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class VanillaExtraClient {

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
            }
        });
    }
}
