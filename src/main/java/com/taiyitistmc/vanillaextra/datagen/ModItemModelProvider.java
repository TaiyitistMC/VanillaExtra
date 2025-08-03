package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.data.PackOutput;
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
        basicItem(ModItems.DRIED_LAND_KELP.get());
        simpleBlockItem(ModBlocks.SAGO_PALM_LOG.get());
        simpleBlockItem(ModBlocks.STRIPPED_SAGO_PALM_LOG.get());
        basicItem(ModBlocks.SAGO_PALM_SAPLING.asItem());
        basicItem(ModItems.SAGO.get());
    }
}
