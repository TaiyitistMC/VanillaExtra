package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModEntities;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProviderZh extends LanguageProvider {

    public ModLanguageProviderZh(PackOutput output) {
        super(output, VanillaExtra.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.LAND_KELP.get(), "陆生海带");
        add(ModBlocks.LAND_KELP_PLANT.get(), "陆生海带");
        add(ModItems.DRIED_LAND_KELP.get(), "陆生海带干");
        add("itemGroup.vainllaextra", "Vanilla Extra");
        add(ModBlocks.SAGO_PALM_LOG.get(), "西米棕榈原木");
        add(ModBlocks.SAGO_PALM_PLANKS.get(), "西米棕榈木板");
        add(ModBlocks.SAGO_PALM_SAPLING.get(), "西米棕榈树苗");
        add(ModBlocks.SAGO_PALM_LEAVES.get(), "西米棕榈树叶");
        add(ModBlocks.STRIPPED_SAGO_PALM_LOG.get(), "去皮西米棕榈原木");
        add(ModItems.SAGO.get(), "西米");
        add(ModItems.BACON.get(), "培根");
        add(ModItems.COOKED_BACON.get(), "熟培根");
        add(ModItems.BACON_AND_EGG.get(), "培根煎蛋");
        add(ModItems.WOLF_MEAT.get(), "狼肉");
        add(ModItems.COOKED_WOLF_MEAT.get(), "熟狼肉");
        add(ModItems.HORSE_MEAT.get(), "马肉");
        add(ModItems.COOKED_HORSE_MEAT.get(), "熟马肉");
        add(ModItems.SQUID_RAW.get(), "生鱿鱼");
        add(ModItems.SQUID_COOKED.get(), "熟鱿鱼");
        add(ModItems.BAT_WING.get(), "蝙蝠翼");
        add(ModItems.COOKED_BAT_WING.get(), "熟蝙蝠翼");
        add(ModItems.LLAMA_MEAT.get(), "羊驼肉");
        add(ModItems.COOKED_LLAMA_MEAT.get(), "熟羊驼肉");
        add(ModItems.HUMAN_MEAT.get(), "人肉");
        add(ModItems.COOKED_HUMAN_MEAT.get(), "熟人肉");
        add(ModBlocks.PEACH_LEAVES.get(), "桃树叶");
        add(ModBlocks.PEACH_LOG.get(), "桃树原木");
        add(ModBlocks.PEACH_SAPLING.get(), "桃树苗");
        add(ModBlocks.PEACH_PLANKS.get(), "桃木板");
        add(ModItems.PEACH.get(), "桃子");
        add(ModItems.PEACH_WOOD_SWORD.get(), "桃木剑");
        add(ModEntities.BLACK_DOG.get(), "黑狗");
        add(ModItems.BLACK_DOG_SPAWN_EGG.get(), "黑狗刷怪蛋");
        add(ModItems.BLACK_DOG_BLOOD.get(), "黑狗血");
        add(ModEntities.FRIENDLY_ZOMBIE.get(), "友好的僵尸");
        add(ModItems.FRIENDLY_ZOMBIE_SPAWN_EGG.get(), "友好的僵尸刷怪蛋");
        add(ModEntities.FRIENDLY_SKELETON.get(), "友好的骷髅");
        add(ModItems.FRIENDLY_SKELETON_SPAWN_EGG.get(), "友好的骷髅刷怪蛋");
        add("advancements.vanillaextra.story.obtain_land_kelp.title", "特殊的海带,陆生海带");
        add("advancements.vanillaextra.story.obtain_land_kelp.description", "一种登上陆地的特殊海带,可以长到6格高,是美味的农作物");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.title", "美味的陆生海带干,一种食物");
        add("advancements.vanillaextra.story.obtain_dried_land_kelp.description", "烤制的陆生海带干，非常美味");
    }
}
