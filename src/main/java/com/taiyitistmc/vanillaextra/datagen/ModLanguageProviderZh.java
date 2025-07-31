package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProviderZh extends LanguageProvider {

    public ModLanguageProviderZh(PackOutput output) {
        super(output, VanillaExtra.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.LAND_KELP.get(), "陆生海带");
        add(ModBlocks.LAND_KELP_PLANT.get(), "陆生海带");
        add(ModItems.DIRED_LAND_KELP.get(), "陆生海带干");
        add("itemGroup.vainllaextra", "Vanilla Extra");
    }
}
