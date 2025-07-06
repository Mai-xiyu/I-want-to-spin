package org.xiyu.yee.datl.fabric.client;

import net.fabricmc.api.ClientModInitializer;

public final class DatlFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindings.register();
        FabricRenderEvents.register();
    }
}
