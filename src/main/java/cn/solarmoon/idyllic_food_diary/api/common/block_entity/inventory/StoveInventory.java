package cn.solarmoon.idyllic_food_diary.api.common.block_entity.inventory;

import cn.solarmoon.idyllic_food_diary.core.common.block_entity.StoveBlockEntity;
import cn.solarmoon.idyllic_food_diary.core.common.registry.IMItems;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class StoveInventory extends ItemStackHandler {

    private final StoveBlockEntity stove;

    public StoveInventory(StoveBlockEntity stove) {
        super(2);
        this.stove = stove;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == 1) return stack.is(ItemTags.LOGS) || stack.is(ItemTags.PLANKS);
        return stack.is(IMItems.COOKING_POT.get());
    }

    @Override
    protected void onContentsChanged(int slot) {
        if (slot == 0) stove.updatePot();
    }

}
