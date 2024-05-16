package cn.solarmoon.idyllic_food_diary;

import cn.solarmoon.idyllic_food_diary.core.client.registry.*;
import cn.solarmoon.idyllic_food_diary.core.common.registry.IMCommonConfig;
import cn.solarmoon.idyllic_food_diary.core.common.registry.*;
import cn.solarmoon.idyllic_food_diary.core.common.registry.ability.*;
import cn.solarmoon.idyllic_food_diary.core.compat.appleskin.AppleSkin;
import cn.solarmoon.idyllic_food_diary.core.compat.create.Create;
import cn.solarmoon.idyllic_food_diary.core.compat.farmersdelight.FarmersDelight;
import cn.solarmoon.idyllic_food_diary.core.compat.patchouli.Patchouli;
import cn.solarmoon.solarmoon_core.api.ObjectRegistry;
import cn.solarmoon.solarmoon_core.api.SolarMoonBase;
import cn.solarmoon.solarmoon_core.api.util.static_utor.Debug;
import cn.solarmoon.solarmoon_core.api.util.static_utor.Translator;
import net.minecraftforge.fml.common.Mod;

import static cn.solarmoon.idyllic_food_diary.IdyllicFoodDiary.MOD_ID;


@Mod(MOD_ID)
public class IdyllicFoodDiary extends SolarMoonBase {

    public static final String MOD_ID = "idyllic_food_diary";
    public static final ObjectRegistry REGISTRY = ObjectRegistry.create(MOD_ID);
    public static Debug DEBUG = Debug.create("[§6田园食记§f] ", IMCommonConfig.deBug);
    public static final Translator TRANSLATOR = Translator.create(MOD_ID);

    @Override
    public void objectsClientOnly() {
        IMLayers.register();
        IMParticles.register();
        IMBlockEntityRenderers.register();
        IMEntityRenderers.register();
    }

    @Override
    public void objects() {
        IMItems.register();
        IMBlocks.register();
        IMBlockEntities.register();
        IMFluids.register();
        IMEffects.register();
        IMCreativeModeTab.register();
        IMRecipes.register();
        IMFeatures.register();
        IMEntityTypes.register();
        IMPacks.register();
        IMSounds.register();
    }

    @Override
    public void eventObjectsClientOnly() {
        new IMClientEvents().register();
        new IMGui().register();
        new IMTooltipRenderers().register();
    }

    @Override
    public void eventObjects() {
        new IMCommonEvents().register();
        new IMDataPacks().register();
    }

    @Override
    public void xData() {
        IMCommonConfig.register();
    }

    @Override
    public void abilitiesClientOnly() {

    }

    @Override
    public void abilities() {
        IMTickers.register();
        IMTileDataHolders.register();
        IMSpittableItems.register();
        IMPlaceableItems.register();
        IMComposterThs.register();
    }

    @Override
    public void compats() {
        Patchouli.register();
        FarmersDelight.register();
        Create.register();
        AppleSkin.register();
    }

}