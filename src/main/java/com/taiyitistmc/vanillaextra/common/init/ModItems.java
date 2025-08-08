package com.taiyitistmc.vanillaextra.common.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.common.item.BlackBloodItem;
import com.taiyitistmc.vanillaextra.common.item.PeachWoodSwordItem;
import com.taiyitistmc.vanillaextra.common.item.TemplateSeedsItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VanillaExtra.MODID);

    // Foods
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
    public static final DeferredItem<Item> BLACK_DOG_BLOOD =
            ITEMS.registerItem("black_dog_blood", properties -> new BlackBloodItem());
    public static final DeferredItem<Item> ENDERMAN_MEAT =
            registerFood("enderman_meat", Foods.PORKCHOP);
    public static final DeferredItem<Item> COOKED_ENDERMAN_MEAT =
            registerFood("cooked_enderman_meat", Foods.COOKED_PORKCHOP);
    public static final DeferredItem<Item> WILD_BOAR_MEAT =
            registerFood("wild_boar_meat", Foods.PORKCHOP);
    public static final DeferredItem<Item> COOKED_WILD_BOAR_MEAT =
            registerFood("cooked_wild_boar_meat", Foods.COOKED_PORKCHOP);
    public static final DeferredItem<Item> OCELOT_MEAT =
            registerFood("ocelot_meat", Foods.PORKCHOP);
    public static final DeferredItem<Item> COOKED_OCELOT_MEAT =
            registerFood("cooked_ocelot_meat", Foods.COOKED_PORKCHOP);
    public static final DeferredItem<Item> SPIDER_LEG =
            registerFood("spider_leg", Foods.POTATO);
    public static final DeferredItem<Item> COOKED_SPIDER_LEG =
            registerFood("cooked_spider_leg", Foods.BAKED_POTATO);

    // Tools
    public static final DeferredItem<Item> PEACH_WOOD_SWORD =
            ITEMS.registerItem("peach_wood_sword", properties -> new PeachWoodSwordItem());

    // SpawnEggs
    public static final DeferredItem<Item> BLACK_DOG_SPAWN_EGG =
            registerSpawnEgg("black_dog_spawn_egg", ModEntities.BLACK_DOG,1315860, 4672845);
    public static final DeferredItem<Item> FRIENDLY_ZOMBIE_SPAWN_EGG =
            registerSpawnEgg("friendly_zombie_spawn_egg", ModEntities.FRIENDLY_ZOMBIE,44975, 7969893);
    public static final DeferredItem<Item> FRIENDLY_SKELETON_SPAWN_EGG =
            registerSpawnEgg("friendly_skeleton_spawn_egg", ModEntities.FRIENDLY_SKELETON,12698049, 4802889);
    public static final DeferredItem<Item> WILD_BOAR_SPAWN_EGG =
            registerSpawnEgg("wild_boar_spawn_egg", ModEntities.WILD_BOAR,7164733, 1444352);

    // Plants
    public static final DeferredItem<Item> DRIED_LAND_KELP =
            registerFood("dried_land_kelp", Foods.BAKED_POTATO);
    public static final DeferredItem<Item> IRON_ORE_SEEDS =
            ITEMS.registerItem("iron_ore_seeds", item ->
                    new ItemNameBlockItem(ModBlocks.IRON_ORE_STEM.get(), new Item.Properties()));
    public static final DeferredItem<Item> COAL_ORE_SEEDS =
            ITEMS.registerItem("coal_ore_seeds", item ->
                    new ItemNameBlockItem(ModBlocks.COAL_ORE_STEM.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLD_ORE_SEEDS =
            ITEMS.registerItem("gold_ore_seeds", item ->
                    new ItemNameBlockItem(ModBlocks.GOLD_ORE_STEM.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ORE_SEEDS =
            ITEMS.registerItem("lapis_ore_seeds", item ->
                    new ItemNameBlockItem(ModBlocks.LAPIS_ORE_STEM.get(), new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_ORE_SEEDS =
            ITEMS.registerItem("diamond_ore_seeds", item ->
                    new ItemNameBlockItem(ModBlocks.DIAMOND_ORE_STEM.get(), new Item.Properties()));
    public static final DeferredItem<Item> REDSTONE_ORE_SEEDS =
            ITEMS.registerItem("redstone_ore_seeds", item ->
                    new ItemNameBlockItem(ModBlocks.REDSTONE_ORE_STEM.get(), new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_ORE_SEEDS =
            ITEMS.registerItem("emerald_ore_seeds", item ->
                    new ItemNameBlockItem(ModBlocks.EMERALD_ORE_STEM.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_ORE_SEEDS =
            ITEMS.registerItem("copper_ore_seeds", item ->
                    new TemplateSeedsItem(ModBlocks.COPPER_ORE_STEM.get()));
    public static final DeferredItem<Item> ANCIENT_DEBRIS_SEEDS =
            ITEMS.registerItem("ancient_debris_seeds", item ->
                    new TemplateSeedsItem(ModBlocks.ANCIENT_DEBRIS_STEM.get()));
    public static final DeferredItem<Item> AMETHYST_SEEDS =
            ITEMS.registerItem("amethyst_debris_seeds", item ->
                    new TemplateSeedsItem(ModBlocks.AMETHYST_STEM.get()));

    public static <T extends EntityType<? extends Mob>> DeferredItem<Item> registerSpawnEgg(String name, DeferredHolder<EntityType<? extends Entity>, T> entity, int backgroundColor, int highlightColor) {
        return ITEMS.registerItem(name, properties -> new DeferredSpawnEggItem(entity, backgroundColor, highlightColor, new Item.Properties()));
    }

    public static DeferredItem<Item> registerFood(String name, FoodProperties food) {
        return registerSimpleItem(name, new Item.Properties().food(food));
    }

    public static DeferredItem<Item> registerSimpleItem(String name, Item.Properties properties) {
        return ITEMS.registerItem(name, item -> new Item(properties));
    }
}
