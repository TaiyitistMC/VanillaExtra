package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.datagen.advancement.ModAdvancementProvider;
import com.taiyitistmc.vanillaextra.datagen.levelgen.ModBiomeModiferProvider;
import com.taiyitistmc.vanillaextra.datagen.levelgen.ModConfiguredFeatureProvider;
import com.taiyitistmc.vanillaextra.datagen.levelgen.ModPlacedFeatureProvider;
import com.taiyitistmc.vanillaextra.datagen.loot.ModLootTableProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;

@SuppressWarnings("removal")
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = VanillaExtra.MODID)
public class ModDataGenerator {


    public static RegistrySetBuilder BUILDER =
            new RegistrySetBuilder()
                    .add(Registries.PLACED_FEATURE,
                            ModPlacedFeatureProvider::placedFeature)
                    .add(Registries.CONFIGURED_FEATURE,
                            ModConfiguredFeatureProvider::configuredFeatures)
                    .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                            ModBiomeModiferProvider::addBiomeModifiers);

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
        event.addProvider(new DatapackBuiltinEntriesProvider(pack,
                        lookUp, BUILDER,
                        Set.of(VanillaExtra.MODID)));
        event.addProvider(new ModAdvancementProvider(pack, lookUp, helper));
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(pack, lookUp, helper);
        event.addProvider(blockTagsProvider);
        event.addProvider(new ModItemTagsProvider(pack, lookUp, blockTagsProvider.contentsGetter(), helper));
    }
}
