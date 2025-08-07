package com.taiyitistmc.vanillaextra.datagen.levelgen;

import com.taiyitistmc.vanillaextra.common.init.ModBlocks;
import com.taiyitistmc.vanillaextra.common.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.material.Fluids;

public class ModConfiguredFeatureProvider {

    public static final ResourceKey<ConfiguredFeature<?,?>> LAND_KELP =
            register("land_kelp");
    public static final ResourceKey<ConfiguredFeature<?,?>> SAGO_PALM_TREE =
            register("sago_palm_tree");
    public static final ResourceKey<ConfiguredFeature<?,?>> PEACH_TREE =
            register("peach_tree");

    public static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(
                context,
                LAND_KELP,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        20,
                        4,
                        0,
                        PlacementUtils.inlinePlaced(
                                Feature.BLOCK_COLUMN,
                                BlockColumnConfiguration.simple(BiasedToBottomInt.of(2, 4), BlockStateProvider.simple(ModBlocks.LAND_KELP_PLANT.get())),
                                BlockPredicateFilter.forPredicate(
                                        BlockPredicate.allOf(
                                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                                BlockPredicate.wouldSurvive(ModBlocks.LAND_KELP_PLANT.get().defaultBlockState(), BlockPos.ZERO),
                                                BlockPredicate.anyOf(
                                                        BlockPredicate.matchesFluids(new BlockPos(1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                                                        BlockPredicate.matchesFluids(new BlockPos(-1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                                                        BlockPredicate.matchesFluids(new BlockPos(0, -1, 1), Fluids.WATER, Fluids.FLOWING_WATER),
                                                        BlockPredicate.matchesFluids(new BlockPos(0, -1, -1), Fluids.WATER, Fluids.FLOWING_WATER)
                                                )
                                        )
                                )
                        )
                )
        );
        FeatureUtils.register(context, SAGO_PALM_TREE, Feature.TREE, createSagoPalmTree().build());
        FeatureUtils.register(context, PEACH_TREE, Feature.TREE, createPeachTree().build());
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Helpers.identifier(name));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createSagoPalmTree() {
        return createStraightBlobTree(ModBlocks.SAGO_PALM_LOG.get(), ModBlocks.SAGO_PALM_LEAVES.get(), 9, 3, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createPeachTree() {
        return createStraightBlobTree(ModBlocks.PEACH_LOG.get(), ModBlocks.PEACH_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(logBlock), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leavesBlock), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }
}
