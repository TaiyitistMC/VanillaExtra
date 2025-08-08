package com.taiyitistmc.vanillaextra.common.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.common.block.LandKelpPlantBlock;
import com.taiyitistmc.vanillaextra.common.block.OreAttachedStemBlock;
import com.taiyitistmc.vanillaextra.common.block.OreStemBlock;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VanillaExtra.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VANILLA_EXTRA_MISC = CREATIVE_MODE_TABS.register("vanilla_extra_misc", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.vainllaextra.misc")).icon(
            () -> ModBlocks.LAND_KELP.get().asItem().getDefaultInstance()).displayItems((parameters, output) -> {
                ModBlocks.BLOCKS.getEntries().forEach(block -> {
                    boolean flag = !block.get().asItem().getDefaultInstance().has(DataComponents.FOOD)
                            && !block.get().getDescriptionId().contains("stem")
                            && !(block.get() instanceof LeavesBlock)
                            && !(block.get() instanceof SaplingBlock)
                            && !(block.get() instanceof RotatedPillarBlock)
                            && !(block.get() instanceof OreStemBlock)
                            && !(block.get() instanceof OreAttachedStemBlock)
                            && !(block.get() instanceof LandKelpPlantBlock);
                    if (flag) {
                        output.accept(block.get());
                    }
                });
                ModItems.ITEMS.getEntries().forEach(item -> {
                    boolean flag = item.get() instanceof BlockItem blockItem
                            && !blockItem.getDescriptionId().contains("stem")
                            && !(blockItem.getBlock() instanceof LeavesBlock)
                            && !(blockItem.getBlock() instanceof SaplingBlock)
                            && !(blockItem.getBlock() instanceof RotatedPillarBlock)
                            && !(blockItem.getBlock() instanceof OreStemBlock)
                            && !(blockItem.getBlock() instanceof OreAttachedStemBlock)
                            && !(blockItem.getBlock() instanceof LandKelpPlantBlock);
                    if ((flag)) {
                        output.accept(item.get());
                    } else if (!(item.get() instanceof BlockItem)
                            && !item.get().getDefaultInstance().has(DataComponents.FOOD)) {
                        output.accept(item.get());
                    }
                });
    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VANILLA_EXTRA_FOOD = CREATIVE_MODE_TABS.register("vanilla_extra_food", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.vainllaextra.food")).icon(
            () -> ModItems.BACON_AND_EGG.get().getDefaultInstance()).displayItems((parameters, output) -> {
        ModItems.ITEMS.getEntries().forEach(item -> {
            boolean flag = item.get().getDefaultInstance().has(DataComponents.FOOD);
            if (flag) {
                output.accept(item.get());
            }
        });
    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VANILLA_EXTRA_NATURE = CREATIVE_MODE_TABS.register("vanilla_extra_nature", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.vainllaextra.nature")).icon(
            () -> ModBlocks.REDSTONE_TREE_LEAVES.get().asItem().getDefaultInstance()).displayItems((parameters, output) -> {
        ModItems.ITEMS.getEntries().forEach(item -> {
            if (item.get() instanceof BlockItem blockItem && !blockItem.getDescriptionId().contains("stem")) {
                boolean flag = (blockItem.getBlock() instanceof LeavesBlock)
                        || (blockItem.getBlock() instanceof SaplingBlock)
                        || (blockItem.getBlock() instanceof RotatedPillarBlock)
                        || blockItem instanceof ItemNameBlockItem;
                if (flag) {
                    output.accept(item.get());
                }
            }
        });
    }).build());
}
