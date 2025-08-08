package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.common.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VanillaExtra.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ModBlocks.BLOCKS.getEntries().stream().forEach(
                blockDeferredHolder -> {
                    if (blockDeferredHolder.get().getDescriptionId().contains("planks")) {
                        tag(BlockTags.PLANKS).add(blockDeferredHolder.get());
                    }
                    if (blockDeferredHolder.get().getDescriptionId().contains("sapling")) {
                        tag(BlockTags.SAPLINGS).add(blockDeferredHolder.get());
                    }
                    if (blockDeferredHolder.get().getDescriptionId().contains("log")) {
                        tag(BlockTags.LOGS).add(blockDeferredHolder.get());
                        tag(BlockTags.LOGS_THAT_BURN).add(blockDeferredHolder.get());
                    }
                    if (blockDeferredHolder.get().getDescriptionId().contains("leaves")) {
                        tag(BlockTags.LEAVES).add(blockDeferredHolder.get());
                    }
                }
        );
    }
}
