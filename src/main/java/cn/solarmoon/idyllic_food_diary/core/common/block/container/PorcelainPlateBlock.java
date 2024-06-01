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

public class PorcelainPlateBlock extends AbstractContainerBlock {

    public PorcelainPlateBlock() {
        super(SoundType.GLASS);
    }

    public static final VoxelShape SHAPE = Shapes.or(Block.box(4, 0 , 4, 12, 1, 12),
            Block.box(1, 1, 1, 15, 2, 15));

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return WoodenPlateBlock.SHAPE;
    }

}
