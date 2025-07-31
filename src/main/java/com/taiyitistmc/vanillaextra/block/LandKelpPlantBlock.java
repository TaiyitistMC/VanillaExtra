package com.taiyitistmc.vanillaextra.block;

import com.mojang.serialization.MapCodec;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;

public class LandKelpPlantBlock extends GrowingPlantBodyBlock {

    public static final MapCodec<LandKelpPlantBlock> CODEC = simpleCodec(LandKelpPlantBlock::new);

    public LandKelpPlantBlock() {
        this(Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY));
    }

    public LandKelpPlantBlock(Properties properties) {
        super(properties, Direction.UP, Shapes.block(), true);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.LAND_KELP.get();
    }

    @Override
    protected boolean canAttachTo(BlockState state) {
        return true;
    }

    @Override
    protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return CODEC;
    }

}
