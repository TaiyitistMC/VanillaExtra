package com.taiyitistmc.vanillaextra;

import com.mojang.logging.LogUtils;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModCreativeModeTabs;
import com.taiyitistmc.vanillaextra.init.ModEntities;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(VanillaExtra.MODID)
public class VanillaExtra {

    public static final String MODID = "vanillaextra";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VanillaExtra(IEventBus modEventBus, ModContainer modContainer) {
        ModEntities.ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

}
