package cn.solarmoon.idyllic_food_diary.registry.common;


import cn.solarmoon.idyllic_food_diary.IdyllicFoodDiary;
import cn.solarmoon.solarmoon_core.api.entry.common.SoundEntry;

public class IMSounds {
    public static void register() {}

    public static final SoundEntry PLAYER_POUR = IdyllicFoodDiary.REGISTRY.sound()
            .name("player.pouring_water")
            .build();

    public static final SoundEntry PLAYER_SPILLING_WATER = IdyllicFoodDiary.REGISTRY.sound()
            .name("player.water_splashing")
            .build();

    public static final SoundEntry BOILING_WATER = IdyllicFoodDiary.REGISTRY.sound()
            .name("block.boiling_water")
            .build();

    public static final SoundEntry STIR_FRY = IdyllicFoodDiary.REGISTRY.sound()
            .name("block.stir_fry")
            .build();

    public static final SoundEntry STIR_SIZZLE = IdyllicFoodDiary.REGISTRY.sound()
            .name("block.stir_sizzle")
            .build();

}
