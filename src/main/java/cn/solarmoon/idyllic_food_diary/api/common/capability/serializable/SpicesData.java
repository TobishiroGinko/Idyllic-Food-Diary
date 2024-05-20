package cn.solarmoon.idyllic_food_diary.api.common.capability.serializable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpicesData implements INBTSerializable<CompoundTag> {

    private final SpiceList spices;

    public SpicesData() {
        spices = new SpiceList();
    }

    /**
     * 注意，一般不用这个方法修改内容物！
     */
    public SpiceList getSpices() {
        return spices;
    }

    public boolean isEmpty() {
        return spices.isEmpty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        for (Spice spices : spices) {
            listTag.add(spices.serializeNBT());
        }
        tag.put("Spices", listTag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ListTag listTag = nbt.getList("Spices", ListTag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag tag = listTag.getCompound(i);
            spices.add(Spice.readFromNBT(tag));
        }
    }

}
