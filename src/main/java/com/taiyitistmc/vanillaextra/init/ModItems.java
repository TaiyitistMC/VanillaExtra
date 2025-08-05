package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.item.BlackBloodItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VanillaExtra.MODID);

    public static final DeferredItem<Item> DRIED_LAND_KELP =
            registerFood("dried_land_kelp", Foods.BAKED_POTATO);
    public static final DeferredItem<Item> SAGO =
            registerFood("sago", Foods.POTATO);
    public static final DeferredItem<Item> BACON =
            registerFood("bacon", Foods.PORKCHOP);
    public static final DeferredItem<Item> COOKED_BACON =
            registerFood("cooked_bacon", Foods.COOKED_PORKCHOP);
    public static final DeferredItem<Item> BACON_AND_EGG =
            registerFood("bacon_and_egg", Foods.COOKED_BEEF);
    public static final DeferredItem<Item> WOLF_MEAT =
            registerFood("wolf_meat", Foods.BEEF);
    public static final DeferredItem<Item> COOKED_WOLF_MEAT =
            registerFood("cooked_wolf_meat", Foods.COOKED_BEEF);
    public static final DeferredItem<Item> HORSE_MEAT =
            registerFood("horse_meat", Foods.BEEF);
    public static final DeferredItem<Item> COOKED_HORSE_MEAT =
            registerFood("cooked_horse_meat", Foods.COOKED_BEEF);
    public static final DeferredItem<Item> SQUID_RAW =
            registerFood("squid_raw", Foods.COD);
    public static final DeferredItem<Item> SQUID_COOKED =
            registerFood("squid_cooked", Foods.COOKED_COD);
    public static final DeferredItem<Item> BAT_WING =
            registerFood("bat_wing", Foods.SALMON);
    public static final DeferredItem<Item> COOKED_BAT_WING =
            registerFood("cooked_bat_wing", Foods.COOKED_SALMON);
    public static final DeferredItem<Item> LLAMA_MEAT =
            registerFood("llama_meat", Foods.PORKCHOP);
    public static final DeferredItem<Item> COOKED_LLAMA_MEAT =
            registerFood("cooked_llama_meat", Foods.COOKED_PORKCHOP);
    public static final DeferredItem<Item> HUMAN_MEAT =
            registerFood("human_meat", Foods.PORKCHOP);
    public static final DeferredItem<Item> COOKED_HUMAN_MEAT =
            registerFood("cooked_human_meat", Foods.COOKED_PORKCHOP);
    public static final DeferredItem<Item> PEACH =
            registerFood("peach", Foods.APPLE);
    public static final DeferredItem<Item> PEACH_WOOD_SWORD =
            registerSword("peach_wood_sword", Tiers.WOOD, 4, -2.4F);
    public static final DeferredItem<Item> BLACK_DOG_SPAWN_EGG =
            registerSpawnEgg("black_dog_spawn_egg", ModEntities.BLACK_DOG,1315860, 4672845);
    public static final DeferredItem<Item> BLACK_DOG_BLOOD =
            ITEMS.registerItem("black_dog_blood", properties -> new BlackBloodItem());

    public static <T extends EntityType<? extends Mob>> DeferredItem<Item> registerSpawnEgg(String name, DeferredHolder<EntityType<? extends Entity>, T> entity, int backgroundColor, int highlightColor) {
        return ITEMS.registerItem(name, properties -> new DeferredSpawnEggItem(entity, backgroundColor, highlightColor, new Item.Properties()));
    }

    public static DeferredItem<Item> registerFood(String name, FoodProperties food) {
        return registerSimpleItem(name, new Item.Properties().food(food));
    }

    public static DeferredItem<Item> registerSword(String name, Tier tier, int attackDamage, float attackSpeed) {
        return ITEMS.registerItem(name, item -> new SwordItem(tier, new Item.Properties().attributes(SwordItem.createAttributes(tier, attackDamage, attackSpeed))));
    }

    public static DeferredItem<Item> registerSimpleItem(String name, Item.Properties properties) {
        return ITEMS.registerItem(name, item -> new Item(properties));
    }
}
