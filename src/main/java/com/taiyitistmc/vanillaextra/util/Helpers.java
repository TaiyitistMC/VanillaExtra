package com.taiyitistmc.vanillaextra.util;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class Helpers {

    public static ResourceLocation identifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(VanillaExtra.MODID, name);
    }

    public static String unwrapName(String name) {
        return name.substring(15);
    }

    public static ResourceKey<Item> createModItemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Helpers.identifier(name));
    }

    public static ResourceKey<Block> createModBlockKey(String name) {
        return ResourceKey.create(Registries.BLOCK, Helpers.identifier(name));
    }

    public static ResourceKey<Block> createVanillaBlockKey(Block block) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(getBlockName(block)));
    }

    public static String getBlockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public static String getItemName(ItemLike itemLike) {
        return BuiltInRegistries.ITEM.getKey(itemLike.asItem()).getPath();
    }
}
