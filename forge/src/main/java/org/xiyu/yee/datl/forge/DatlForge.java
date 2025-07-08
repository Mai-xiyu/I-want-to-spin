package org.xiyu.yee.datl.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.forge.client.ForgeRenderEvents;

@Mod(Datl.MOD_ID)
public final class DatlForge {
    public DatlForge(FMLJavaModLoadingContext context) {
        // Run our common setup.
        Datl.init();
        var modBusGroup = context.getModBusGroup();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            FMLClientSetupEvent.getBus(modBusGroup).addListener(DatlForge::clientSetup);
        }
    }
    
    private static void clientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ForgeRenderEvents.class);
    }
}
