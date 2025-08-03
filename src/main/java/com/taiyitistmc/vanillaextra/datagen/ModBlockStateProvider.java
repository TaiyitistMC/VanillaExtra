package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VanillaExtra.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        crossBlock(ModBlocks.LAND_KELP.get());
        crossBlock(ModBlocks.LAND_KELP_PLANT.get());
        crossBlock(ModBlocks.SAGO_PALM_SAPLING.get());
        logBlock((RotatedPillarBlock) ModBlocks.SAGO_PALM_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_SAGO_PALM_LOG.get());
        simpleBlockWithItem(ModBlocks.SAGO_PALM_PLANKS.get(), cubeAll(ModBlocks.SAGO_PALM_PLANKS.get()));
        simpleBlockWithItem(ModBlocks.SAGO_PALM_LEAVES.get(), cubeAll(ModBlocks.SAGO_PALM_LEAVES.get()));
    }

    public void crossBlock(Block block) {
        this.simpleBlock(block, this.cross(block));
    }

    public ModelFile cubeAll(Block block) {
        return this.models().cubeAll(this.getName(block), this.blockTexture(block));

    }
    public ModelFile cross(Block block) {
        return this.models().cross(this.getName(block), this.blockTexture(block));
    }

    private String getName(Block block) {
        return this.getKey(block).getPath();
    }

    private ResourceLocation getKey(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
