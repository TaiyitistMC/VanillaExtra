package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.block.LandKelpBlock;
import com.taiyitistmc.vanillaextra.block.LandKelpPlantBlock;
import com.taiyitistmc.vanillaextra.block.OreAttachedStemBlock;
import com.taiyitistmc.vanillaextra.block.OreStemBlock;
import com.taiyitistmc.vanillaextra.datagen.levelgen.ModConfiguredFeatureProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VanillaExtra.MODID);
    public static final DeferredBlock<Block> LAND_KELP = register("land_kelp", LandKelpBlock::new, new Item.Properties().food(Foods.POTATO));
    public static final DeferredBlock<Block> LAND_KELP_PLANT = register("land_kelp_plant", LandKelpPlantBlock::new);
    public static final DeferredBlock<Block> SAGO_PALM_LEAVES = register("sago_palm_leaves", () -> leaves(SoundType.GRASS));
    public static final DeferredBlock<Block> SAGO_PALM_LOG = register("sago_palm_log", () -> log(MapColor.COLOR_ORANGE, MapColor.STONE));
    public static final DeferredBlock<Block> SAGO_PALM_PLANKS = register("sago_palm_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
    public static final DeferredBlock<Block> SAGO_PALM_SAPLING = register("sago_palm_sapling", () -> sapling("sago_palm_sapling", ModConfiguredFeatureProvider.SAGO_PALM_TREE));
    public static final DeferredBlock<Block> STRIPPED_SAGO_PALM_LOG = register("stripped_sago_palm_log", () -> log(MapColor.COLOR_ORANGE, MapColor.STONE));
    public static final DeferredBlock<Block> PEACH_LEAVES = register("peach_leaves", () -> leaves(SoundType.GRASS));
    public static final DeferredBlock<Block> PEACH_LOG = register("peach_log", () -> log(MapColor.COLOR_ORANGE, MapColor.STONE));
    public static final DeferredBlock<Block> PEACH_PLANKS = register("peach_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
    public static final DeferredBlock<Block> PEACH_SAPLING =
            register("peach_sapling", () -> sapling("peach_sapling", ModConfiguredFeatureProvider.PEACH_TREE));
    public static final DeferredBlock<Block> IRON_ORE_STEM = register("iron_ore_stem",
            () -> new OreStemBlock(ModBlockReferences.IRON_ORE, ModBlockReferences.ATTACHED_IRON_ORE_STEM, ModItemReferences.IRON_ORE_SEEDS));
    public static final DeferredBlock<Block> ATTACHED_IRON_ORE_STEM = register("attached_iron_ore_stem",
            () -> new OreAttachedStemBlock(ModBlockReferences.IRON_ORE_STEM, ModBlockReferences.IRON_ORE, ModItemReferences.IRON_ORE_SEEDS));
    public static final DeferredBlock<Block> COAL_ORE_STEM = register("coal_ore_stem",
            () -> new OreStemBlock(ModBlockReferences.COAL_ORE, ModBlockReferences.ATTACHED_COAL_ORE_STEM, ModItemReferences.COAL_ORE_SEEDS));
    public static final DeferredBlock<Block> ATTACHED_COAL_ORE_STEM = register("attached_coal_ore_stem",
            () -> new OreAttachedStemBlock(ModBlockReferences.COAL_ORE_STEM, ModBlockReferences.COAL_ORE, ModItemReferences.COAL_ORE_SEEDS));
    public static final DeferredBlock<Block> GOLD_ORE_STEM = register("gold_ore_stem",
            () -> new OreStemBlock(ModBlockReferences.GOLD_ORE, ModBlockReferences.ATTACHED_GOLD_ORE_STEM, ModItemReferences.GOLD_ORE_SEEDS));
    public static final DeferredBlock<Block> ATTACHED_GOLD_ORE_STEM = register("attached_gold_ore_stem",
            () -> new OreAttachedStemBlock(ModBlockReferences.GOLD_ORE_STEM, ModBlockReferences.GOLD_ORE, ModItemReferences.GOLD_ORE_SEEDS));
    public static final DeferredBlock<Block> LAPIS_ORE_STEM = register("lapis_ore_stem",
            () -> new OreStemBlock(ModBlockReferences.LAPIS_ORE, ModBlockReferences.ATTACHED_LAPIS_ORE_STEM, ModItemReferences.LAPIS_ORE_SEEDS));
    public static final DeferredBlock<Block> ATTACHED_LAPIS_ORE_STEM = register("attached_lapis_ore_stem",
            () -> new OreAttachedStemBlock(ModBlockReferences.LAPIS_ORE_STEM, ModBlockReferences.LAPIS_ORE, ModItemReferences.LAPIS_ORE_SEEDS));

    public static DeferredBlock<Block> register(String name, BlockBehaviour.Properties properties) {
        var block = BLOCKS.registerSimpleBlock(name, properties);
        ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    public static <B extends Block> DeferredBlock<B> register(String name, Supplier<B> sup) {
        var block = BLOCKS.register(name, sup);
        ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    public static <B extends Block> DeferredBlock<B> register(String name, Supplier<B> sup, Item.Properties properties) {
        var block = BLOCKS.register(name, sup);
        ModItems.ITEMS.registerSimpleBlockItem(block, properties);
        return block;
    }

    public static <B extends Block> DeferredBlock<B> registerFruit(String name, Supplier<B> sup, String uuid) {
        var block = BLOCKS.register(name, sup);
        ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    private static Block leaves(SoundType soundType) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never));
    }

    private static Block log(MapColor topMapColor, MapColor sideMapColor) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((mapColor) -> mapColor.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }

    private static Block sapling(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return sapling(new TreeGrower(name, Optional.empty(), Optional.of(tree), Optional.empty()));
    }

    private static Block sapling(TreeGrower treeGrower) {
        return new SaplingBlock(treeGrower, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }
}
