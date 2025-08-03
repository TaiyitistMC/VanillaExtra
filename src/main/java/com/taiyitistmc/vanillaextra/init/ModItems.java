package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VanillaExtra.MODID);

    public static final DeferredItem<Item> DRIED_LAND_KELP =
            registerFood("dried_land_kelp", Foods.BAKED_POTATO);
    public static final DeferredItem<Item> SAGO =
            registerFood("sago", Foods.POTATO);
    public static final DeferredItem<Item> BACON = registerFood("bacon", Foods.PORKCHOP);
    public static final DeferredItem<Item> COOKED_BACON = registerFood("cooked_bacon", Foods.COOKED_PORKCHOP);
    public static final DeferredItem<Item> BACON_AND_EGG = registerFood("bacon_and_egg", Foods.COOKED_BEEF);
    public static final DeferredItem<Item> WOLF_MEAT = registerFood("wolf_meat", Foods.BEEF);
    public static final DeferredItem<Item> COOKED_WOLF_MEAT = registerFood("cooked_wolf_meat", Foods.COOKED_BEEF);
    public static final DeferredItem<Item> HORSE_MEAT = registerFood("horse_meat", Foods.BEEF);
    public static final DeferredItem<Item> COOKED_HORSE_MEAT = registerFood("cooked_horse_meat", Foods.COOKED_BEEF);

    public static DeferredItem<Item> register(String name, Item item) {
        return ITEMS.register(name, () -> item);
    }

    public static DeferredItem<Item> registerFood(String name, FoodProperties food) {
        return registerSimpleItem(name, new Item.Properties().food(food));
    }

    public static DeferredItem<Item> registerSimpleItem(String name, Item.Properties properties) {
        return ITEMS.registerItem(name, item -> new Item(properties));
    }
}
