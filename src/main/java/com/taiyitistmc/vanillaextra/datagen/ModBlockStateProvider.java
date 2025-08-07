package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.common.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VanillaExtra.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
            if (blockDeferredHolder.get() instanceof RotatedPillarBlock pillarBlock) {
                logBlock(pillarBlock);
            } else if (blockDeferredHolder.get().getDescriptionId().contains("kelp") || blockDeferredHolder.get().getDescriptionId().contains("sapling")) {
                crossBlock(blockDeferredHolder.get());
            } else if (blockDeferredHolder.get().getDescriptionId().contains("stem")) {
                stemBlock(blockDeferredHolder.get());
            } else {
                simpleBlockWithItem(blockDeferredHolder.get(), cubeAll(blockDeferredHolder.get()));
            }
        });
    }

    public void crossBlock(Block block) {
        this.simpleBlock(block, this.cross(block));
    }

    public ModelFile cubeAll(Block block) {
        return this.models().cubeAll(this.getName(block), this.blockTexture(block));
    }

    public void stemBlock(Block block) {
        if (block instanceof StemBlock stemBlock) {
            this.getVariantBuilder(stemBlock).forAllStates(state -> {
                int age = state.getValue(BlockStateProperties.AGE_7);
                ModelFile modelFile = stem(stemBlock, 0);
                if (age == 0) {
                    modelFile = stem(stemBlock, 0);
                } else if (age == 1) {
                    modelFile = stem(stemBlock, 1);
                } else if (age == 2) {
                    modelFile = stem(stemBlock, 2);
                } else if (age == 3) {
                    modelFile = stem(stemBlock, 3);
                } else if (age == 4) {
                    modelFile = stem(stemBlock, 4);
                } else if (age == 5) {
                    modelFile = stem(stemBlock, 5);
                } else if (age == 6) {
                    modelFile = stem(stemBlock, 6);
                } else if (age == 7) {
                    modelFile = stem(stemBlock, 7);
                }
                return ConfiguredModel.builder()
                        .modelFile(modelFile)
                        .build();
            });
        }
        if (block instanceof AttachedStemBlock attachedStemBlock) {
            this.getVariantBuilder(attachedStemBlock).forAllStates(state -> {
                Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
                int yRot = (int) facing.getClockWise().toYRot();
                if (facing == Direction.EAST) {
                    yRot = 180;
                } else if (facing == Direction.NORTH) {
                    yRot = 90;
                } else if (facing == Direction.SOUTH) {
                    yRot = 270;
                } else if (facing == Direction.WEST) {
                    yRot = 0;
                }
                return ConfiguredModel.builder()
                        .modelFile(stemFruit(attachedStemBlock))
                        .rotationY(yRot)
                        .build();
            });
        }
        for (int i = 0; i <= 7; i++) {
            stem(block, i);
        }
    }

    public ModelFile stem(Block block, int num) {
        return this.models()
                .withExistingParent(getName(block) + "_stage" + num, "minecraft:block/stem_growth" + num)
                .texture("stem", "block/ore_stem");
    }

    public ModelFile stemFruit(Block block) {
        return this.models()
                .withExistingParent(getName(block), "minecraft:block/stem_fruit")
                .texture("stem","block/ore_stem")
                .texture("upperstem", "block/attached_ore_stem");
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
