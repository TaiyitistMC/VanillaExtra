package com.taiyitistmc.vanillaextra.datagen.loot;

import com.taiyitistmc.vanillaextra.common.block.OreStemBlock;
import com.taiyitistmc.vanillaextra.common.init.ModBlocks;
import com.taiyitistmc.vanillaextra.common.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class ModBlockLoot extends BlockLootSubProvider {

    protected ModBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.LAND_KELP.get());
        this.dropOther(ModBlocks.LAND_KELP_PLANT.get(), ModBlocks.LAND_KELP.get());
        ModBlocks.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
            String id = blockDeferredHolder.get().getDescriptionId();
            if (!id.contains("leaves") && !id.contains("kelp") && !id.contains("stem")) {
                dropSelf(blockDeferredHolder.get());
            }
        });
        this.stemDrop(ModBlocks.IRON_ORE_STEM.get(), ModBlocks.ATTACHED_IRON_ORE_STEM.get(), ModItems.IRON_ORE_SEEDS);
        this.stemDrop(ModBlocks.COAL_ORE_STEM.get(), ModBlocks.ATTACHED_COAL_ORE_STEM.get(), ModItems.COAL_ORE_SEEDS);
        this.stemDrop(ModBlocks.GOLD_ORE_STEM.get(), ModBlocks.ATTACHED_GOLD_ORE_STEM.get(), ModItems.GOLD_ORE_SEEDS);
        this.stemDrop(ModBlocks.LAPIS_ORE_STEM.get(), ModBlocks.ATTACHED_LAPIS_ORE_STEM.get(), ModItems.LAPIS_ORE_SEEDS);
        this.stemDrop(ModBlocks.COPPER_ORE_STEM.get(), ModBlocks.ATTACHED_COPPER_ORE_STEM.get(), ModItems.COPPER_ORE_SEEDS);
        this.stemDrop(ModBlocks.DIAMOND_ORE_STEM.get(), ModBlocks.ATTACHED_DIAMOND_ORE_STEM.get(), ModItems.DIAMOND_ORE_SEEDS);
        this.stemDrop(ModBlocks.EMERALD_ORE_STEM.get(), ModBlocks.ATTACHED_EMERALD_ORE_STEM.get(), ModItems.EMERALD_ORE_SEEDS);
        this.stemDrop(ModBlocks.REDSTONE_ORE_STEM.get(), ModBlocks.ATTACHED_REDSTONE_ORE_STEM.get(), ModItems.REDSTONE_ORE_SEEDS);
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

    protected void stemDrop(Block stem, Block attachStem, ItemLike seed) {
        stemDrop(stem, seed);
        attachStemDrop(attachStem, seed);
    }

    protected void stemDrop(Block stem, ItemLike seed) {
        this.add(stem, factory -> this.createModStemDrops(stem, seed.asItem()));
    }

    protected void attachStemDrop(Block stem, ItemLike seed) {
        this.add(stem, factory -> this.createModAttachStemDrops(stem, seed.asItem()));
    }

    public LootTable.Builder createModStemDrops(Block block, Item item) {
        return LootTable.lootTable().withPool(this.applyExplosionDecay(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item).apply(OreStemBlock.AGE.getPossibleValues(), (p_249795_) -> SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(3, (float)(p_249795_ + 1) / 15.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(OreStemBlock.AGE, p_249795_)))))));
    }

    public LootTable.Builder createModAttachStemDrops(Block block, Item item) {
        return LootTable.lootTable().withPool(this.applyExplosionDecay(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item))));
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
