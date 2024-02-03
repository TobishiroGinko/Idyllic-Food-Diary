package cn.solarmoon.immersive_delight.common.item;


import cn.solarmoon.immersive_delight.common.registry.IMEntityBlocks;
import cn.solarmoon.immersive_delight.api.common.item.specific.AbstractCupItem;
import net.minecraft.world.item.Item;

/**
 * 青瓷杯
 * 小容量的杯子item
 * 默认容量250，只能堆叠一个
 */
public class CeladonCupItem extends AbstractCupItem {

    public CeladonCupItem() {
        super(IMEntityBlocks.CELADON_CUP.get(), 250, new Item.Properties()

        );
    }

}