package com.taiyitistmc.vanillaextra.util;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import net.minecraft.resources.ResourceLocation;

public class Helpers {

    public static ResourceLocation identifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(VanillaExtra.MODID, name);
    }
}
