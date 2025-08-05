package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import com.taiyitistmc.vanillaextra.util.Helpers;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProviderEn extends LanguageProvider {

    public ModLanguageProviderEn(PackOutput output) {
        super(output, VanillaExtra.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.vainllaextra", "Vanilla Extra");
        ModBlocks.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
            add(blockDeferredHolder.get(), formatFieldName(Helpers.unwrapName(blockDeferredHolder.get().asItem().getDefaultInstance().toString())));
        });
        ModItems.ITEMS.getEntries().forEach(itemDeferredHolder -> {
            if (!(itemDeferredHolder.get() instanceof BlockItem)) {
                add(itemDeferredHolder.get(),
                        formatFieldName(Helpers.unwrapName(itemDeferredHolder.get().getDefaultInstance().toString())));
            }
        });
        add("advancements.vanillaextra.story.obtain_land_kelp.title", "A Special Kind of Kelp,Land Kelp");
        add("advancements.vanillaextra.story.obtain_land_kelp.description", "A Special Kind of Kelp that growing in Land,it can grow to 6 block heights, is a delicious crop");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.title", "Delicious Dried Land Kelp,a kind of Food");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.description", "Delicious Food that cooked from Land Kelp");
    }

    private static String formatFieldName(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        boolean nextUpper = true;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                result.append(' ');
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }
}
