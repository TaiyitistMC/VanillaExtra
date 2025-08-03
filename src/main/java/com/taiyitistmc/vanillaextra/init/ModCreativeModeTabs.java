package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VanillaExtra.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VANILLA_EXTRA = CREATIVE_MODE_TABS.register("vanilla_extra", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.vainllaextra")).withTabsBefore(CreativeModeTabs.COMBAT).icon(
            () -> ModBlocks.LAND_KELP.get().asItem().getDefaultInstance()).displayItems((parameters, output) -> {
                ModBlocks.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
                    output.accept(blockDeferredHolder.get());
                });
                ModItems.ITEMS.getEntries().forEach(itemDeferredHolder -> {
                    output.accept(itemDeferredHolder.get());
                });
    }).build());
}
