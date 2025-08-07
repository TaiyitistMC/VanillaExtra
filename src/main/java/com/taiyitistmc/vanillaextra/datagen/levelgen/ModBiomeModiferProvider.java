package com.taiyitistmc.vanillaextra.datagen.levelgen;

import com.taiyitistmc.vanillaextra.common.init.ModEntities;
import com.taiyitistmc.vanillaextra.common.util.Helpers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ModBiomeModiferProvider {

    public static final ResourceKey<BiomeModifier> ADD_LAND_KELP = register("add_land_kelp");
    public static final ResourceKey<BiomeModifier> ADD_SAGO_PALM_TREE = register("add_sago_palm_tree");
    public static final ResourceKey<BiomeModifier> ADD_PEACH_TREE = register("add_peach_tree");

    public static final ResourceKey<BiomeModifier> ADD_BLACK_DOG = register("add_black_dog");

    public static void addBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        context.register(ADD_LAND_KELP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(features.getOrThrow(ModPlacedFeatureProvider.LAND_KELP_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_SAGO_PALM_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_WET),
                HolderSet.direct(features.getOrThrow(ModPlacedFeatureProvider.SAGO_PALM_TREE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_PEACH_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(features.getOrThrow(ModPlacedFeatureProvider.PEACH_TREE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_BLACK_DOG, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.BLACK_DOG.get(), 12, 1, 2))
        ));
    }

    private static ResourceKey<BiomeModifier> register(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Helpers.identifier(name));
    }
}
