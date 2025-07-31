package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VanillaExtra.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModBlocks.LAND_KELP.get().asItem());
        simpleBlockItem(ModBlocks.LAND_KELP_PLANT.get());
        basicItem(ModItems.DIRED_LAND_KELP.get());
    }
}
