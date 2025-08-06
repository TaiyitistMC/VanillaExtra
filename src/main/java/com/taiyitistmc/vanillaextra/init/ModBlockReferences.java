package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class ModBlockReferences {

    public static final ResourceKey<Block> IRON_ORE = createVanillaKey("iron_ore");
    public static final ResourceKey<Block> IRON_ORE_STEM = createKey("iron_ore_stem");
    public static final ResourceKey<Block> ATTACHED_IRON_ORE_STEM = createKey("attached_iron_ore_stem");
    public static final ResourceKey<Block> COAL_ORE = createVanillaKey("coal_ore");
    public static final ResourceKey<Block> COAL_ORE_STEM = createKey("coal_ore_stem");
    public static final ResourceKey<Block> ATTACHED_COAL_ORE_STEM = createKey("attached_coal_ore_stem");
    public static final ResourceKey<Block> GOLD_ORE = createVanillaKey("gold_ore");
    public static final ResourceKey<Block> GOLD_ORE_STEM = createKey("gold_ore_stem");
    public static final ResourceKey<Block> ATTACHED_GOLD_ORE_STEM = createKey("attached_gold_ore_stem");
    public static final ResourceKey<Block> LAPIS_ORE = createVanillaKey("lapis_ore");
    public static final ResourceKey<Block> LAPIS_ORE_STEM = createKey("lapis_ore_stem");
    public static final ResourceKey<Block> ATTACHED_LAPIS_ORE_STEM = createKey("attached_lapis_ore_stem");

    private static ResourceKey<Block> createKey(String id) {
        return ResourceKey.create(Registries.BLOCK, Helpers.identifier(id));
    }

    private static ResourceKey<Block> createVanillaKey(String id) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(id));
    }
}
