package cn.solarmoon.idyllic_food_diary.core.client.event;

import cn.solarmoon.idyllic_food_diary.api.common.item.AbstractCupItem;
import cn.solarmoon.idyllic_food_diary.api.common.item.AbstractKettleItem;
import cn.solarmoon.idyllic_food_diary.api.util.namespace.NETList;
import cn.solarmoon.idyllic_food_diary.core.common.item.block_item.CookingPotItem;
import cn.solarmoon.idyllic_food_diary.core.common.item.block_item.SteamerBaseItem;
import cn.solarmoon.idyllic_food_diary.core.common.registry.IMPacks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * shift+左键把水倒空
 */
public class DrinkingClientEvent {

    /**
     * 可以把水泼出去并对范围内的实体产生效果
     */
    @SubscribeEvent
    public void pour(PlayerInteractEvent.LeftClickEmpty event) {
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        if(stack.getItem() instanceof AbstractCupItem
                || stack.getItem() instanceof AbstractKettleItem
                || stack.getItem() instanceof CookingPotItem
                || stack.getItem() instanceof SteamerBaseItem
        ) {
            if(event.getEntity().isCrouching()) {
                IMPacks.SERVER_PACK.getSender().send(NETList.POURING, pos, stack);
            }
        }
    }

}
