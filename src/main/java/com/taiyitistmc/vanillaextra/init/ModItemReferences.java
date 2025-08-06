package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItemReferences {

    public static final ResourceKey<Item> IRON_ORE_SEEDS = createKey("iron_ore_seeds");
    public static final ResourceKey<Item> COAL_ORE_SEEDS = createKey("coal_ore_seeds");
    public static final ResourceKey<Item> LAPIS_ORE_SEEDS = createKey("lapis_ore_seeds");
    public static final ResourceKey<Item> GOLD_ORE_SEEDS = createKey("gold_ore_seeds");

    private static ResourceKey<Item> createKey(String id) {
        return ResourceKey.create(Registries.ITEM, Helpers.identifier(id));
    }
}
