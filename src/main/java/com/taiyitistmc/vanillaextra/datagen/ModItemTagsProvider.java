package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
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
        ModBlocks.BLOCKS.getEntries().forEach(
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
        ModItems.ITEMS.getEntries().forEach(itemDeferredHolder -> {
            if (itemDeferredHolder.get().getDescriptionId().contains("meat")) {
                tag(ItemTags.MEAT).add(itemDeferredHolder.get());
            }
        });
        tag(ItemTags.MEAT).add(ModItems.BACON.get());
        tag(ItemTags.MEAT).add(ModItems.SQUID_COOKED.get());
        tag(ItemTags.MEAT).add(ModItems.SQUID_RAW.get());
        tag(ItemTags.MEAT).add(ModItems.BAT_WING.get());
        tag(ItemTags.MEAT).add(ModItems.COOKED_BAT_WING.get());
    }
}
