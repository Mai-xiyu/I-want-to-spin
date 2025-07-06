package org.xiyu.yee.datl.neoforge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.client.PlayerRenderHandler;

@EventBusSubscriber(modid = Datl.MOD_ID)
public class NeoForgeRenderEvents {
    @SubscribeEvent
    public static void onRenderLivingPre(RenderLivingEvent.Pre event) {
            PlayerRenderHandler.onRenderPlayer(event.getPoseStack());
    }

}
