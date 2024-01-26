package cn.solarmoon.immersive_delight.api.common.block.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public abstract class BaseFruitCrop extends SweetBerryBushBlock {

    public BaseFruitCrop() {
        super(BlockBehaviour.Properties
                .copy(Blocks.SWEET_BERRY_BUSH)
        );
    }

    /**
     * 设置可放置（种植）的地方
     * 默认为橡树树叶
     * 一般情况下请修改canSurviveBlock
     */
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return ( canSurviveTag() == null ? state.is(canSurviveBlock()) : state.is(canSurviveTag()) )
                && ( level.getBlockState(pos.below()).canBeReplaced() ||  level.getBlockState(pos.below()).getBlock().equals(this));
    }

    /**
     * 设置可放置（种植）的具体方块
     * 默认为橡树树叶处
     */
    public Block canSurviveBlock() {
        return Blocks.OAK_LEAVES;
    }

    /**
     * 根据tag设定可种植的
     */
    public TagKey<Block> canSurviveTag() {
        return null;
    }

    /**
     * 设置可存在的地方
     * 默认为橡树树叶下方（用的可放置逻辑）
     */
    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return mayPlaceOn(levelReader.getBlockState(pos.above()), levelReader, pos.above());
    }

    /**
     * 中键物品，默认和产物一致
     */
    @Override
    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return harvestResults(null, true).getItem().getDefaultInstance();
    }

    /**
     * 设置收获产物
     * 默认产率1个，低概率2-3个，极低概率4个
     * @param flag 一般决定了是否为最终成长阶段
     */
    public ItemStack harvestResults(@Nullable Level level, boolean flag) {
        int j = 0;
        if (level != null) {
            float random = level.random.nextFloat();
            if(random < 0.1) j = 1;
            else if(random < 0.01) j = 2;
            else if(random < 0.001) j = 3;
        }
        return new ItemStack(getHarvestItem(), j + (flag ? 1 : 0));
    }

    /**
     * 如果不想改倍率直接改这个
     * 获取收割物
     */
    public abstract Item getHarvestItem();

    /**
     * 收获功能
     * 直接抄的甜浆果丛
     * 区别是这里返回age0，因为水果一般整个摘了
     */
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            popResource(level, pos, harvestResults(level, flag));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = state.setValue(AGE, 0);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.use(state, level, pos, player, hand, hitResult);
        }
    }

}
