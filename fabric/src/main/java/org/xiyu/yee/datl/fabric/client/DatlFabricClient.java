package org.xiyu.yee.datl.fabric.client;

import net.fabricmc.api.ClientModInitializer;

public final class DatlFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 注册按键绑定
        KeyBindings.register();
        
        // 注册渲染事件
        FabricRenderEvents.register();
    }
}
