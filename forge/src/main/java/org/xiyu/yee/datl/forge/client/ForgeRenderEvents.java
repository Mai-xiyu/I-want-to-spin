package org.xiyu.yee.datl.forge.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.client.PlayerRenderHandler;

@Mod.EventBusSubscriber(modid = Datl.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeRenderEvents {
    
    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        // 在渲染前应用旋转效果
        LocalPlayer localPlayer = Minecraft.getInstance().player;

            PlayerRenderHandler.onRenderPlayer(localPlayer, event.getPoseStack());

    }
}
