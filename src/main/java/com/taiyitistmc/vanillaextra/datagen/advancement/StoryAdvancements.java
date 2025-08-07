package com.taiyitistmc.vanillaextra.datagen.advancement;

import com.taiyitistmc.vanillaextra.common.init.ModBlocks;
import com.taiyitistmc.vanillaextra.common.init.ModItems;
import com.taiyitistmc.vanillaextra.common.util.Helpers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class StoryAdvancements implements AdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
        Advancement.Builder builder = Advancement.Builder.advancement();
        AdvancementHolder landKelpHolder = obtainItemAdvancement(builder, ModBlocks.LAND_KELP.get(), consumer);
        obtainItemAdvancement(builder.parent(landKelpHolder), ModItems.DRIED_LAND_KELP.get(), consumer);
    }

    private AdvancementHolder obtainItemAdvancement(Advancement.Builder advancement, ItemLike displayItem, Consumer<AdvancementHolder> consumer) {
        return advancement
                .display(
                        displayItem,
                        Component.translatable("advancements.vanillaextra.story." + "obtain_" + Helpers.unwrapName(displayItem.asItem().getDefaultInstance().toString()) + ".title"),
                        Component.translatable("advancements.vanillaextra.story." + "obtain_" + Helpers.unwrapName(displayItem.asItem().getDefaultInstance().toString()) + ".description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(Helpers.unwrapName(displayItem.asItem().getDefaultInstance().toString()), InventoryChangeTrigger.TriggerInstance.hasItems(displayItem))
                .save(consumer, Helpers.identifier("story/obtain_" + Helpers.unwrapName(displayItem.asItem().getDefaultInstance().toString())).toString());
    }
}
