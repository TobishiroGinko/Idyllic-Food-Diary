package cn.solarmoon.idyllic_food_diary.compat.appleskin;

import net.minecraftforge.fml.ModList;

/**
 * 苹果皮
 */
public class AppleSkin {

    public static final String MOD_ID = "appleskin";

    public static boolean isLoaded() {
        return ModList.get().isLoaded(MOD_ID);
    }

    public static void register() {
        if (isLoaded()) {
            new ASClientEvents().register();
        }
    }


}
