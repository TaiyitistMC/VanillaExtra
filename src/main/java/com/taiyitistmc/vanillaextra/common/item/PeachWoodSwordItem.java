package com.taiyitistmc.vanillaextra.common.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class PeachWoodSwordItem extends SwordItem {

    public PeachWoodSwordItem() {
        super(Tiers.WOOD, new Item.Properties()
                .attributes(SwordItem.createAttributes(Tiers.WOOD, 4, -2.4F))
                .component(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));
    }
}
