package com.taiyitistmc.vanillaextra.datagen.loot;

import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {

    protected ModBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.LAND_KELP.get());
        this.dropOther(ModBlocks.LAND_KELP_PLANT.get(), ModBlocks.LAND_KELP.get());
        ModBlocks.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
            if (!blockDeferredHolder.get().getDescriptionId().contains("leaves") && !blockDeferredHolder.get().getDescriptionId().contains("kelp")) {
                dropSelf(blockDeferredHolder.get());
            }
        });
        this.leavesDrop(ModBlocks.SAGO_PALM_LEAVES.get(), ModBlocks.SAGO_PALM_SAPLING.get());
        this.leavesWithFruitDrops(ModBlocks.PEACH_LEAVES.get(), ModBlocks.PEACH_SAPLING.get(), ModItems.PEACH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(e -> (Block) e.value())
                .toList();
    }

    protected void leavesDrop(Block leaves, Block sapling) {
        this.add(leaves, factory -> this.createLeavesDrops(factory, sapling, NORMAL_LEAVES_SAPLING_CHANCES));
    }

    protected void leavesWithFruitDrops(Block leaves, Block sapling, ItemLike fruit) {
        this.add(leaves, factory -> this.createLeavesWithFruitDrops(factory, sapling, fruit, NORMAL_LEAVES_SAPLING_CHANCES));
    }

    protected LootTable.Builder createLeavesWithFruitDrops(Block oakLeavesBlock, Block saplingBlock, ItemLike fruit, float... chances) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(oakLeavesBlock, saplingBlock, chances).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(this.doesNotHaveShearsOrSilkTouch()).add(((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(oakLeavesBlock, LootItem.lootTableItem(fruit))).when(BonusLevelTableCondition.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE), new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F}))));
    }

    private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
        return this.hasShearsOrSilkTouch().invert();
    }

    private LootItemCondition.Builder hasShearsOrSilkTouch() {
        return HAS_SHEARS.or(this.hasSilkTouch());
    }

}
