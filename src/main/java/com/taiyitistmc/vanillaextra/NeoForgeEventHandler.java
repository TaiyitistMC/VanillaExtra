package com.taiyitistmc.vanillaextra;

import com.taiyitistmc.vanillaextra.common.init.ModStrippables;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = VanillaExtra.MODID, bus = EventBusSubscriber.Bus.GAME)
public class NeoForgeEventHandler {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModStrippables::register);
    }
}
