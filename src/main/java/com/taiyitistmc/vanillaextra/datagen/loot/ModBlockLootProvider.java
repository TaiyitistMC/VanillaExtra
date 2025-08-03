package com.taiyitistmc.vanillaextra.datagen.loot;

import com.taiyitistmc.vanillaextra.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {

    protected ModBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.LAND_KELP.get());
        this.dropOther(ModBlocks.LAND_KELP_PLANT.get(), ModBlocks.LAND_KELP.get());
        this.dropSelf(ModBlocks.SAGO_PALM_LOG.get());
        this.dropSelf(ModBlocks.SAGO_PALM_SAPLING.get());
        this.dropSelf(ModBlocks.SAGO_PALM_PLANKS.get());
        this.leavesDrop(ModBlocks.SAGO_PALM_LEAVES.get(), ModBlocks.SAGO_PALM_SAPLING.get());
        this.dropSelf(ModBlocks.STRIPPED_SAGO_PALM_LOG.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(e -> (Block) e.value())
                .toList();
    }

    protected void leavesDrop(Block leaves, Block sapling) {
        this.add(leaves, factory -> this.createOakLeavesDrops(factory, sapling, NORMAL_LEAVES_SAPLING_CHANCES));

    }
}
