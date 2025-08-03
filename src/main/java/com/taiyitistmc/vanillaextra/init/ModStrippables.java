package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.util.AxeItemHooks;

public class ModStrippables {

    public static void register() {
        AxeItemHooks.addStrippable(ModBlocks.SAGO_PALM_LOG.get(), ModBlocks.STRIPPED_SAGO_PALM_LOG.get());
    }
}
