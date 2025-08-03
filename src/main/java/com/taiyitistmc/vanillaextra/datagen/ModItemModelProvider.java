package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VanillaExtra.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ITEMS.getEntries().forEach(itemDeferredHolder -> {
            boolean isBlockItem = itemDeferredHolder.get() instanceof BlockItem && !(itemDeferredHolder.get().getDefaultInstance().is(ModBlocks.LAND_KELP.get().asItem())) && !(((BlockItem) itemDeferredHolder.get()).getDescriptionId().contains("sapling"));
            if (isBlockItem) {
                simpleBlockItem(Block.byItem(itemDeferredHolder.get()));
            } else {
                basicItem(itemDeferredHolder.get());
            }
        });
    }
}
