package org.xiyu.yee.datl.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.neoforge.client.NeoForgeRenderEvents;

@Mod(Datl.MOD_ID)
public final class DatlNeoForge {
    public DatlNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        // Run our common setup.
        Datl.init();
        
        // 注册客户端设置事件
        modEventBus.addListener(this::clientSetup);
    }
    
    private void clientSetup(FMLClientSetupEvent event) {
        // 注册客户端渲染事件
        NeoForge.EVENT_BUS.register(NeoForgeRenderEvents.class);
    }
}

