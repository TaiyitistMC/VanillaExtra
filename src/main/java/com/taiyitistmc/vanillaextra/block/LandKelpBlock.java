package com.taiyitistmc.vanillaextra.block;

import com.mojang.serialization.MapCodec;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class LandKelpBlock extends GrowingPlantHeadBlock {

    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);
    public static final MapCodec<LandKelpBlock> CODEC = simpleCodec(LandKelpBlock::new);

    public LandKelpBlock(Properties properties) {
        super(properties, Direction.UP, SHAPE, true, 0.14);
    }

    public LandKelpBlock() {
        this(Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.LAND_KELP_PLANT.get();
    }

    @Override
    protected boolean canAttachTo(BlockState state) {
        return !state.is(Blocks.MAGMA_BLOCK);
    }

    @Override
    protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return 1;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.is(Blocks.AIR);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) < 6 && CommonHooks.canCropGrow(level, pos.relative(this.growthDirection), state, random.nextDouble() < 0.14)) {
            BlockPos blockpos = pos.relative(this.growthDirection);
            if (this.canGrowInto(level.getBlockState(blockpos))) {
                level.setBlockAndUpdate(blockpos, this.getGrowIntoState(state, level.random));
                CommonHooks.fireCropGrowPost(level, blockpos, level.getBlockState(blockpos));
            }
        }

    }
}
