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
        add(ModItems.DRIED_LAND_KELP.get(), "陆生海带干");
        add("itemGroup.vainllaextra", "Vanilla Extra");
        add("advancements.vanillaextra.story.obtain_land_kelp.title", "特殊的海带,陆生海带.");
        add("advancements.vanillaextra.story.obtain_land_kelp.description", "一种登上陆地的特殊海带,可以长到6格高,是美味的农作物.");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.title", "美味的陆生海带干,一种食物.");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.description", "烤制的路生海带干，非常美味.");
    }
}
