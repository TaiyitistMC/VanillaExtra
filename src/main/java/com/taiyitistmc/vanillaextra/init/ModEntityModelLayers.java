package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.util.Helpers;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModEntityModelLayers {

    public static final ModelLayerLocation BLACK_DOG = register("black_dog");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String text) {
        return new ModelLayerLocation(Helpers.identifier(name), text);
    }
}
