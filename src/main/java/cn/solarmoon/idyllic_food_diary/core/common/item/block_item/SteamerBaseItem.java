package cn.solarmoon.idyllic_food_diary.core.common.item.block_item;

import cn.solarmoon.idyllic_food_diary.core.client.renderer.Item.SteamerBaseItemRenderer;
import cn.solarmoon.idyllic_food_diary.core.common.registry.IMBlocks;
import cn.solarmoon.solarmoon_core.api.client.renderer.Item.BaseItemRenderer;
import cn.solarmoon.solarmoon_core.api.client.renderer.Item.IItemRendererProvider;
import cn.solarmoon.solarmoon_core.api.common.item.ITankItem;
import net.minecraft.world.item.BlockItem;

import java.util.function.Supplier;

public class SteamerBaseItem extends BlockItem implements ITankItem, IItemRendererProvider {

    public SteamerBaseItem() {
        super(IMBlocks.STEAMER_BASE.get(), new Properties().stacksTo(1));
    }

    @Override
    public int getMaxCapacity() {
        return 1000;
    }

    @Override
    public Supplier<BaseItemRenderer> getRendererFactory() {
        return SteamerBaseItemRenderer::new;
    }
}
