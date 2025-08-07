package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.common.block.OreAttachedStemBlock;
import com.taiyitistmc.vanillaextra.common.block.OreStemBlock;
import com.taiyitistmc.vanillaextra.common.init.ModBlocks;
import com.taiyitistmc.vanillaextra.common.init.ModItems;
import com.taiyitistmc.vanillaextra.common.item.TemplateSeedsItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VanillaExtra.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ITEMS.getEntries().forEach(itemDeferredHolder -> {
            boolean isBlockItem =
                    itemDeferredHolder.get() instanceof BlockItem blockItem
                            && !(itemDeferredHolder.get().getDefaultInstance().is(ModBlocks.LAND_KELP.get().asItem()))
                            && !(itemDeferredHolder.get().getDescriptionId().contains("sapling"))
                            && !(blockItem.getBlock() instanceof OreStemBlock)
                            && !(blockItem.getBlock() instanceof OreAttachedStemBlock);
            if (isBlockItem) {
                simpleBlockItem(Block.byItem(itemDeferredHolder.get()));
            } else if (itemDeferredHolder.get().getDescriptionId().contains("sapling")) {
                blockCustomItem(itemDeferredHolder.get());
            } else if (itemDeferredHolder.get().getDescriptionId().contains("spawn_egg")) {
                spawnEggItem(itemDeferredHolder.get());
            } else if (itemDeferredHolder.get().getDescriptionId().contains("stem")) {
                blockStemItem(itemDeferredHolder.get());
            } else if (itemDeferredHolder.get() instanceof TemplateSeedsItem) {
                templateSeedsItem(itemDeferredHolder.get());
            } else {
                basicItem(itemDeferredHolder.get());
            }
        });
    }

    public ItemModelBuilder blockCustomItem(Item item) {
        return this.blockCustomItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    public ItemModelBuilder blockCustomItem(ResourceLocation item) {
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "block/" + item.getPath()));
    }

    public ItemModelBuilder blockStemItem(Item item) {
        return this.blockStemItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    public ItemModelBuilder templateSeedsItem(Item item) {
        return this.templateSeedsItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    public ItemModelBuilder templateSeedsItem(ResourceLocation item) {
        return this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0",
                        ResourceLocation.fromNamespaceAndPath(item.getNamespace(),
                                "item/template_seeds"));
    }

    public ItemModelBuilder blockStemItem(ResourceLocation item) {
        if (item.getPath().contains("stem") && !item.getPath().contains("attached")) {
            return this.getBuilder(item.toString())
                    .parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0",
                            ResourceLocation.fromNamespaceAndPath(item.getNamespace(),
                                    "block/ore_stem"));
        } else {
            return this.getBuilder(item.toString())
                    .parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0",
                            ResourceLocation.fromNamespaceAndPath(item.getNamespace(),
                                    "block/attached_ore_stem"));
        }
    }
}
