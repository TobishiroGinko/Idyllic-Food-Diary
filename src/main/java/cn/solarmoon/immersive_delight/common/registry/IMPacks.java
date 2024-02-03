package cn.solarmoon.immersive_delight.common.registry;

import cn.solarmoon.immersive_delight.api.network.PackToClient;
import cn.solarmoon.immersive_delight.api.network.PackToServer;
import cn.solarmoon.immersive_delight.api.registry.BasePackRegistry;
import net.minecraft.resources.ResourceLocation;

import static cn.solarmoon.immersive_delight.ImmersiveDelight.MOD_ID;

public class IMPacks extends BasePackRegistry {

    @Override
    public void addRegistry() {
        packs.add(new PackToServer(new ResourceLocation(MOD_ID, "server")));
        packs.add(new PackToClient(new ResourceLocation(MOD_ID, "client")));
    }

}