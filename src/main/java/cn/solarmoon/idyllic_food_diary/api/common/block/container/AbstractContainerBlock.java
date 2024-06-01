package cn.solarmoon.idyllic_food_diary.api.common.block.container;

import cn.solarmoon.solarmoon_core.api.common.block.BaseWaterBlock;
import cn.solarmoon.solarmoon_core.api.common.block.IHorizontalFacingBlock;
import cn.solarmoon.solarmoon_core.api.common.block.IWaterLoggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

public abstract class AbstractContainerBlock extends BaseWaterBlock implements IHorizontalFacingBlock, IWaterLoggedBlock {

    public AbstractContainerBlock(SoundType soundType) {
        super(BlockBehaviour.Properties.of()
                .sound(soundType)
                .strength(0.7F)
                .noOcclusion()
        );
    }

    public AbstractContainerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        getThis(player, level, pos, state, InteractionHand.MAIN_HAND, true);
        super.attack(state, level, pos, player);
    }

}
