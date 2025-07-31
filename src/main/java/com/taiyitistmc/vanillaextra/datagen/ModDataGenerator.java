package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.datagen.loot.ModLootTableProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = VanillaExtra.MODID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var pack = gen.getPackOutput();
        var helper = event.getExistingFileHelper();
        var lookUp = event.getLookupProvider();
        event.addProvider(new ModBlockStateProvider(pack, helper));
        event.addProvider(new ModItemModelProvider(pack, helper));
        event.addProvider(new ModLootTableProvider(pack, lookUp));
        event.addProvider(new ModRecipeProvider(pack, lookUp));
        event.addProvider(new ModLanguageProviderEn(pack));
        event.addProvider(new ModLanguageProviderZh(pack));
    }
}
