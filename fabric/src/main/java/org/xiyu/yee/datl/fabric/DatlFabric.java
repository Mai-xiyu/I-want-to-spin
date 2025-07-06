package org.xiyu.yee.datl.fabric;

import org.xiyu.yee.datl.Datl;
import net.fabricmc.api.ModInitializer;

public final class DatlFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Datl.init();
    }
}
