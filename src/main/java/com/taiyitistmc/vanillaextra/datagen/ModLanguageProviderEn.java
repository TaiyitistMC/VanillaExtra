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
        add(ModItems.DRIED_LAND_KELP.get(), "Dired Land Kelp");
        add(ModBlocks.SAGO_PALM_LEAVES.get(), "Sago Palm Leaves");
        add(ModBlocks.SAGO_PALM_LOG.get(), "Sago Palm Log");
        add(ModBlocks.SAGO_PALM_PLANKS.get(), "Sago Palm Planks");
        add(ModBlocks.SAGO_PALM_SAPLING.get(), "Sago Palm Sapling");
        add(ModBlocks.STRIPPED_SAGO_PALM_LOG.get(), "Stripped Sago Palm Log");
        add(ModItems.SAGO.get(), "Sago");
        add(ModItems.BACON.get(), "Bacon");
        add(ModItems.COOKED_BACON.get(), "Cooked Bacon");
        add(ModItems.BACON_AND_EGG.get(), "Bacon And Egg");
        add(ModItems.WOLF_MEAT.get(), "Wolf Meat");
        add(ModItems.COOKED_WOLF_MEAT.get(), "Cooked Wolf Meat");
        add(ModItems.HORSE_MEAT.get(), "Horse Meat");
        add(ModItems.COOKED_HORSE_MEAT.get(), "Cooked Horse Meat");
        add("advancements.vanillaextra.story.obtain_land_kelp.title", "A Special Kind of Kelp,Land Kelp");
        add("advancements.vanillaextra.story.obtain_land_kelp.description", "A Special Kind of Kelp that growing in Land,it can grow to 6 block heights, is a delicious crop");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.title", "Delicious Dried Land Kelp,a kind of Food");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.description", "Delicious Food that cooked from Land Kelp");
    }
}
