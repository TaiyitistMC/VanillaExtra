package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.block.LandKelpBlock;
import com.taiyitistmc.vanillaextra.block.LandKelpPlantBlock;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VanillaExtra.MODID);
    public static final DeferredBlock<Block> LAND_KELP = register("land_kelp", LandKelpBlock::new, new Item.Properties().food(Foods.POTATO));
    public static final DeferredBlock<Block> LAND_KELP_PLANT = register("land_kelp_plant", LandKelpPlantBlock::new);

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
}
