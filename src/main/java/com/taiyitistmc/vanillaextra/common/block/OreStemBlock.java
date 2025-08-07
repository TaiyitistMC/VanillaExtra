package com.taiyitistmc.vanillaextra.common.block;

import com.taiyitistmc.vanillaextra.common.util.Helpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class OreStemBlock extends StemBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    private final boolean isVanillaFruit;

    public OreStemBlock(String name, Block fruit) {
        this(name, fruit, true);
    }

    private OreStemBlock(String name) {
        this(name, null, false);
    }

    public OreStemBlock(String name, Block fruit, boolean isVanillaFruit) {
        super(isVanillaFruit ? Helpers.createVanillaBlockKey(fruit) : Helpers.createModBlockKey(name), Helpers.createModBlockKey("attached_" + name + "_stem"), Helpers.createModItemKey(name + "_seeds"), Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP).pushReaction(PushReaction.DESTROY));
        this.isVanillaFruit = isVanillaFruit;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
