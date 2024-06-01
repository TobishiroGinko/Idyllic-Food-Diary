package cn.solarmoon.idyllic_food_diary.api.util;

import cn.solarmoon.idyllic_food_diary.compat.farmersdelight.util.FarmersUtil;
import cn.solarmoon.idyllic_food_diary.core.data.tags.IMBlockTags;
import cn.solarmoon.solarmoon_core.api.util.VecUtil;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT;

public class FarmerUtil {

    /**
     * 检查是否为热源
     */
    public static boolean isHeatSource(BlockState state) {
        boolean commonFlag = state.is(IMBlockTags.HEAT_SOURCE) || FarmersUtil.isHeatSource(state);
        if (state.hasProperty(LIT)) {
            return commonFlag && state.getValue(LIT);
        }
        return commonFlag;
    }

    /**
     * 吐子儿
     */
    public static void spit(Item item, int count, Player player) {
        Level level = player.level();
        Vec3 spawnPos = VecUtil.getSpawnPosFrontEntity(player, 0.5, -0.5);
        ItemEntity itemEntity = new ItemEntity(level, spawnPos.x, spawnPos.y, spawnPos.z, new ItemStack(item, count));
        itemEntity.addDeltaMovement(player.getLookAngle().scale(0.3));
        itemEntity.setPickUpDelay(20);
        level.addFreshEntity(itemEntity);
    }

    /**
     * 吐子儿
     */
    public static void spit(ItemStack stack, Player player) {
        Level level = player.level();
        Vec3 spawnPos = VecUtil.getSpawnPosFrontEntity(player, 0.5, -0.5);
        ItemEntity itemEntity = new ItemEntity(level, spawnPos.x, spawnPos.y, spawnPos.z, stack);
        itemEntity.addDeltaMovement(player.getLookAngle().scale(0.3));
        itemEntity.setPickUpDelay(20);
        level.addFreshEntity(itemEntity);
    }

}
