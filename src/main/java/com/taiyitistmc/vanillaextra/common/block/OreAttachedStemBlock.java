package com.taiyitistmc.vanillaextra.common.block;

import com.taiyitistmc.vanillaextra.common.util.Helpers;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class OreAttachedStemBlock extends AttachedStemBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final boolean isVanillaFruit;

    public OreAttachedStemBlock(String name, Block fruit) {
        this(name, fruit, true);
    }

    public OreAttachedStemBlock(String name) {
        this(name, null, false);
    }

    public OreAttachedStemBlock(String name, Block fruit, boolean isVanillaFruit) {
        super(Helpers.createModBlockKey(name + "_stem"), isVanillaFruit ? Helpers.createVanillaBlockKey(fruit) : Helpers.createModBlockKey(name), Helpers.createModItemKey(name + "_seeds"), Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY));
        this.isVanillaFruit = isVanillaFruit;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
