package cn.solarmoon.idyllic_food_diary.core.common.block.container;

import cn.solarmoon.idyllic_food_diary.api.common.block.container.AbstractContainerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodenPlateBlock extends AbstractContainerBlock {

    public WoodenPlateBlock() {
        super(SoundType.BAMBOO);
    }

    public static final VoxelShape SHAPE = Shapes.or(Block.box(5, 0 , 5, 13, 1, 13),
            Block.box(1, 1, 1, 15, 2, 15));

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

}
