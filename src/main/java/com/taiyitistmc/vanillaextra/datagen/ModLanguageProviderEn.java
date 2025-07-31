package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProviderEn extends LanguageProvider {

    public ModLanguageProviderEn(PackOutput output) {
        super(output, VanillaExtra.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.LAND_KELP.get(), "Land Kelp");
        add(ModBlocks.LAND_KELP_PLANT.get(), "Land Kelp");
        add("itemGroup.vainllaextra", "Vanilla Extra");
        add(ModItems.DIRED_LAND_KELP.get(), "Dired Land Kelp");
    }
}
