package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VanillaExtra.MODID);

    public static final DeferredItem<Item> DIRED_LAND_KELP =
            registerSimpleItem("dired_land_kelp", new Item.Properties().food(Foods.BAKED_POTATO));

    public static DeferredItem<Item> register(String name, Item item) {
        return ITEMS.register(name, () -> item);
    }

    public static DeferredItem<Item> registerSimpleItem(String name, Item.Properties properties) {
        return ITEMS.registerItem(name, item -> new Item(properties));
    }
}
