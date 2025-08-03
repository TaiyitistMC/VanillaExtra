package com.taiyitistmc.vanillaextra.datagen.levelgen;

import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.List;

public class ModPlacedFeatureProvider {

    private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

    public static final ResourceKey<PlacedFeature> LAND_KELP_PLACED =
            register("land_kelp_placed");
    public static final ResourceKey<PlacedFeature> SAGO_PALM_TREE_PLACED =
            register("sago_palm_tree_placed");
    public static final ResourceKey<PlacedFeature> SAGO_PALM_TREES_PLACED =
            register("sago_palm_trees_placed");

    public static void placedFeature(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementModifier placementmodifier = SurfaceWaterDepthFilter.forMaxDepth(0);
        Holder<ConfiguredFeature<?, ?>> land_kelp_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.LAND_KELP);
        Holder<ConfiguredFeature<?, ?>> sago_plam_tree_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.SAGO_PALM_TREE);
        context.register(LAND_KELP_PLACED, new PlacedFeature(land_kelp_holder,
                List.of(RarityFilter.onAverageOnceEvery(14),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
        PlacementUtils.register(context, SAGO_PALM_TREE_PLACED, sago_plam_tree_holder, PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), placementmodifier, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SAGO_PALM_SAPLING.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
        PlacementUtils.register(context, SAGO_PALM_TREES_PLACED, sago_plam_tree_holder, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
    }

    private static ResourceKey<PlacedFeature> register(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Helpers.identifier(name));
    }
}
