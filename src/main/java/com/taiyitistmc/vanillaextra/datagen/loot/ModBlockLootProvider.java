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
        ModBlocks.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
            if (!blockDeferredHolder.get().getDescriptionId().contains("leaves") && !blockDeferredHolder.get().getDescriptionId().contains("kelp")) {
                dropSelf(blockDeferredHolder.get());
            }
        });
        this.leavesDrop(ModBlocks.SAGO_PALM_LEAVES.get(), ModBlocks.SAGO_PALM_SAPLING.get());
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
