package org.xiyu.yee.datl.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.forge.client.ForgeRenderEvents;

@Mod(Datl.MOD_ID)
public final class DatlForge {
    public DatlForge(FMLJavaModLoadingContext context) {
        // Run our common setup.
        Datl.init();
        var modBusGroup = context.getModBusGroup();
        FMLClientSetupEvent.getBus(modBusGroup).addListener(this::clientSetup);
    }
    
    private void clientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ForgeRenderEvents.class);
    }
}
