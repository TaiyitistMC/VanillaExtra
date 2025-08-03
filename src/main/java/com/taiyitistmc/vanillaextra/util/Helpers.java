package com.taiyitistmc.vanillaextra.util;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Helpers {

    public static ResourceLocation identifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(VanillaExtra.MODID, name);
    }

    public static String unwrapName(String name) {
        return name.substring(15);
    }

    public static ItemEntity itemEntity(Level level, double x, double y, double z, ItemStack stack) {
        return new ItemEntity(level, x, y, z, stack);
    }
}
