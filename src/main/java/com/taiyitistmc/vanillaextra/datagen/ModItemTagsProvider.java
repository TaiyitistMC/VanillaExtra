package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {


    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags,  @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, VanillaExtra.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ModBlocks.BLOCKS.getEntries().stream().forEach(
                blockDeferredHolder -> {
                    if (blockDeferredHolder.get().getDescriptionId().contains("planks")) {
                        tag(ItemTags.PLANKS).add(blockDeferredHolder.get().asItem());
                    }
                    if (blockDeferredHolder.get().getDescriptionId().contains("sapling")) {
                        tag(ItemTags.SAPLINGS).add(blockDeferredHolder.get().asItem());
                    }
                    if (blockDeferredHolder.get().getDescriptionId().contains("log")) {
                        tag(ItemTags.LOGS).add(blockDeferredHolder.get().asItem());
                    }
                }
        );
    }
}
